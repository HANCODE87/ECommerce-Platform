package com.i4.ecommerce_web.controller;

import com.i4.ecommerce_web.pojo.Products;
import com.i4.ecommerce_web.pojo.Result;
import com.i4.ecommerce_web.service.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    /**
     * 新增產品
     * @param products products prodName,prodPrice,prodStock
     * @return Result 產品資訊
     */
    @PostMapping()
    public ResponseEntity<Result<Void>> addProduct(@RequestBody Products products){
        log.info("新增產品:{}",products);
        productsService.addProduct(products);
        return new ResponseEntity<>(Result.success("新增產品成功"), HttpStatus.CREATED);
    }

    /**
     * 根據id查詢產品
     * @param prodId Integer
     * @return  product
     */
    @GetMapping("/{prodId}")
    public ResponseEntity<Result<Products>> searchProduct(@PathVariable Integer prodId){
        log.info("查詢產品:{}",prodId);
        return new ResponseEntity<>(Result.success(productsService.searchProduct(prodId)), HttpStatus.OK);
    }

    /**
     * 根據id更新產品
     * @param prodId Integer
     * @param products products
     * @return success
     */
    @PutMapping("/{prodId}")
    public ResponseEntity<Result<Void>> updateProduct(@PathVariable Integer prodId, @RequestBody Products products){
        log.info("更新產品:{}",prodId);
        products.setProdId(prodId);
        if(productsService.updateProduct(products) == null){
            return new ResponseEntity<>(Result.error("ID錯誤，沒有資料"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Result.success(),HttpStatus.OK);
    }

    /**
     * 根據id刪除產品
     * @param prodId Integer
     * @return message
     */
    @DeleteMapping("/{prodId}")
    public ResponseEntity<Result<Void>> deleteProduct(@PathVariable Integer prodId){
        log.info("刪除產品:{}",prodId);
        if (productsService.deleteProduct(prodId)){
            return new ResponseEntity<>(Result.error("刪除失敗"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Result.success("成功刪除產品"),HttpStatus.OK);
    }

    /**
     * 生成熱銷產品
     * @return List<Products> 產品的List
     */
    @GetMapping("/popular")
    public ResponseEntity<Result<List<Products>>> orderBySales(){
        List<Products> products = productsService.orderBySales();
        return new ResponseEntity<>(Result.success(products),HttpStatus.OK);
    }

}
