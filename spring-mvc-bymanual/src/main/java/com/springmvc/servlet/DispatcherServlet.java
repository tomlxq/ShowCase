package com.springmvc.servlet;

import com.springmvc.annotation.*;
import com.springmvc.util.ClassTools;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.Map.Entry;


/**
 * @author com.springmvc
 * @Description 请求几种处理类
 * @date 2017年8月30日下午5:23:54
 */
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1378531571714153483L;

    /**
     * 要扫描的包，只有在这个包下并且加了注解的才会呗扫描到
     */
    private static final String PACKAGE = "com.springmvc";

    private static final String CONTROLLER_KEY = "controller";

    private static final String METHOD_KEY = "method";

    // 存放Controller中url和方法的对应关系，格式：{url:{controller:实例化后的对象,method:实例化的方法}}
    private static Map<String, Map<String, Object>> urlMethodMapping = new HashMap<>();

    public DispatcherServlet() {
        super();
    }

    /**
     * 初始化方法，用于实例化扫描到的对象，并做注入和url映射（注：该方法逻辑上已经判断了，只执行一次）
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 只处理一次
        if (urlMethodMapping.size() > 0) {
            return;
        }
        // 开始扫描包下全部class文件
        Set<Class<?>> classes = ClassTools.getClasses(PACKAGE);

        // 存放Controller和Service的Map，格式：{beanName:实例化后的对象}
        Map<String, Object> instanceNameMap = new HashMap<String, Object>();
        // 存放Service接口类型与接口实例对象的Map，格式：{Service.instance.class:实现类实例化后的对象}
        Map<Class<?>, Object> instanceTypeMap = new HashMap<Class<?>, Object>();

        // 组装instanceMap
        buildInstanceMap(classes, instanceNameMap, instanceTypeMap);

        // 开始注入
        doIoc(instanceNameMap, instanceTypeMap);

        // 注入完之后开始映射url和method
        buildUrlMethodMapping(instanceNameMap, urlMethodMapping);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 完整路径
        String url = req.getRequestURI();
        // 跟路径
        String path = req.getContextPath();
        // 计算出method上配置的路径
        String finallyUrl = url.replace(path, "");

        // 取出这个url对应的Controller和method
        Map<String, Object> map = urlMethodMapping.get(finallyUrl);
        if (map == null) {
            throw new RuntimeException("请求地址不存在！");
        }
        Method method = (Method) map.get(METHOD_KEY);
        try {
            // 封装需要注入的参数，目前只支持request和response以及加了@RequestParam标签的基本数据类型的参数注入
            List<Object> paramValue = buildParamObject(req, resp, method);

            // 没有参数的场合
            if (paramValue.size() == 0) {
                method.invoke(map.get(CONTROLLER_KEY));
            } else {
                // 有参数的场合
                method.invoke(map.get(CONTROLLER_KEY), paramValue.toArray());
            }
        } catch (Exception e) {
            throw new RuntimeException("执行url对应的method失败！");
        }
    }

    /**
     * 封装需要注入的参数，目前只支持request和response以及加了@RequestParam标签的基本数据类型的参数注入
     *
     * @param req
     * @param resp
     * @param method
     * @return
     */
    private List<Object> buildParamObject(HttpServletRequest req, HttpServletResponse resp, Method method) {

        // 封装需要注入的参数，目前只支持request和response以及加了@RequestParam标签的基本数据类型的参数注入
        Parameter[] parameters = method.getParameters();
        List<Object> paramValue = new ArrayList<>();
        for (Parameter parameter : parameters) {
            // 当前参数有别名注解并且别名不为空
            if (parameter.isAnnotationPresent(RequestParam.class) && !parameter.getAnnotation(RequestParam.class).value().isEmpty()) {
                // 我们获取
                String value = req.getParameter(parameter.getAnnotation(RequestParam.class).value());
                paramValue.add(value);
            } else if (parameter.getParameterizedType().getTypeName().contains("HttpServletRequest")) {
                paramValue.add(req);
            } else if (parameter.getParameterizedType().getTypeName().contains("HttpServletResponse")) {
                paramValue.add(resp);
            } else {
                paramValue.add(null);
            }
            // 这里只做了request和response以及基本数据类型的参数注入，如果要做对象的注入也是可以写，这里暂时就不写了
            // TODO: 做对象的注入
        }
        return paramValue;
    }

    /**
     * 注入完之后开始映射url和method
     *
     * @param instanceMap
     * @param urlMethodMapping
     */
    private void buildUrlMethodMapping(Map<String, Object> instanceMap,
                                       Map<String, Map<String, Object>> urlMethodMapping) {
        // 注入完之后开始映射url和method
        // 组装urlMethodMapping
        for (Entry<String, Object> entry : instanceMap.entrySet()) {

            // 迭代出所有的url
            String parenturl = "";

            // 判断Controller上是否加了requestMapping
            if (entry.getValue().getClass().isAnnotationPresent(RequestMapping.class)) {
                parenturl = entry.getValue().getClass().getAnnotation(RequestMapping.class).value();
            }

            // 取出全部的method
            Method[] methods = entry.getValue().getClass().getMethods();

            // 迭代全部的方法，检查哪些方法上加了requestMaping注解
            for (Method method : methods) {
                if (method.isAnnotationPresent(RequestMapping.class)) {

                    // 得到一个完整的url请求
                    String url = parenturl + method.getAnnotation(RequestMapping.class).value();
                    Map<String, Object> value = new HashMap<>();
                    value.put(CONTROLLER_KEY, entry.getValue());
                    value.put(METHOD_KEY, method);
                    urlMethodMapping.put(url, value);
                }
            }
        }
    }

    /**
     * 根据实例Map开始注入
     *
     * @param instanceMap
     */
    private void doIoc(Map<String, Object> instanceMap, Map<Class<?>, Object> instanceTypeMap) {
        // 开始注入，我们只对加了@Controller和@Service标签中的，属性加了@autowired的进行注入操作
        for (Entry<String, Object> entry : instanceMap.entrySet()) {

            // 取出全部的属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            // 循环属性校验哪些是加了@autowired注解的
            for (Field field : fields) {
                field.setAccessible(true);// 可访问私有属性

                // 有注解的时候
                if (field.isAnnotationPresent(Autowired.class)) {

                    // 没有配别名注入的时候
                    if (field.getAnnotation(Autowired.class).value().isEmpty()) {
                        // 直接获取
                        try {
                            // 根据类型来获取他的实现类
                            Object object = instanceTypeMap.get(field.getType());
                            field.set(entry.getValue(), object);
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            // 将被注入的对象
                            Object object = instanceMap.get(field.getAnnotation(Autowired.class).value());
                            field.set(entry.getValue(), object);
                        } catch (Exception e) {
                            throw new RuntimeException("开始注入时出现了异常");
                        }
                    }
                }
            }
        }
    }

    /**
     * 组装instanceMap
     *
     * @param classes
     * @param instanceMap
     */
    private void buildInstanceMap(Set<Class<?>> classes, Map<String, Object> instanceMap, Map<Class<?>, Object> instanceTypeMap) {
        // 开始循环全部class
        for (Class<?> clasz : classes) {

            // 组装instanceMap
            // 判断是否是是加了Controller注解的java对象
            if (clasz.isAnnotationPresent(Controller.class)) {
                try {
                    // 实例化对象
                    Object obj = clasz.newInstance();
                    Controller controller = clasz.getAnnotation(Controller.class);

                    // 如果没有设置别名，那么用类名首字母小写做key
                    if (controller.value().isEmpty()) {
                        instanceMap.put(firstLowerName(clasz.getSimpleName()), obj);
                    } else {
                        // 如果设置了别名那么用别名做key
                        instanceMap.put(controller.value(), obj);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("初始化instanceMap时在处理Controller注解时出现了异常");
                }
            } else if (clasz.isAnnotationPresent(Service.class)) {
                // 实例化对象
                Object obj = null;
                try {
                    // 实例化对象
                    obj = clasz.newInstance();
                    Service service = clasz.getAnnotation(Service.class);

                    // 如果没有设置别名，那么用类名首字母小写做key
                    if (service.value().isEmpty()) {
                        instanceMap.put(firstLowerName(clasz.getSimpleName()), obj);
                    } else {
                        // 如果设置了别名那么用别名做key
                        instanceMap.put(service.value(), obj);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("初始化instanceMap时在处理Service注解时出现了异常");
                }
                // 实现的接口数组
                Class<?>[] interfaces = clasz.getInterfaces();
                for (Class<?> class1 : interfaces) {
                    if (instanceTypeMap.get(class1) != null) {
                        throw new RuntimeException(class1.getName() + "接口不能被多个类实现！");
                    }
                    instanceTypeMap.put(class1, obj);
                }
            } else {
                continue;
            }
        }
    }

    /**
     * 首字母小写
     *
     * @param name
     * @return
     */
    private String firstLowerName(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }
}
