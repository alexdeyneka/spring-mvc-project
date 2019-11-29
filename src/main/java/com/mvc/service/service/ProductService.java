package com.mvc.service.service;

import com.mvc.service.model.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    @Value("${applicationUrl}")
    private String applicationUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> getProductList() {
        return restTemplate.getForEntity(applicationUrl + "/product", String.class);
    }

    public void addOrUpdateProduct(ProductDTO productDTO) {
        restTemplate.postForObject(applicationUrl + "/product/create", new HttpEntity<>(productDTO), ProductDTO.class);
    }
}
