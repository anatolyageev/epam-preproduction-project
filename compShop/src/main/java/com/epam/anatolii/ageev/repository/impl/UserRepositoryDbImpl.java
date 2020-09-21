package com.epam.anatolii.ageev.repository.impl;

import com.epam.anatolii.ageev.domain.User;
import com.epam.anatolii.ageev.repository.UserRepository;
import com.epam.anatolii.ageev.repository.db.JdbcConnectionHolder;
import com.epam.anatolii.ageev.repository.utils.JdbcUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epam.anatolii.ageev.constants.sql.Fields.*;
import static com.epam.anatolii.ageev.constants.sql.SqlQuery.*;

public class UserRepositoryDbImpl implements UserRepository {
    final static Logger LOG = Logger.getLogger(UserRepositoryDbImpl.class);

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try(Statement st = JdbcConnectionHolder.getConnection().createStatement();
            ResultSet rs = st.executeQuery(SQL_GET_ALL)) {
            while (rs.next()){
                userList.add(userFromResultSet(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getOne(String login) {
        ResultSet rs = null;
        try(PreparedStatement ps = JdbcConnectionHolder.getConnection().prepareStatement(SQL_GEY_ONE)) {
            ps.setString(1,login);
            rs = ps.executeQuery();
            if(rs.next()){
                return userFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JdbcUtils.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public boolean deleteUser(String login) {
        boolean result = false;
        try(PreparedStatement ps = JdbcConnectionHolder.getConnection().prepareStatement(SQL_DELETE_USER)) {
            LOG.debug("Repository method deleteUser() start --> " + login);
            if (ps.executeUpdate()>0){
                result = true;
            }
            LOG.debug("Repository method deleteUser() finish --> " + login);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Repository method deleteUser() error --> " + throwables);
        }
        return result;
    }

    @Override
    public User createUser(User user) {
        ResultSet rs = null;
        try(PreparedStatement ps = JdbcConnectionHolder.getConnection().prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            int index =1;
            ps.setString(index++,user.getLogin());
            ps.setString(index++,user.getFirstName());
            ps.setString(index++,user.getLastName());
            ps.setString(index++,user.getEmail());
            ps.setString(index++,user.getPassword());
            if(ps.executeUpdate()>0){
                rs = ps.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    Long id = rs.getLong(1);
                    user.setId(id);
                }
            }
            LOG.debug("Repository method createUser() --> " + user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JdbcUtils.closeResultSet(rs);
        }
        return user;
    }

    @Override
    public boolean checkUserExistInDb(String login) {
        return !Objects.isNull(getOne(login));
    }

    private User userFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(ENTITY_ID));
        user.setLogin(rs.getString(USER_LOGIN));
        user.setFirstName(rs.getString(USER_FIRST_NAME));
        user.setLastName(rs.getString(USER_LAST_NAME));
        user.setEmail(rs.getString(USER_EMAIL));
        user.setPassword(rs.getString(USER_PASSWORD));
        return user;
    }
}
