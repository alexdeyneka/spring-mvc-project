package com.mvc.service.controller;

import com.mvc.service.entity.User;
import com.mvc.service.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(NavigationController.class)
public class NavigationControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private List<User> userList = new ArrayList<>();

    @Before
    public void setUp() {
        userList = Utils.getUserList();
    }

    @Test
    public void homePage() throws Exception {
        this.mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(forwardedUrl("/WEB-INF/views/home.jsp"));
    }

    @Test
    public void registerGetTest() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(forwardedUrl("/WEB-INF/views/register.jsp"));
    }

    @Test
    public void registerPostTestSuccess() throws Exception {
        Optional<User> optionalUser = Optional.ofNullable(userList.get(0));
        given(userService.save(any(User.class))).willReturn(optionalUser);
        this.mockMvc.perform(post("/register?userName=someName&password=1111"))
                .andExpect(status().isOk())
                .andExpect(view().name("success"))
                .andExpect(forwardedUrl("/WEB-INF/views/success.jsp"))
                .andExpect(model().attribute("info", "User was registered successfully"));
    }

    @Test
    public void registerPostTestError() throws Exception {
        this.mockMvc.perform(post("/register?userName=someName&password=1111"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(forwardedUrl("/WEB-INF/views/error.jsp"));
    }

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(forwardedUrl("/WEB-INF/views/login.jsp"));
    }

    @Test
    public void welcomeSuccessTest() throws Exception {
        User user = userList.get(0);
        given(userService.findAll()).willReturn(userList);
        this.mockMvc.perform(post("/login?userName=Alex&password=111"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(forwardedUrl("/WEB-INF/views/welcome.jsp"))
                .andExpect(model().attribute("userName", user.getUserName()))
                .andExpect(model().attribute("password", user.getPassword()));
    }

    @Test
    public void welcomeInvalidCredsErrorTest() throws Exception {
        given(userService.findAll()).willReturn(userList);
        this.mockMvc.perform(post("/login?userName=Nick&password=777"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(forwardedUrl("/WEB-INF/views/error.jsp"))
                .andExpect(model().attribute("userName", "Nick"))
                .andExpect(model().attribute("password", "777"));
    }

    @Test
    public void welcomeEmptyListErrorTest() throws Exception {
        given(userService.findAll()).willReturn(new ArrayList<>());
        this.mockMvc.perform(post("/login?userName=Alex&password=111"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(forwardedUrl("/WEB-INF/views/error.jsp"))
                .andExpect(model().attribute("info", "Access DENIED: invalid credentials"));
    }
}