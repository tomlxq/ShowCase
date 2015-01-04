package com.photograph.Controller;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/4  19:18
 */

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest extends ControllerBase {





    @Test
    //有些单元测试你不希望回滚
    @Rollback(false)
    public void ownerId() throws Exception {
        mockMvc.perform((get("/spring/rest/4.do"))).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform((get("/spring/test.do"))).andExpect(status().isOk())
                .andDo(print())
                .andExpect(model().attributeHasNoErrors("teacher"));
    }

    @Test
    public void testAll() throws Exception {
        mockMvc.perform((post("/user/") .param("limit", "10").param("start", "1"))).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void changePwdTest() throws Exception {

        mockMvc.perform((post("/user/changepwd")
                .param("oldPassword", "")
                .param("newPassword", "")
                .param("userId", "ADSF")
                .param("comparePassword", "")))
                .andExpect(status().isOk()).andDo(print());
    }

}
