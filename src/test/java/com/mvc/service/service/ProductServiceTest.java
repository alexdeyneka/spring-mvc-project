package com.mvc.service.service;

import com.mvc.service.entity.User;
import org.junit.Before;
import utils.ProductUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServiceTest {

    private List<User> userList = new ArrayList<>();

    @Before
    public void setUp() {
        userList = Arrays.asList(ProductUtils.getUserOne(),
                ProductUtils.getUserTwo(),
                ProductUtils.getUserThree());
    }
}