package cn.javass.chapter6.web.controller.paramtype;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.javass.chapter6.model.DataBinderTestModel;
import cn.javass.chapter6.model.UserModel;


@Controller
@RequestMapping("/method/param/annotation")
public class ModelAttributeTypeController {

    @ModelAttribute("cityList")
    public List<String> cityList() {
        return Arrays.asList("北京", "山东");
        //如上代码会在执行功能处理方法之前执行，并将其自动添加到模型对象中，在功能处理方法中调用Model 入参的containsAttribute("cityList")将会返回true。
    }

    @ModelAttribute("user")  //①
    public UserModel getUser(@RequestParam(value = "username", defaultValue = "") String username) {
        //TODO 去数据库根据用户名查找用户对象
        //如你要修改用户资料时一般需要根据用户的编号/用户名查找用户来进行编辑，此时可以通过如上代码查找要编辑的用户。
        UserModel user = new UserModel();
        user.setRealname("zhang");
        return user;
    }


    @RequestMapping(value = "/model1") //②
    public String test1(@ModelAttribute("user") UserModel user, Model model) {
        //http://localhost:8080/springmvc-chapter6/method/param/annotation/model1?username=zhang&password=123&workInfo.city=bj
        //只是此处多了一个注解@ModelAttribute("user")，它的作用是将该绑定的命令对象以“user”为名称添加到模型对象中供视图页面展示使用。我们此时可以在视图页面使用${user.username}来获取绑定的命令对象的属性。
        // 绑定请求参数到命令对象支持对象图导航式的绑定，如请求参数包含“?username=zhang&password=123&workInfo.city=bj”自动绑定到user中的workInfo属性的city属性中。
        //此处我们看到①和②有同名的命令对象，那Spring Web MVC内部如何处理的呢：

        //(1、首先执行@ModelAttribute注解的方法，准备视图展示时所需要的模型数据；@ModelAttribute注解方法形式参数规则和@RequestMapping规则一样，如可以有@RequestParam等；

        //（2、执行@RequestMapping注解方法，进行模型绑定时首先查找模型数据中是否含有同名对象，如果有直接使用，如果没有通过反射创建一个，因此②处的user将使用①处返回的命令对象。即②处的user等于①处的user。

        System.out.println(model.containsAttribute("cityList"));
        System.out.println(user);
        return "success";
    }

    //http://localhost:9080/springmvc-chapter6/method/param/annotation/model2/username=wang?username=zhang&bool=yes&schooInfo.specialty=computer&hobbyList[0]=program&hobbyList[1]=music&map[key1]=value1&map[key2]=value2&state=blocked
    @RequestMapping(value = "/model2/{username}")
    public String test2(@ModelAttribute("model") DataBinderTestModel model) {
        System.out.println(model);
        // 含“bool=yes&schooInfo.specialty=computer&hobbyList[0]=program&hobbyList[1]=music&map[key1]=value1&map[key2]=value2&state=blocked”会自动绑定到命令对象上。


        //当URI模板变量和请求参数同名时，URI模板变量具有高优先权。

        return "success";
    }


    @RequestMapping(value = "/model3")
    public
    @ModelAttribute("user2")
    UserModel test3(@ModelAttribute("user2") UserModel user) {
        UserModel user2 = new UserModel();
        user2.setUsername("zhang");
        return user2;
        //大家可以看到返回值类型是命令对象类型，而且通过@ModelAttribute("user2")注解，此时会暴露返回值到模型数据（名字为user2）中供视图展示使用。那哪个视图应该展示呢？此时Spring Web MVC会根据RequestToViewNameTranslator进行逻辑视图名的翻译，详见【4.15.5、RequestToViewNameTranslator】一节。


        //此时又有问题了，@RequestMapping注解方法的入参user暴露到模型数据中的名字也是user2，其实我们能猜到：

        //（3、@ModelAttribute注解的返回值会覆盖@RequestMapping注解方法中的@ModelAttribute注解的同名命令对象。

    }


    @RequestMapping(value = "/model4")
    public String test4(@ModelAttribute UserModel user, Model model) {
       // 此时我们没有为命令对象提供暴露到模型数据中的名字，此时的名字是什么呢？Spring Web MVC自动将简单类名（首字母小写）作为名字暴露，如“cn.javass.chapter6.model.UserModel”暴露的名字为“userModel”。
        System.out.println(model.containsAttribute("userModel"));
        return "success";
    }

    @RequestMapping(value = "/model5")
    public String test5(UserModel user, Model model) {
        System.out.println(model.containsAttribute("userModel"));
        return "success";
    }

    @RequestMapping(value = "/model6")
    public
    @ModelAttribute
    List<String> test6() {
        return Arrays.asList("山东", "北京");
    }

    @RequestMapping(value = "/model7")
    public
    @ModelAttribute
    List<UserModel> test7() {
        return Arrays.asList(new UserModel(), new UserModel());
    }

    @RequestMapping(value = "/model8")
    public
    @ModelAttribute
    Map<String, UserModel> test8() {
        return new HashMap<String, UserModel>();
    }
    //对于集合类型（Collection接口的实现者们，包括数组），生成的模型对象属性名为“简单类名（首字母小写）”+“List”，如List<String>生成的模型对象属性名为“stringList”，List<UserModel>生成的模型对象属性名为“userModelList”。
}
