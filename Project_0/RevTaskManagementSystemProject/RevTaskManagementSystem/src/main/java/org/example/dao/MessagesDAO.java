package org.example.dao;

import org.example.connection.DBConnection;
import org.example.models.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAO {
    private Connection connection;

    public MessagesDAO() {
        this.connection = DBConnection.getConnection();
    }

    public void sendMessage(Messages message) throws SQLException {
        String query = "INSERT INTO messages (message_content, receiver_id, sender_id, timestamp) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, message.getMessage_content());
            pst.setInt(2, message.getReceiver_id());
            pst.setInt(3, message.getSender_id());
            pst.setTimestamp(4, Timestamp.valueOf(message.getTimestamp()));
            pst.executeUpdate();
        }
    }




    public List<Messages> getMessagesByReceiverId(int receiver_id) {
        List<Messages> messagesList = new ArrayList<>();
        String query = "SELECT * FROM messages WHERE receiver_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, receiver_id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Messages message = new Messages(
                            rs.getInt("message_id"),
                            rs.getString("message_content"),
                            rs.getInt("receiver_id"),
                            rs.getInt("sender_id"),
                            rs.getTimestamp("timestamp").toLocalDateTime()
                    );
                    messagesList.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messagesList;
    }

    public List<Messages> getMessagesBySenderId(int sender_id) {
        List<Messages> messagesList = new ArrayList<>();
        String query = "SELECT * FROM messages WHERE sender_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, sender_id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Messages message = new Messages(
                            rs.getInt("message_id"),
                            rs.getString("message_content"),
                            rs.getInt("receiver_id"),
                            rs.getInt("sender_id"),
                            rs.getTimestamp("timestamp").toLocalDateTime()
                    );
                    messagesList.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messagesList;
    }

}
