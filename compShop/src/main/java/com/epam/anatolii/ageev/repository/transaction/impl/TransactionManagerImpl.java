package com.epam.anatolii.ageev.repository.transaction.impl;

import com.epam.anatolii.ageev.exeptions.DBException;
import com.epam.anatolii.ageev.repository.db.DataSourceManager;
import com.epam.anatolii.ageev.repository.db.JdbcConnectionHolder;
import com.epam.anatolii.ageev.repository.transaction.RepositoryExecutor;
import com.epam.anatolii.ageev.repository.transaction.TransactionManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager {
    private static final Logger LOG = Logger.getLogger(TransactionManagerImpl.class);
    DataSource dataSource;

    public TransactionManagerImpl(){
        LOG.debug("TransactionManagerImpl constructor");
        try {
            this.dataSource = DataSourceManager.getDataSource();
        } catch (DBException e) {
            LOG.error("TransactionManagerImpl constractor error --> " + e);
            e.printStackTrace();
        }
    }

    @Override
    public <T> T executeDmlTransaction(RepositoryExecutor<T> repositoryExecutor) {
        Connection connection = null;
        T result = null;
       try{
           connection = dataSource.getConnection();
           JdbcConnectionHolder.setConnection(connection);
           result = (T) repositoryExecutor.execute();
           connection.commit();
       } catch (SQLException throwables) {
           rollback(connection);
           LOG.error("TransactionManagerImpl method executeDmlTransaction() error --> " + throwables);
       }
       finally {
           closeConnection(connection);
       }
       return result;
    }

    @Override
    public <T> T executeDqlTransaction(RepositoryExecutor<T> repositoryExecutor) {
        Connection connection = null;
        T result = null;
        try{
            connection = dataSource.getConnection();
            JdbcConnectionHolder.setConnection(connection);
            result = (T) repositoryExecutor.execute();
        } catch (SQLException throwables) {
            rollback(connection);
            LOG.error("TransactionManagerImpl method executeDqlTransaction() error --> " + throwables);
        }
        finally {
            closeConnection(connection);
        }
        return result;
    }

    /**
     * Rollbacks a connection.
     *
     * @param con
     * Connection to be rollbacked.
     */
    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    /**
     * Closes a connection and remove it from ThreadLocal
     *
     * @param connection
     * Connection to be closed.
     */
    private void closeConnection(Connection connection) {
        JdbcConnectionHolder.removeConnection();
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
