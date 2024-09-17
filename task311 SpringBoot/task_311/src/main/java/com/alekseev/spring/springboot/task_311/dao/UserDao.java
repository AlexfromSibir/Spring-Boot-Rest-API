package com.alekseev.spring.springboot.task_311.dao;
import com.alekseev.spring.springboot.task_311.model.User;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    User readUser(long id);

    User deleteUser(long id);
}