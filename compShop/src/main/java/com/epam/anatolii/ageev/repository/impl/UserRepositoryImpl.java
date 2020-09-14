package com.epam.anatolii.ageev.repository.impl;

import com.epam.anatolii.ageev.domain.User;
import com.epam.anatolii.ageev.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    private Map<String, User> userDb;

    public UserRepositoryImpl() {
        this.userDb = new HashMap<>();
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userDb.values();
    }

    @Override
    public User getOne(String login) {
        return userDb.get(login);
    }

    @Override
    public void deleteUser(String login) {
        userDb.remove(login);
    }

    @Override
    public User createUser(User user) {
        return userDb.put(user.getLogin(), user);
    }

    @Override
    public User updateUser(User user) {
        return userDb.put(user.getLogin(), user);
    }

    @Override
    public boolean checkUserExistInDb(String login) {
        return userDb.containsKey(login);
    }
}
