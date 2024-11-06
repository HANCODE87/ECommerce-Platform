package com.i4.ecommerce_web.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//為NULL的屬性不會被回傳
public class Result {
    private String msg;
    private Object data;

    private Result(String msg) {
        this.msg = msg;
    }

    public static Result success(String msg,Object data) {
        return new Result(msg, data);
    }



    public static Result success(Object data) {
        return new Result("success",data);
    }


    public static Result success(String msg) {
        return new Result(msg);
    }

    public static Result success() {
        return new Result();
    }

    public static Result error(String msg) {
        return new Result(msg);
    }

}
