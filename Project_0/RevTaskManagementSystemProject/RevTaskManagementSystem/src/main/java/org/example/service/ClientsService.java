package org.example.service;

import org.example.dao.ClientsDAO;
import org.example.models.Clients;

import java.sql.SQLException;
import java.util.List;

public class ClientsService {
    private ClientsDAO clientsDAO;



    public ClientsService(ClientsDAO clientsDAO) {
        this.clientsDAO = clientsDAO;
    }

    public ClientsService() {
        this.clientsDAO = new ClientsDAO();
    }

    public void addClient(Clients client) {
        try {
            clientsDAO.addClient(client);
            System.out.println("Client added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding client: " + e.getMessage());
        }
    }

    public Clients getClientByName(String clientName) {
        try {
            return clientsDAO.getClientByName(clientName);
        } catch (SQLException e) {
            System.err.println("Error retrieving client: " + e.getMessage());
            return null;
        }
    }
    public Clients getClientById(int clientId) throws SQLException {
        // Add any business logic here if needed
        return clientsDAO.getClientById(clientId);
    }

    public List<Clients> getAllClients() {
        try {
            return clientsDAO.getAllClients();
        } catch (SQLException e) {
            System.err.println("Error retrieving clients: " + e.getMessage());
            return null;
        }
    }

    public void updateClient(Clients client) {
        try {
            clientsDAO.updateClient(client);
            System.out.println("Client updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating client: " + e.getMessage());
        }
    }

    public void deleteClient(String clientName) {
        try {
            clientsDAO.deleteClient(clientName);
            System.out.println("Client deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting client: " + e.getMessage());
        }
    }
}
