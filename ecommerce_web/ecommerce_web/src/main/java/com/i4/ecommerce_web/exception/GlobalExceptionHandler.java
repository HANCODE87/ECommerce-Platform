package com.i4.ecommerce_web.exception;

import com.i4.ecommerce_web.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) //捕獲所有異常
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("操作失敗，請聯繫管理員");
    }
}
