package com.i4.ecommerce_web.mapper;

import com.i4.ecommerce_web.pojo.Products;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductsMapper {
    /**
     * 新增產品
     * @param products prodName,prodPrice,prodStock
     */
    @Insert("insert into products(prod_name, prod_price, prod_stock) values (#{prodName}, #{prodPrice}, #{prodStock})")
    void addProduct(Products products);

    /**
     * 根據id查詢產品
     * @param prodId Integer id
     * @return products
     */
    @Select("select * from products where prod_id = #{prodId}")
    Products findById(Integer prodId);

    /**
     * 根據id更新產品
     * @param products products
     */
    void updateById(Products products);

    /**
     * 根據id刪除產品
     * @param prodId prodId
     */
    @Delete("delete from products where prod_id = #{prodId}")
    void deleteById(Integer prodId);
}
