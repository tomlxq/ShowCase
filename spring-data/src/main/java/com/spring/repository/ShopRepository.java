/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/5/14  7:14
 */
package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

}