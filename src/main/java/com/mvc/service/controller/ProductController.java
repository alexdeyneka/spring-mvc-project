package com.mvc.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.service.model.ProductDTO;
import com.mvc.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/all")
    public String getAll(Model model) throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/product", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        List<ProductDTO> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.toString().contains("INFO")) {
                model.addAttribute("response", "Product list is empty. Add some products");
                return "productList";
            }
            list.add(new ObjectMapper().readValue(jsonArray.get(i).toString(), ProductDTO.class));
        }
        model.addAttribute("response", list);
        return "productList";
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "product";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam(value = "id") Integer id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "quantity") Integer quantity,
                             @RequestParam(value = "price") Double price,
                             @RequestParam(value = "productionDate") String productionDate,
                             @RequestParam(value = "expirationDate") String expirationDate,
                             Model model) throws ParseException {
        model.addAttribute("info", "Product was added successfully");
        ProductDTO productDTO = new ProductDTO();
        Date prodDate = new SimpleDateFormat("yyyy-MM-dd").parse(productionDate);
        Date expDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate);
        productDTO.setProductId(id);
        productDTO.setName(name);
        productDTO.setQuantity(quantity);
        productDTO.setPrice(price);
        productDTO.setProductionDate(prodDate);
        productDTO.setExpireDate(expDate);
        restTemplate.postForObject("http://localhost:8080/product/create", new HttpEntity<>(productDTO), ProductDTO.class);
        return "success";
    }
}
