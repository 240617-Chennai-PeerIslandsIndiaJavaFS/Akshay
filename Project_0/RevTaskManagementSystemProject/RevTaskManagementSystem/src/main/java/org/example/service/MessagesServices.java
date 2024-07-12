package org.example.service;

import org.example.dao.MessagesDAO;
import org.example.models.Messages;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MessagesServices {
    private MessagesDAO messagesDAO=new MessagesDAO();

    public MessagesServices() {
        this.messagesDAO = new MessagesDAO();
    }

    public void sendMessage(int senderId, int receiverId, String content) throws SQLException {
        Messages message = new Messages();
        message.setMessage_content(content);
        message.setReceiver_id(receiverId);
        message.setSender_id(senderId);
        message.setTimestamp(LocalDateTime.now());

        messagesDAO.sendMessage(message);
    }

    public List<Messages> getMessagesForReceiver(int receiverId) {
        return messagesDAO.getMessagesByReceiverId(receiverId);
    }

    public List<Messages> getMessagesForSender(int senderId) {
        return messagesDAO.getMessagesBySenderId(senderId);
    }

    public void associateToManager(int associateId, int managerId, String content) throws SQLException {
        sendMessage(associateId, managerId, content);
    }
}
