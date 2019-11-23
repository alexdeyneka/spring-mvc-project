package com.mvc.service.controller;

import com.mvc.service.model.ProductDTO;
import com.mvc.service.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    private List<ProductDTO> productDTOList = new ArrayList<>();

    @Before
    public void setUp() {
        productDTOList = Utils.getProductList();
    }

    @Test
    public void getAll() throws Exception {
        ResponseEntity<String> response = new ResponseEntity<>(productDTOList.toString(), HttpStatus.OK);
        given(productService.getForEntity()).willReturn(response);
        this.mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(forwardedUrl("/WEB-INF/views/productList.jsp"))
                .andExpect(model().attribute("response", productDTOList.toString()));
        verify(productService, times(1)).getForEntity();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void addProduct() throws Exception {
        this.mockMvc.perform(get("/addProduct"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(forwardedUrl("/WEB-INF/views/product.jsp"));
    }

    @Test
    public void testAddProduct() throws Exception {
        Date mockDate = new Date();
        given(productService.parseDate(anyString())).willReturn(mockDate);
        this.mockMvc.perform(post("/addProduct?id=5&name=oops&quantity=5&price=15&productionDate=null&expirationDate=null"))
                .andExpect(status().isOk())
                .andExpect(view().name("success"))
                .andExpect(forwardedUrl("/WEB-INF/views/success.jsp"))
                .andExpect(model().attribute("info", "Product was added successfully"));
        verify(productService, times(1)).postForObject(any(ProductDTO.class));
        verify(productService, times(2)).parseDate(anyString());
        verifyNoMoreInteractions(productService);
    }
}