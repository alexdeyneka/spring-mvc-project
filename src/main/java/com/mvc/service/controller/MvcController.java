package com.mvc.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.service.entity.ProductDTO;
import com.mvc.service.entity.User;
import com.mvc.service.service.MvcService;
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
public class MvcController {

    private final MvcService mvcService;

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String welcome(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, Model model) {
        String result = "";
        List<User> list = mvcService.findAll();
        if (list.isEmpty()) {
            model.addAttribute("info", "Access DENIED: invalid credentials");
            return "error";
        }
        for (User user : list) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                model.addAttribute("userName", userName);
                model.addAttribute("password", password);
                result = "welcome";
            } else {
                model.addAttribute("info", "Access DENIED: invalid credentials");
                model.addAttribute("userName", userName);
                model.addAttribute("password", password);
                result = "error";
            }
        }
        return result;
    }

    @GetMapping("/all")
    public String getAll(Model model) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/product";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        List<ProductDTO> list = new ArrayList<>();
        for (int i = 0; i<jsonArray.length(); i++) {
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
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/product/create";
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(id);
        productDTO.setName(name);
        productDTO.setQuantity(quantity);
        productDTO.setPrice(price);
        Date prodDate = new SimpleDateFormat("yyyy-MM-dd").parse(productionDate);
        Date expDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate);
        productDTO.setProductionDate(prodDate);
        productDTO.setExpireDate(expDate);
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO);
        restTemplate.postForObject(url, request, ProductDTO.class);
        return "success";
    }

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, Model model) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        mvcService.save(user);
        model.addAttribute("info", "User was registered successfully");
        return "success";
    }
}
