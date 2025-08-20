package com.quiz.dao;

import com.quiz.model.Profile;
import com.quiz.model.User;
import com.quiz.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    /**
     * Inserts a new user into the 'users' table.
     * @param user The User object containing username, password, and email.
     */
    public void registerUser(User user) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates a user's credentials against the 'users' table.
     * @param username The username to check.
     * @param password The password to check.
     * @return A User object if the credentials are valid, otherwise null.
     */
    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                return user;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a user's profile information by joining the 'users' and 'profiles' tables.
     * @param userId The ID of the user whose profile is to be fetched.
     * @return A Profile object containing the user's details.
     */
    public Profile getUserProfile(int userId) {
        Profile profile = null;
        String sql = "SELECT p.full_name, p.bio " +
                     "FROM users u LEFT JOIN profiles p ON u.id = p.user_id " +
                     "WHERE u.id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                profile = new Profile();
                profile.setUserId(userId);
                profile.setFullName(rs.getString("full_name"));
                profile.setBio(rs.getString("bio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    /**
     * Inserts a new profile or updates an existing one using an ON DUPLICATE KEY UPDATE query.
     * @param profile The Profile object containing the user_id, full_name, and bio.
     */
    public void updateUserProfile(Profile profile) {
        String sql = "INSERT INTO profiles (user_id, full_name, bio) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE full_name = VALUES(full_name), bio = VALUES(bio)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, profile.getUserId());
            stmt.setString(2, profile.getFullName());
            stmt.setString(3, profile.getBio());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}