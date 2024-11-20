package com.i4.ecommerce_web.service.impl;

import com.i4.ecommerce_web.mapper.ProductsMapper;
import com.i4.ecommerce_web.pojo.Products;
import com.i4.ecommerce_web.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductsMapper productsMapper;

    public ProductsServiceImpl(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    /**
     * 新增產品
     * @param products products prodName,prodPrice,prodStock
     */
    @Override
    public void addProduct(Products products) {
        products.setCreateTime(LocalDateTime.now());
        products.setUpdateTime(LocalDateTime.now());
        productsMapper.addProduct(products);
    }

    /**
     * 查詢產品
     * @param prodId prodId
     * @return product
     */
    @Override
    public Products findProductById(Integer prodId) {
        return productsMapper.findById(prodId);
    }

    /**
     * 根據id更新產品
     * @param products products
     * @return product
     */
    @Override
    public Products updateProduct(Products products) {
        productsMapper.findById(products.getProdId());
        products.setUpdateTime(LocalDateTime.now());
        productsMapper.updateById(products);
        return productsMapper.findById(products.getProdId());
    }

    /**
     * 根據產品id刪除產品
     * @param prodId 產品id
     * @return boolean
     */
    @Override
    public Boolean deleteProduct(Integer prodId) {
        if(productsMapper.findById(prodId) == null){
            return false;
        }
        productsMapper.deleteById(prodId);
        return true;
    }

    /**
     * 根據排序方式查詢產品
     * @param sort 排序方式
     * @param isAsc 是否是正序
     * @return 排序後的產品
     */
    @Override
    public List<Products> orderProducts(String sort, boolean isAsc) {
        return productsMapper.orderBySort(sort, isAsc);
    }

    /**
     * 根據關鍵字查詢產品
     * @param keyWord 關鍵字
     * @param sort 排序方式
     * @param isAsc 是否是正序
     * @return 符合關鍵字的商品
     */
    @Override
    public List<Products> searchByKeyWord(String keyWord, String sort,boolean isAsc) {
        return productsMapper.searchByKeyword(keyWord, sort, isAsc);
    }

}
