/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/5/14  7:15
 */
package com.spring.service;

import com.spring.exception.ShopNotFound;
import com.spring.model.Shop;

import java.util.List;

public interface ShopService {

    public Shop create(Shop shop);
    public Shop delete(int id) throws ShopNotFound;
    public List<Shop> findAll();
    public Shop update(Shop shop) throws ShopNotFound;
    public Shop findById(int id);

}