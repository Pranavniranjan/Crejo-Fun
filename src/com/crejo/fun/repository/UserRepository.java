package com.crejo.fun.repository;

import com.crejo.fun.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static List<User> users = new ArrayList<>();

    public User save(User user){
        users.add(user);
        return user;
    }

    public List<User> getAllUsers(){
        return users;
    }
}
