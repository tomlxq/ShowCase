package com.tom.mvc.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * 说明：
 * 此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器
 * @author tom
 * @version 创建时间： 2015/2/9  20:39
 */
public class UserControllerStandaloneSetupTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        UserController userController = new UserController();
        mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void testView() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(MockMvcResultMatchers.view().name("user/view"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
    }

}
