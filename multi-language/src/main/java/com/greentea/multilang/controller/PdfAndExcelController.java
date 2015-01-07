package com.greentea.multilang.controller;

import com.greentea.multilang.pojo.Person;
import com.greentea.multilang.pojo.Student;
import com.greentea.multilang.pojo.User;
import com.greentea.multilang.view.JXLExcelView;
import com.greentea.multilang.view.ViewExcel;
import com.greentea.multilang.view.ViewExcel1;
import com.greentea.multilang.view.ViewPDF1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/exceltest",method= RequestMethod.GET) //http://localhost:8080/multi-language/exceltest
    public ModelAndView viewExcel(HttpServletRequest request, HttpServletResponse response){
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
    @RequestMapping(value = "/pdftest",method=RequestMethod.GET) //http://localhost:8080/multi-language/pdftest
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

    private List getStudents(){
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
    public ModelAndView viewJxlExcel(@RequestParam(value="titles", required=false, defaultValue="编号,姓名,年龄,性别,密码,地址") String titles ,@RequestParam(value="colums", required=false, defaultValue="id,name,age,sex,password,address") String colums,HttpServletRequest request,
                                     HttpServletResponse response) {
        String [] array1 = null;
        if(null != colums && colums.indexOf(",") != -1){
            array1 = colums.split(",");
        }
        String [] array2 = null;
        if(null != titles && titles.indexOf(",") != -1){
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
    @RequestMapping("/viewPDF")
    public ModelAndView viewPDF2(HttpServletRequest request, HttpServletResponse response)  {
        Map model = new HashMap();
        return new ModelAndView("pdf", model);

    }
}
