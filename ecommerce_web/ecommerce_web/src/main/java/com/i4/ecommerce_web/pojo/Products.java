package com.i4.ecommerce_web.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    private Integer prodId;
    private String prodName;
    private Double prodPrice;
    private Integer prodStock;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
