package com.mvc.service.controller;

import com.mvc.service.model.ProductDTO;
import com.mvc.service.service.ProductService;
import com.mvc.service.utils.ProductUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@RequiredArgsConstructor
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductUtils productUtils;

    @GetMapping("/all")
    public String getAll(Model model) {
        ResponseEntity<String> response = productService.getProductList();
        model.addAttribute("response", response.getBody());
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
        ProductDTO productDTO = new ProductDTO(id, name, quantity, price, productUtils.parseDate(productionDate), productUtils.parseDate(expirationDate));
        productService.addOrUpdateProduct(productDTO);
        model.addAttribute("info", "Product was added successfully");
        return "success";
    }
}
