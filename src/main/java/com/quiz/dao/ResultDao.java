package com.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.quiz.util.DBConnection;

public class ResultDao {
    public void saveResult(int userId, int quizId, int score) {
        String sql = "INSERT INTO results (user_id, quiz_id, score) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, quizId);
            stmt.setInt(3, score);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}