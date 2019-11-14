package com.mvc.service.model;

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

    public ProductDTO(Integer productId, String name, Integer quantity, Double price, Date productionDate, Date expireDate) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productionDate = productionDate;
        this.expireDate = expireDate;
    }
}
