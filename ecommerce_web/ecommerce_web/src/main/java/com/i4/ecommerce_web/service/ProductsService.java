package com.i4.ecommerce_web.service;

import com.i4.ecommerce_web.pojo.Products;

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

    Boolean deleteProduct(Integer prodId);
}
