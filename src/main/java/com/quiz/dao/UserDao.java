package com.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.quiz.model.User;
import com.quiz.util.DBConnection;

public class UserDao {

	// Add this method INSIDE the UserDao class

	public User validateUser(String username, String password) {
	    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, username);
	        stmt.setString(2, password);
	        ResultSet rs = stmt.executeQuery();
	        
	        // If a user is found, create a User object
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
	    // Return null if no user is found or if there's an error
	    return null;
	}
    // Method to register a new user
    public void registerUser(User user) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // In a real app, HASH this password!
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}