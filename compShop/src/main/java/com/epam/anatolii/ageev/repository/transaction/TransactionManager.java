package com.epam.anatolii.ageev.repository.transaction;

public interface TransactionManager {
    <T> T executeDmlTransaction(RepositoryExecutor<T> repositoryExecutor);

    <T> T executeDqlTransaction(RepositoryExecutor<T> repositoryExecutor);
}
