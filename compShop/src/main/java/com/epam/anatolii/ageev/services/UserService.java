package com.epam.anatolii.ageev.services;

import com.epam.anatolii.ageev.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getOne(String login);

    boolean deleteUser(String login);

    User createUser(User user);

    boolean checkUserExistInDb(String login);
}
