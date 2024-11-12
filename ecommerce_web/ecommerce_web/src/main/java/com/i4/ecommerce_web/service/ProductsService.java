package com.i4.ecommerce_web.service;

import com.i4.ecommerce_web.pojo.Products;

import java.util.List;

public interface ProductsService {




    /**
     * 新增產品
     * @param products products
     */
    void addProduct(Products products);

    /**
     * 查詢產品
     * @param prodId prodId
     * @return product
     */
    Products searchProduct(Integer prodId);

    /**
     * 更新產品
     * @param products products
     */
    Products updateProduct(Products products);

    /**
     * 刪除產品
     * @param prodId 產品id
     * @return 是否刪除成功
     */
    Boolean deleteProduct(Integer prodId);

    /**
     * 回傳List根據銷售量排序
     * @return List<Products> 排序後的產品名單
     */
    List<Products> orderBySales();

}
