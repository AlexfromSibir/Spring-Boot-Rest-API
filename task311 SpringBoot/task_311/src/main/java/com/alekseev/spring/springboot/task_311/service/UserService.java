package com.alekseev.spring.springboot.task_311.service;


import com.alekseev.spring.springboot.task_311.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User readUser(long id);

    User deleteUser(long parseUnsignedInt);

    void createOrUpdateUser(User user);
}
