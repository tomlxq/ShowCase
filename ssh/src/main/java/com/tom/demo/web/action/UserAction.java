package com.tom.demo.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.tom.demo.entity.User;
import com.tom.demo.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/31  20:24
 */
@Namespace("/user")
@Component
@Scope("prototype")
public class UserAction extends ActionSupport {

    @Resource
    private UserService userService;
    private User user;
    private List<User> userList;
    @Action(value = "regUser", results = {
            @Result(name = "success", location = "login.ftl"),
            @Result(name = "input", location = "error.jsp")})
    public String reg() {
        System.out.println("----reg page----");
        return SUCCESS;
    }
    @Action(value = "saveUser", results = {
            @Result(name = "success", location = "list.ftl"),
            @Result(name = "input", location = "error.jsp")})
    public String save() {
        System.out.println("----save----");
        System.out.println(user);
        userService.saveUser(user);
        return SUCCESS;
    }
    public String list() {
        System.out.println("----list----");
        //System.out.println(user);

        userList = userService.queryUserAll();
        return SUCCESS;
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
