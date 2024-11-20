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
    Products findProductById(Integer prodId);

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
     * 根據排序方式查詢產品
     * @param sort 排序方式
     * @param isAsc 是否是正序
     * @return 排序後的產品
     */
    List<Products> orderProducts(String sort,boolean isAsc);

    /**
     * 根據關鍵字查詢產品
     * @param keyWord 關鍵字
     * @param sort 排序方式
     * @param isAsc 是否是正序
     * @return 符合關鍵字的商品
     */
    List<Products> searchByKeyWord(String keyWord, String sort,boolean isAsc);
}
