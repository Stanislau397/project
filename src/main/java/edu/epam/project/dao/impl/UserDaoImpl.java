package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.dao.UserDao;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    public static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public boolean register(User user, String password) throws DaoException {
        boolean isRegistered;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_USER)) {
            statement.setLong(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, password);
            statement.setString(4, user.getEmail());
            statement.setString(5, String.valueOf(user.getRole()));
            statement.setBoolean(6, user.isBlocked());
            int result = statement.executeUpdate();
            isRegistered = (result == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRegistered;
    }

    @Override
    public boolean updateEmailAndRoleByUserId(String email, RoleType role, long userId) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ROLE_AND_EMAIL)) {
            statement.setString(1, email);
            statement.setString(2, String.valueOf(role));
            statement.setLong(3, userId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserStatusByUserName(String userName, boolean status) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_STATUS)) {
            statement.setBoolean(1, status);
            statement.setString(2, userName);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean changeUserRoleByUserName(String userName, String role) throws DaoException {
        boolean isRoleUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_ROLE)) {
            statement.setString(1, role);
            statement.setString(2, userName);
            int update = statement.executeUpdate();
            isRoleUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRoleUpdated;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> isFound;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            User user = new User();
            user.setUserName(resultSet.getString(TableColumn.USER_NAME));
            user.setBlocked(resultSet.getBoolean(TableColumn.USER_STATUS));
            user.setRole(RoleType.valueOf(resultSet.getString(TableColumn.USER_ROLE)));
            user.setEmail(resultSet.getString(TableColumn.USER_EMAIL));
            isFound = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }

    @Override
    public int countAmountOfUsers() throws DaoException {
        int amountOfUsers = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.COUNT_USERS);
            if (resultSet.next()) {
                amountOfUsers = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return amountOfUsers;
    }

    @Override
    public long findUserIdByUserName(String userName) throws DaoException {
        long userId = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ID_BY_USER_NAME)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getLong(TableColumn.USER_ID);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return userId;
    }

    @Override
    public Optional<User> findUserByUserName(String userName) throws DaoException {
        Optional<User> isFound = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_USER_NAME)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString(TableColumn.USER_NAME));
                user.setEmail(resultSet.getString(TableColumn.USER_EMAIL));
                user.setUserId(resultSet.getLong(TableColumn.USER_ID));
                user.setRole(RoleType.valueOf(resultSet.getString(TableColumn.USER_ROLE)));
                user.setBlocked(resultSet.getBoolean(TableColumn.USER_STATUS));
                isFound = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return isFound;
    }

    @Override
    public boolean changePassword(User user, String password, String newPassword, String confirmNewPassword) throws DaoException {
        boolean isPasswordChanged;
        String userName = user.getUserName();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHANGE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setString(2, userName);
            statement.setString(3, password);

            int update = statement.executeUpdate();
            isPasswordChanged = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isPasswordChanged;
    }

    @Override
    public boolean changeUserName(String userName, String newUserName) throws DaoException {
        boolean isUserNameChanged;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHANGE_USER_NAME)) {
            statement.setString(1, newUserName);
            statement.setString(2, userName);
            int update = statement.executeUpdate();
            isUserNameChanged = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUserNameChanged;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_USERS);
            while (resultSet.next()) {
                long id = resultSet.getLong(TableColumn.USER_ID);
                String login = resultSet.getString(TableColumn.USER_NAME);
                String email = resultSet.getString(TableColumn.USER_EMAIL);
                String role = resultSet.getString(TableColumn.USER_ROLE);
                boolean status = resultSet.getBoolean(TableColumn.USER_STATUS);
                User user = new User(id, RoleType.valueOf(role), login, email, status);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return allUsers;
    }

    @Override
    public List<User> findLatestRegisteredUsers() throws DaoException {
        List<User> latestUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_LATEST_USERS);
            while (resultSet.next()) {
                long id = resultSet.getLong(TableColumn.USER_ID);
                String login = resultSet.getString(TableColumn.USER_NAME);
                String email = resultSet.getString(TableColumn.USER_EMAIL);
                String role = resultSet.getString(TableColumn.USER_ROLE);
                boolean status = resultSet.getBoolean(TableColumn.USER_STATUS);
                User user = new User(id, RoleType.valueOf(role), login, email, status);
                latestUsers.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return latestUsers;
    }
}
