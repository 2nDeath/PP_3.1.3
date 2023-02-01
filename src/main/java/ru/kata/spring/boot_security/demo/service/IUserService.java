package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface IUserService {
    public List<User> getAllUsers();
    public User getUser(long n);
    public void saveUser(User user);
    public void deleteUser(long id);
}
