package org.example.dao;

import org.example.connection.DBConnection;
import org.example.models.UserModels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserDAO.class);
//    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());
    public static Connection connection;

    public UserDAO(DBConnection dbConnection) {
        connection = DBConnection.getConnection();
    }

    public UserDAO() {
        connection = DBConnection.getConnection();
    }

    public boolean registerUser(UserModels.User user) {
        String query = "INSERT INTO user (user_name, email, phone, password, user_role, status) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getUser_name());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getUser_role().name());
            pstmt.setString(6, user.getStatus().name());
            int rowsAffected = pstmt.executeUpdate();
            boolean result = rowsAffected > 0;
            logger.info("User registration result: " + result);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to register user");
            return false;
        }
    }

    public List<UserModels.User> fetchUsers() {
        List<UserModels.User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                UserModels.User user = new UserModels.User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setUser_role(UserModels.UserRole.valueOf(resultSet.getString("user_role")));
                user.setStatus(UserModels.Status.valueOf(resultSet.getString("status")));
                users.add(user);
            }
            logger.info("Fetched users: " + users.size());
        } catch (SQLException e) {
            logger.error("Failed to fetch users");
        }
        return users;
    }

    public boolean updateUser(UserModels.User user) {
        String query = "UPDATE user SET user_name =?, email =?, phone =?, password =?, user_role =?, status =? WHERE user_id =?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getUser_name());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getUser_role().name());
            pstmt.setString(6, user.getStatus().name());
            pstmt.setInt(7, user.getUser_id());
            int rowsAffected = pstmt.executeUpdate();
            boolean result = rowsAffected > 0;
            logger.info("User update result: " + result);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to update user");
            return false;
        }
    }

    public static UserModels.User getUser(String user_name) {
        UserModels.User user = null;
        String query = "SELECT * FROM user WHERE user_name =?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user_name);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                user = new UserModels.User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setUser_role(UserModels.UserRole.valueOf(resultSet.getString("user_role")));
                user.setStatus(UserModels.Status.valueOf(resultSet.getString("status")));
            }
            logger.info("Fetched user: " + (user != null ? user.getUser_name() : "null"));
        } catch (SQLException e) {
            logger.error("Failed to get user");
        }
        return user;
    }

    public boolean deleteUser(int user_id) {
        String query = "DELETE FROM user WHERE user_id =?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, user_id);
            int rowsAffected = pstmt.executeUpdate();
            boolean result = rowsAffected > 0;
            logger.info("User deletion result: " + result);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to delete user");
            return false;
        }
    }

    public UserModels.User login(String user_name, String password) {
        UserModels.User user = null;
        String query = "SELECT * FROM user WHERE user_name =? AND password =?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user_name);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                user = new UserModels.User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setUser_role(UserModels.UserRole.valueOf(resultSet.getString("user_role")));
                user.setStatus(UserModels.Status.valueOf(resultSet.getString("status")));
            }
            logger.info("Login result: " + (user != null ? "Success" : "Failure"));
        } catch (SQLException ex) {
            logger.error("Failed to login");
        }
        return user;
    }
}