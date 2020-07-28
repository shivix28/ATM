package com.example.ATMBackend.service;

import com.example.ATMBackend.dao.UserDao;
import com.example.ATMBackend.model.ATMNotes;
import com.example.ATMBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("UserDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user){
        return userDao.insertUser(user);
    }

    public int getTotalAmount(){
        return userDao.getTotalAmount();
    }

    public List<User> getAllUsers(){
        return userDao.selectAllUsers();
    }

    public Optional<User> getUserByID( String id){
        return userDao.selectUserById(id);
    }

    public User updateUserData(String id, String pin, int amount, String type){
        User tempUser = userDao.findUserByID(id);

        if(tempUser.getPIN().equals(pin)){
            if(type.equalsIgnoreCase("deposit")){
                int newAmount = amount + tempUser.getAmount();
                tempUser.setAmount(newAmount);
            } else if (type.equalsIgnoreCase("withdraw")){
                int newAmount = tempUser.getAmount()-amount;
                tempUser.setAmount(newAmount);
            }
        }

        return userDao.updateUserById(tempUser);

    }

    public ATMNotes withdrawCash(String id, String pin, int amount) {
        User tempUser = userDao.findUserByID(id);

        if(tempUser.getPIN().equals(pin)){
            if(amount>20000){
                return new ATMNotes();
            }
            int newAmount = tempUser.getAmount()-amount;
            tempUser.setAmount(newAmount);
            userDao.updateUserById(tempUser);
            ATMNotes notes = new ATMNotes();
            notes.twoThousand = amount/2000;
            amount = amount%2000;
            notes.oneThousand = amount/1000;
            amount = amount%1000;
            notes.fiveHundred = amount/500;
            amount = amount%500;
            notes.oneHundred = amount/100;
            notes.amountLeftInAccount = newAmount;
            return notes;

        }
        return new ATMNotes();



    }


}
