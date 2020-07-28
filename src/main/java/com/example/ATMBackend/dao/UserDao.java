package com.example.ATMBackend.dao;

import com.example.ATMBackend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

   int insertUser(User user);

   List<User> selectAllUsers();

   User updateUserById(User user);

   Optional<User> selectUserById(String ID);

   User findUserByID(String ID);

   int getTotalAmount();


}
