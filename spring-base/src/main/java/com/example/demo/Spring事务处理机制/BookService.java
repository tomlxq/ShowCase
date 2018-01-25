package com.example.demo.Spring事务处理机制;

/**
 * Created by tom on 2018/1/25.
 */
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.RollbackException;

public class BookService {
    public static final String ADD_BOOK="insert into t_book(id,name) values(1,'duck-j2ee')";
    private JdbcTemplate jdbcTemplate;

    public void addBook() throws Exception{
        this.jdbcTemplate.execute(ADD_BOOK);
        throw new RollbackException("跳出执行");
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}