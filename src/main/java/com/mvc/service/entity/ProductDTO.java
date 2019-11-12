package com.mvc.service.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private Integer productId;
    private String name;
    private Integer quantity;
    private Double price;
    private Date productionDate;
    private Date expireDate;

    public ProductDTO() {
    }
}
