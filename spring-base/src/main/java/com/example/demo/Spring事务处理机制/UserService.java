package com.example.demo.Spring事务处理机制;

/**
 * Created by tom on 2018/1/25.
 */
import org.springframework.jdbc.core.JdbcTemplate;

public class UserService {
    public static final String ADD_USER="insert into t_user(id,name) values(1,'duck')";

    private BookService bs;
    private JdbcTemplate jdbcTemplate;

    public void addUser()throws Exception {
        this.bs.addBook();
        this.jdbcTemplate.execute(ADD_USER);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BookService getBs() {
        return bs;
    }

    public void setBs(BookService bs) {
        this.bs = bs;
    }
}
