package com.mvc.service.service;

import com.mvc.service.entity.User;
import com.mvc.service.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.Utils;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private List<User> userList = new ArrayList<>();

    @Before
    public void setUp() {
        userList = Utils.getUserList();
    }

    @Test
    public void findById() {
        long id = userList.get(0).getId();
        when(userRepository.findById(id)).thenReturn(Optional.ofNullable(userList.get(0)));
        Optional<User> userOptional = userService.findById(id);
        userOptional.ifPresent(user -> assertEquals(userList.get(0), user));
    }

    @Test
    public void findAll() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.findAll();
        assertEquals(userList, result);
    }

    @Test
    public void userNameExists() {
        String userName = "Alex";
        List<User> originalList = userList;
        List<User> fakeList = Collections.singletonList(new User(10, "Taras", "000"));
        assertTrue(originalList.stream().anyMatch(n -> n.getUserName().equals(userName)));
        assertTrue(fakeList.stream().noneMatch(n -> n.getUserName().equals(userName)));
    }

    @Test
    public void save() {
        User originalUser = userList.get(0);
        when(userRepository.save(any(User.class))).thenReturn(originalUser);
        User savedUser = userRepository.save(originalUser);
        assertEquals(originalUser, savedUser);
    }
}