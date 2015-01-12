package com.greentea.multilang.controller;

import com.greentea.multilang.pojo.Person;
import com.greentea.multilang.pojo.Student;
import com.greentea.multilang.pojo.User;
import com.greentea.multilang.view.*;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.sf.json.JSONObject;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  16:14
 */
@Controller
public class PdfAndExcelController {
    private static final Logger logger = LoggerFactory.getLogger(PdfAndExcelController.class);

    @RequestMapping("/pdf")  //http://localhost:8080/multi-language/pdf.pdf
    public String helloPDF() {
        return "helloPDF";
    }

    @RequestMapping("/excel")  //http://localhost:8080/multi-language/excel.xls
    public String helloExcel() {
        return "excel";
    }

    //http://localhost:8080/multi-language/view.pdf
    //http://localhost:8080/multi-language/view.json
    //http://localhost:8080/multi-language/view.xml
    @RequestMapping("/view")
    public Person getPerson(Model model) {
        Person person = new Person();
        person.setId(1);
        person.setName("test");
        model.addAttribute("person", person);
        return person;
    }

    @RequestMapping(value = "/exceltest", method = RequestMethod.GET) //http://localhost:8080/multi-language/exceltest
    public ModelAndView viewExcel(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("ViewController.viewExcel is started......");
        List list = new ArrayList();
        Map model = new HashMap();
        list.add("test1");
        list.add("test2");
        model.put("list", list);
        ViewExcel viewExcel = new ViewExcel();
        logger.debug("ViewController.viewExcel is ended......");
        return new ModelAndView(viewExcel, model);
    }



    @RequestMapping(value = "/pdftest", method = RequestMethod.GET) //http://localhost:8080/multi-language/pdftest
    public ModelAndView viewPDF(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        List list = new ArrayList();
        Map model = new HashMap();
        list.add("test1");
        list.add("test2");
        model.put("list", list);
        ViewPDF1 viewPDF = new ViewPDF1();
        return new ModelAndView(viewPDF, model);
    }

    @RequestMapping("/viewExcel1")  //http://localhost:8080/multi-language/viewExcel1
    public ModelAndView viewExcel1(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        model.put("list", getStudents());
        return new ModelAndView(new ViewExcel1(), model);
    }

    private List getStudents() {
        List stuList = new ArrayList();
        // 构造数据
        Student stu1 = new Student("gaoxiang1", "male1", "20060101", 1);
        Student stu2 = new Student("gaoxiang2", "male2", "20060102", 2);
        Student stu3 = new Student("gaoxiang3", "male3", "20060103", 3);
        Student stu4 = new Student("gaoxiang4", "male4", "20060104", 4);
        Student stu5 = new Student("gaoxiang5", "male5", "20060105", 5);
        stuList.add(stu1);
        stuList.add(stu2);
        stuList.add(stu3);
        stuList.add(stu4);
        stuList.add(stu5);
        return stuList;
    }

    @RequestMapping("/jxlExcel")
    public ModelAndView viewJxlExcel(@RequestParam(value = "titles", required = false, defaultValue = "编号,姓名,年龄,性别,密码,地址") String titles, @RequestParam(value = "colums", required = false, defaultValue = "id,name,age,sex,password,address") String colums, HttpServletRequest request,
                                     HttpServletResponse response) {
        String[] array1 = null;
        if (null != colums && colums.indexOf(",") != -1) {
            array1 = colums.split(",");
        }
        String[] array2 = null;
        if (null != titles && titles.indexOf(",") != -1) {
            array2 = titles.split(",");
        }
        Map model = new HashMap();
        // 构造数据
        List<User> users = new ArrayList<User>();

        users.add(new User("123456", "李逵", "123", "成都市", "1", 23));
        users.add(new User("123457", "李四", "124", "北京市", "2", 53));
        users.add(new User("123458", "李三", "125", "河南市", "0", 73));
        users.add(new User("123459", "李五", "126", "大路市", "3", 93));
        model.put("list", users);
        model.put("columns", array1);
        model.put("titles", array2);
        return new ModelAndView(new JXLExcelView(), model);
    }

    @RequestMapping("/viewPDF1")
    public ModelAndView viewPDF1(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Map model = new HashMap();
        model.put("list", getStudents());
        return new ModelAndView(new ViewPDF1(), model);
    }

    public String getTemplateString(String template, Map map) {
        File file = new File(template);
        logger.debug("{}", template);
        if (StringUtils.isNotEmpty(template) && !file.exists()) return "";
        try {
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(file.getParent()));
            Template temp = cfg.getTemplate(file.getName(), Locale.US, "UTF-8");
            StringWriter out = new StringWriter();
            temp.process(map, out);
            return out.toString();
        } catch (TemplateException t) {
            logger.debug("{}", t);
        } catch (IOException i) {
            logger.debug("{}", i);
        }
        return "";
    }

    @RequestMapping("/viewFtl")
    public ModelAndView pdf(HttpServletRequest request,
                            HttpServletResponse response) {

        Map model = new HashMap();
        List<JSONObject> list = new ArrayList<JSONObject>();
        JSONObject role = new JSONObject();
        role.put("roleId", 1);
        role.put("roleName", "admin");
        role.put("createTime", new Date().getTime());
        role.put("roleTypeId", 1);
        role.put("roleApplyScopeId", 1);
        list.add(role);
        role = new JSONObject();
        role.put("roleId", 2);
        role.put("roleName", "guest");
        role.put("createTime", new Date().getTime());
        role.put("roleTypeId", 2);
        role.put("roleApplyScopeId", 2);
        list.add(role);
        role = new JSONObject();
        role.put("roleId", 3);
        role.put("roleName", "user");
        role.put("createTime", new Date().getTime());
        role.put("roleTypeId", 3);
        role.put("roleApplyScopeId", 3);
        list.add(role);
        // model.put("roleList", list);
        ModelAndView mav = new ModelAndView();
        if (list == null) {
            mav.setViewName("forward:/error/page-not-found");
            return mav;
        }
        mav.setViewName("pdf");
        mav.addObject("roleList", list);
        return mav;
    }

    @RequestMapping("/viewPDF2")
    public ModelAndView generatePdf(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException, DocumentException {

        //中文需转义
        String pdfName = "pdfName";
        // response.setHeader("Content-disposition", "attachment;filename="+pdfName);
        response.setContentType("application/pdf");
        OutputStream os = response.getOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        //指定模板地址
        renderer.setDocument("http://localhost:8080/viewFtl");
        // renderer.getSharedContext().setUserAgentCallback(new HttpURLUserAgent(renderer.getOutputDevice()));
        ITextFontResolver fontResolver = renderer.getFontResolver();
        // if (StringUtils.isOSWindow())
        fontResolver.addFont("C:/Windows/Fonts/ARIALUNI.TTF",
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //  else
        //     fontResolver.addFont("/usr/share/fonts/TTF/ARIALUNI.TTF",
        //             BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(os);
        os.close();

        return null;
    }

    /*http://blog.csdn.net/shanliangliuxing/article/details/6833471*/

    /**
     * * 通过模板导出PDF
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws DocumentException
     * @throws TemplateException
     */
    @RequestMapping("/viewPDF3")
    public File viewPDF2(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException, TemplateException {
        Map model = new HashMap();
        List<JSONObject> list = new ArrayList<JSONObject>();
        JSONObject role = new JSONObject();
        role.put("roleId", 1);
        role.put("roleName", "管理员");
        role.put("createTime", new Date().getTime());
        role.put("roleTypeId", 1);
        role.put("roleApplyScopeId", 1);
        list.add(role);
        role = new JSONObject();
        role.put("roleId", 2);
        role.put("roleName", "guest");
        role.put("createTime", new Date().getTime());
        role.put("roleTypeId", 2);
        role.put("roleApplyScopeId", 2);
        list.add(role);
        role = new JSONObject();
        role.put("roleId", 3);
        role.put("roleName", "user");
        role.put("createTime", new Date().getTime());
        role.put("roleTypeId", 3);
        role.put("roleApplyScopeId", 3);
        list.add(role);
        model.put("roleList", list);

        String tplPath = request.getSession().getServletContext().getRealPath("") + "\\WEB-INF\\jsp\\pdf.ftl";
        logger.debug("{}", request.getSession().getServletContext().getRealPath("/"));
       // logger.debug("{}", request.getServletContext().getResourcePaths("/WEB-INF/jsp/pdf.ftl"));
        //F:\data\wwwroot\ShowCase\multi-language\src\main\webapp\
        tplPath = request.getSession().getServletContext().getRealPath("") + "\\WEB-INF\\jsp\\pdf.ftl";

        logger.debug("{}", new File(tplPath).exists());
        logger.debug("##########{}", request.getContextPath());
        logger.debug("##########{}", tplPath);
        String htmlStr = getTemplateString(tplPath, model);
        logger.debug("{}", htmlStr);

        //中文需转义
        //String pdfName = "pdfName";
        String excelName = "测试中文.pdf";
     /*   例1.内嵌显示一个文件
        Content-disposition: inline; filename=foobar.pdf
        例2.往response里附加一个文件

        Content-disposition: attachment; filename=foobar.pdf*/

        //response.setHeader("Content-Disposition", "inline;filename="+ URLEncoder.encode(excelName, "utf-8")); // 设置文件名
       // response.setContentType("application/pdf");
        OutputStream os = response.getOutputStream();
        //response.setContentType("APPLICATION/OCTET-STREAM");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename="+ URLEncoder.encode(excelName, "UTF-8"));

        ITextRenderer renderer = new ITextRenderer();
        //指定模板地址
        //renderer.setDocument("http://localhost:8080/multi-language");

        // 解决图片的相对路径问题
        //renderer.getSharedContext().setBaseURL("file:/F:/data/wwwroot/ShowCase/multi-language/src/main/webapp");
        //logger.debug("########## renderer.getSharedContext().getBaseURL {}", renderer.getSharedContext().getBaseURL());
        //renderer.getSharedContext().setBaseURL(new File(request.getSession().getServletContext().getRealPath("/")).toURI().toURL().toString());
        //logger.debug("########## renderer.getSharedContext().getBaseURL {}", renderer.getSharedContext().getBaseURL());
        // String base=new File(request.getSession().getServletContext().getRealPath("")).toURI().toURL().toString();
        // logger.debug("{}",base);
        renderer.getSharedContext().setUserAgentCallback(new HttpURLUserAgent(request, renderer.getOutputDevice()));
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/Windows/Fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //这里引用了arialuni.ttf字体，它位于C盘windows/fonts文件夹下，将它引用后，还需要在页面上使用这个字体
        // <body style="font-family:'Arial Unicode MS'">

        InputSource is = new InputSource(new BufferedReader(new StringReader(htmlStr)));
        Document dom = XMLResource.load(is).getDocument();
        renderer.setDocument(dom, (String) null);
        //file:/F:/data/wwwroot/ShowCase/multi-language/src/main/webapp/
        // renderer.setDocument(dom, base);
        // renderer.getSharedContext().setBaseURL(base);
        renderer.layout();
        renderer.createPDF(os);
        os.close();
        return null;


    }
    public static void export(InputStream inputStream, Map beanParams, OutputStream out) throws Exception {
        try {
            // Map beanParams = (Map) ((DynaBean) context).get(DATA);
            XLSTransformer transformer = new XLSTransformer();
            org.apache.poi.ss.usermodel.Workbook wb = transformer.transformXLS(inputStream, beanParams);
            wb.write(out);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * 通过模板导出excel
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/excel2")
    public ModelAndView viewExcel2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map model = new HashMap();
       // List list = new ArrayList();
        List<Student> list = new ArrayList<Student>();
        Student role = new Student("test1","男",new Date().toString(),90);
        list.add(role);
        role = new Student("test2","男",new Date().toString(),78);
        list.add(role);
        role = new Student("test3","女",new Date().toString(),56);
        list.add(role);  role = new Student("test4","女",new Date().toString(),88);
        list.add(role);

       // list.add("test1");
       // list.add("test2");
        model.put("allStu", list);
        model.put("lastYearS", 2001);
        model.put("thisToday", 2002);

        response.reset();
        //
        OutputStream out = response.getOutputStream();
        InputStream in = null;
        String filename = "测试.xls";
        //response.setHeader("Content-Disposition", "inline;filename="+ URLEncoder.encode(filename, "utf-8")); // 设置文件名
        //response.setHeader("Content-Disposition", "inline; filename=\"" + filename + ".xls\"");

       // response.setContentType("APPLICATION/OCTET-STREAM");
        response.setContentType("application/vnd.ms-excel");
       // response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(filename, "UTF-8"));
        in = request.getServletContext().getResourceAsStream("/WEB-INF/jsp/TestExcel.xls");
        export(in, model, out);
        out.flush();
        return null;
    }

}
