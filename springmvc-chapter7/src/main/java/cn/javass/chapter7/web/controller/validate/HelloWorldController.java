package cn.javass.chapter7.web.controller.validate;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.javass.chapter7.model.UserModel;

@Controller
public class HelloWorldController {
	//通过在命令对象上注解@Valid来告诉Spring MVC此命令对象在绑定完毕后需要进行JSR-303验证，如果验证失败会将错误信息添加到errors错误对象中
	//在浏览器地址栏中输入http://localhost:9080/springmvc-chapter7/validate/hello，
	// 即没有username数据，请求后将直接到验证失败界面并显示错误消息“用户名不能为空”，如果请求时带上“?username=zhang”将重定向到成功页面。
	@RequestMapping("/validate/hello")
	public String validate(@Valid @ModelAttribute("user") UserModel user, Errors errors) {
		
		if(errors.hasErrors()) {
			return "validate/error";
		}
		return "redirect:/success";
	}
}
