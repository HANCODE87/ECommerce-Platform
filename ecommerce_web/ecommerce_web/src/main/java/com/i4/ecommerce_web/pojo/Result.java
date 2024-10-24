package com.i4.ecommerce_web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;


    public static Result success(String msg,Object data) {
        return new Result(200, msg, data);
    }

    public static Result error(String msg) {
        return new Result(400, msg, null);
    }

    public static Result success(){
        return new Result(200,"success",null);
    }
}
