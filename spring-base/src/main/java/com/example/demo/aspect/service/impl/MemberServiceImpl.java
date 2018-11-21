package com.example.demo.aspect.service.impl;

import com.example.demo.aspect.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Override
    public String addMember() throws Exception {
        logger.debug("add member");
        throw new Exception("ddddddd");
        //return "tom";
    }

    @Override
    public void delMember() {
        logger.debug("del member");
    }

    @Override
    public void updateMember() {
        logger.debug("update member");
    }

    @Override
    public void queryMember() {
        logger.debug("query member");
    }
}
