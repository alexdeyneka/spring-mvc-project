package com.mvc.service.service;

import com.mvc.service.entity.User;
import com.mvc.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MvcService {

    private final UserRepository userRepository;

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean userNameExists(String name) {
        List<User> list = findAll();
        for (User user : list) {
            if (name.equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }

    public User save(User user) {
        if (userNameExists(user.getUserName())) {
            log.error("The user with name " + user.getUserName() + " already exists. Try again");
            ResponseEntity.badRequest().build();
        }
        return userRepository.save(user);
    }
}
