package com.mvc.service.service;

import com.mvc.service.model.ProductDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    @Spy
    private ProductService productService;

    private List<ProductDTO> productList = new ArrayList<>();

    @Before
    public void setUp() {
        productList = Utils.getProductList();
    }

    @Test
    public void parseDate() throws ParseException {
        String date = "2019-25-05";
        Date originDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String actual = originDate.toString();
        assertEquals("Tue Jan 05 00:00:00 EET 2021", actual);
    }

    @Test
    public void getForEntity() {
        String expected = "Some body";
        Mockito.when(restTemplate.getForEntity(anyString(), any(Class.class)))
                .thenReturn(new ResponseEntity<>(expected, HttpStatus.OK));
        ResponseEntity<String> actual = productService.getForEntity();
        assertEquals(expected, actual.getBody());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void postForObject() {
        ProductDTO expectedProduct = productList.get(0);
        Mockito.when(restTemplate.postForObject(anyString(), any(HttpEntity.class), any(Class.class)))
                .thenReturn(expectedProduct);
        productService.postForObject(expectedProduct);
        verify(restTemplate, times(1)).postForObject(anyString(), any(HttpEntity.class), any(Class.class));
    }
}