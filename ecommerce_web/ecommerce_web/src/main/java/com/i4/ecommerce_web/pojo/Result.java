package com.i4.ecommerce_web.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//為NULL的屬性不會被回傳
public class Result<T> {
    private String msg;
    private T data;

    private Result(String msg) {
        this.msg = msg;
    }

    public static <T> Result<T> success(String msg,T data) {
        return new Result<>(msg, data);
    }



    public static <T> Result<T> success(T data) {
        return new Result<>("success", data);
    }


    public static Result<Void> success(String msg) {
        return new Result<>(msg);
    }

    public static Result<Void> success() {
        return new Result<>();
    }

    public static Result<Void> error(String msg) {
        return new Result<>(msg);
    }

    public static <T> Result<T> error(String msg,T data) {
        return new Result<>(msg, data);
    }
}
