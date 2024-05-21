package com.michaelakamihe.ecommercebackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.michaelakamihe.ecommercebackend.exceptions.UserNotFoundException;
import com.michaelakamihe.ecommercebackend.model.User;
import com.michaelakamihe.ecommercebackend.repo.UserRepository;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getUsers () {
        return repo.findAll();
    }

    public User getUser (Long id) {
        return repo.findById(id).orElseThrow(() ->
                new UserNotFoundException("User by id " + id + " was not found."));
    }

    public User updateUser (Long id, User user) {
        User oldUser = getUser(id);

        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setAddress(user.getAddress());
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());

        return repo.save(oldUser);
    }

    public void deleteUser (Long id) {
        repo.deleteById(id);
    }
}
