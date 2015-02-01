package com.tom.demo.dao;

import com.tom.demo.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/31  20:30
 */

public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    List<User> findAll();
}
