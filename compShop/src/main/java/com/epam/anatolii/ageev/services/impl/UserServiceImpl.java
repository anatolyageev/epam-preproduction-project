package com.epam.anatolii.ageev.services.impl;

import com.epam.anatolii.ageev.domain.User;
import com.epam.anatolii.ageev.exeptions.ResourceNotFoundException;
import com.epam.anatolii.ageev.repository.UserRepository;
import com.epam.anatolii.ageev.services.UserService;

import java.util.List;
import java.util.Optional;

import static com.epam.anatolii.ageev.constants.Messages.NO_USER_WITH_LOGIN;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getOne(String login) {
        return Optional.ofNullable(userRepository.getOne(login))
                .orElseThrow(()-> new ResourceNotFoundException(NO_USER_WITH_LOGIN + login));
    }

    @Override
    public void deleteUser(String login) {
        getOne(login);
        userRepository.deleteUser(login);
    }

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        getOne(user.getLogin());
        return userRepository.updateUser(user);
    }

    @Override
    public boolean checkUserExistInDb(String login) {
        return userRepository.checkUserExistInDb(login);
    }
}
