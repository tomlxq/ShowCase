package com.tom.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/2/9  20:44
 */
//XML风格
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp") //、@WebAppConfiguration：测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；value指定web应用的根；
@ContextHierarchy({ //、@ContextHierarchy：指定容器层次，即spring-config.xml是父容器，而spring-mvc.xml是子容器
        @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")
})
//
public class UserControllerWebAppContextSetupTest {

    @Autowired
    private WebApplicationContext wac;//通过@Autowired WebApplicationContext wac：注入web环境的ApplicationContext容器
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();//然后通过MockMvcBuilders.webAppContextSetup(wac).build()创建一个MockMvc进行测试
    }

    /**
     * 1、mockMvc.perform执行一个请求；
     2、MockMvcRequestBuilders.get("/user/1")构造一个请求
     3、ResultActions.andExpect添加执行完成后的断言
     4、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
     5、ResultActions.andReturn表示执行完成后返回相应的结果。
     * @throws Exception
     */
    @Test
    public void testView() throws Exception {//测试普通控制器
        mockMvc
                .perform(get("/user/1"))
                .andExpect(view().name("user/view"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/user/view.jsp"))
                .andExpect(model().attributeExists("user"))
                .andExpect(content().string(Matchers.contains("你好")))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
