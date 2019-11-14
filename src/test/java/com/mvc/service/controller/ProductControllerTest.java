package com.mvc.service.controller;

import com.mvc.service.model.ProductDTO;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ProductControllerTest {

    private List<ProductDTO> productDTOList = new ArrayList<>();

    @Before
    public void setUp() {
        productDTOList = Utils.getProductList();
    }

    @Test
    public void getAll() {
    }

    @Test
    public void addProduct() {
    }

    @Test
    public void testAddProduct() {
    }
}