package com.mvc.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String name;
    private Integer quantity;
    private Double price;
    private Date productionDate;
    private Date expireDate;
}
