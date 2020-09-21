package com.epam.anatolii.ageev.services.impl;

import com.epam.anatolii.ageev.domain.User;
import com.epam.anatolii.ageev.exeptions.ItemNotFoundException;
import com.epam.anatolii.ageev.repository.UserRepository;
import com.epam.anatolii.ageev.repository.transaction.TransactionManager;
import com.epam.anatolii.ageev.repository.transaction.impl.TransactionManagerImpl;
import com.epam.anatolii.ageev.services.UserService;

import java.util.List;
import java.util.Optional;

import static com.epam.anatolii.ageev.constants.Messages.NO_USER_WITH_LOGIN;

public class UserServiceDbImpl implements UserService {
    TransactionManager transactionManager;
    private UserRepository userRepository;

    public UserServiceDbImpl(UserRepository userRepository) {
        this.transactionManager = new TransactionManagerImpl();
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getOne(String login) {
       return Optional.ofNullable( transactionManager.executeDqlTransaction(() -> userRepository.getOne(login)))
                .orElseThrow(()-> new ItemNotFoundException(NO_USER_WITH_LOGIN + login));
    }

    @Override
    public boolean deleteUser(String login) {
       return transactionManager.executeDmlTransaction(()->userRepository.deleteUser(login));
    }

    @Override
    public User createUser(User user) {
        return transactionManager.executeDmlTransaction(()->userRepository.createUser(user));
    }

    @Override
    public boolean checkUserExistInDb(String login) {
        return transactionManager.executeDqlTransaction(() -> userRepository.checkUserExistInDb(login));
    }
}
