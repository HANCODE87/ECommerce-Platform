package com.i4.ecommerce_web.service.impl;

import com.i4.ecommerce_web.mapper.ProductsMapper;
import com.i4.ecommerce_web.pojo.Products;
import com.i4.ecommerce_web.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsMapper productsMapper;

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
    public Products searchProduct(Integer prodId) {
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

    @Override
    public Boolean deleteProduct(Integer prodId) {
        if(productsMapper.findById(prodId) == null){
            return false;
        }
        productsMapper.deleteById(prodId);
        return true;
    }

}
