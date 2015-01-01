package cn.javass.chapter6.web.controller.paramtype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import cn.javass.chapter6.model.UserModel;

@Controller
@RequestMapping("/method/param/annotation")
@SessionAttributes(value = {"user"}, types = {})    //① 标识 模型对象中名字如果是“user”将存储在会话范围，并自动暴露到模型数据中
public class SessionAttributeController {
    @ModelAttribute("user")    //② 如果模型数据中没有名字为user的对象，调用该方法并存储到模型数据中

    /*   @SessionAttributes(value = {"user"}) 标识将模型数据中的名字为“user” 的对象存储到会话中（默认HttpSession），此处value指定将模型数据中的哪些数据（名字进行匹配）存储到会话中，此外还有一个types属性表示模型数据中的哪些类型的对象存储到会话范围内，如果同时指定value和types属性则那些名字和类型都匹配的对象才能存储到会话范围内。



    包含@SessionAttributes的执行流程如下所示：

            ① 首先根据@SessionAttributes注解信息查找会话内的对象放入到模型数据中；

            ② 执行@ModelAttribute注解的方法：如果模型数据中包含同名的数据，则不执行@ModelAttribute注解方法进行准备表单引用数据，而是使用①步骤中的会话数据；如果模型数据中不包含同名的数据，执行@ModelAttribute注解的方法并将返回值添加到模型数据中；

            ③ 执行@RequestMapping方法，绑定@ModelAttribute注解的参数：查找模型数据中是否有@ModelAttribute注解的同名对象，如果有直接使用，否则通过反射创建一个；并将请求参数绑定到该命令对象；

    此处需要注意：如果使用@SessionAttributes注解控制器类之后，③步骤一定是从模型对象中取得同名的命令对象，如果模型数据中不存在将抛出HttpSessionRequiredException Expected session attribute ‘user’(Spring3.1)

    或HttpSessionRequiredException Session attribute ‘user’ required - not found in session(Spring3.0)异常。

            ④ 如果会话可以销毁了，如多步骤提交表单的最后一步，此时可以调用SessionStatus对象的setComplete()标识当前会话的@SessionAttributes指定的数据可以清理了，此时当@RequestMapping功能处理方法执行完毕会进行清理会话数据。
*/
    public UserModel initUser() {
        return new UserModel();
    }

    @RequestMapping("/session1")   //③ 首先查找模型数据中是否有user对象，有直接使用，没有则创建一个，并将请求参数绑定到该对象上
    public String session1(@ModelAttribute("user") UserModel user, ModelMap model, WebRequest request, SessionStatus status) {
        System.out.println(user == model.get("user"));
        user.setUsername("zhang");
        return "success";
    }

    @RequestMapping("/session2")   //③
    public String session(@ModelAttribute("user") UserModel user, ModelMap model, WebRequest request, SessionStatus status) {
        System.out.println(user == request.getAttribute("user", WebRequest.SCOPE_SESSION));
        System.out.println(user == model.get("user"));
        System.out.println(user);
        if (true) { //④如果会话可以终止了，就 标识会话结束，可以清理掉会话数据了
            status.setComplete();
        }
        return "success";
    }
}
