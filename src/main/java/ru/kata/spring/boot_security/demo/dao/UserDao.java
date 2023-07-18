package ru.kata.spring.boot_security.demo.dao;





import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User showUser(int id);

    void delete(int id);

    void save(User user);

    void update(int id, User udatedUser);
}
