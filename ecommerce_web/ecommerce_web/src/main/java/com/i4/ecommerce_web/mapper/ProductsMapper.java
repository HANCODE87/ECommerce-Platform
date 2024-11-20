package com.i4.ecommerce_web.mapper;

import com.i4.ecommerce_web.pojo.Products;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 根據銷量排序查詢產品
     * @param isAsc 是否是正序
     * @param sort 排序方式
     * @return 排序後的產品
     */
    List<Products> orderBySort(String sort,boolean isAsc);

    /**
     * 根據關鍵字查詢產品
     * @param keyWord 關鍵字
     * @param sort 排序方式
     * @param isAsc 是否是正序
     * @return 符合關鍵字的商品
     */
    List<Products> searchByKeyword(String keyWord,String sort,boolean isAsc);
}
