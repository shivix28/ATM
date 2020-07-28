package com.example.ATMBackend.dao;

import com.example.ATMBackend.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("UserDao")
public class UserDaoService implements UserDao {

    private int totalAmount = 0;
    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(User user) {
        DB.add(new User(user.getUserID(), user.getPIN(), user.getAmount()));
        totalAmount += user.getAmount();
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }



    @Override
    public Optional<User> selectUserById(String ID) {
        return DB.stream()
                .filter(user -> user.getUserID().equals(ID))
                .findFirst();
    }

    @Override
    public User findUserByID(String ID) {
        User tempUser = null;

        for(User user: DB){
            if(user.getUserID().equals(ID)){
                tempUser = user;
                break;
            }
        }

        return tempUser;
    }

    @Override
    public int getTotalAmount() {
        return totalAmount;
    }

    @Override
    public User updateUserById(User u) {
        User tempUser = null;

     for(User user: DB){
         if(user.getUserID().equals(u.getUserID())){
             int delta = u.getAmount() - user.getAmount();
             totalAmount += delta;
             user.setAmount(u.getAmount());
             tempUser = user;
             break;
         }
     }

     return tempUser;


    }

}
