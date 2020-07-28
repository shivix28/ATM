package com.example.ATMBackend.api;

import com.example.ATMBackend.model.ATMNotes;
import com.example.ATMBackend.model.User;
import com.example.ATMBackend.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping ("api/v1/user/")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "userId")
    public User getUserByID(@RequestParam String id) {
        return userService.getUserByID(id)
                .orElse(null);
    }

    @PutMapping(path = "update")
    public User updateUserData(@RequestParam String id, @RequestParam String pin, @RequestParam int amount, @RequestParam String type) {
         return userService.updateUserData(id, pin, amount, type);

    }

    @GetMapping(path = "withdraw")
    public ATMNotes withdrawMoney(@RequestParam String id, @RequestParam String pin, @RequestParam int amount){
        return userService.withdrawCash(id, pin, amount);
    }

    @GetMapping(path="totalAmount")
    public int getTotalAmount(){
        return userService.getTotalAmount();
    }
}
