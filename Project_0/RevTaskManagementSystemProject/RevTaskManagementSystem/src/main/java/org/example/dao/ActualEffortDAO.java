package org.example.dao;

import org.example.connection.DBConnection;
import org.example.models.ActualEffort;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ActualEffortDAO {
    private DBConnection dbConnection;
    private Connection connection;


    public ActualEffortDAO() throws SQLException {
        this.dbConnection = dbConnection;
        this.connection = dbConnection.getConnection();
    }

    public boolean insertEffortCalculation(ActualEffort actualEffort) throws SQLException {
        String sql = "INSERT INTO EffortCalculation (projectEndDate, actualEffort, projectId) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(actualEffort.getProject_end_date().getTime()));
            statement.setInt(2, actualEffort.getActual_effort());
            statement.setInt(3, actualEffort.getProject_id());

            boolean rowInserted = statement.executeUpdate() > 0;
            return rowInserted;
        }
    }public void addActualEffort(ActualEffort actualEffort) throws SQLException {
        String insertQuery = "INSERT INTO EffortCalculation (Project_end_date, Actual_effort, Project_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setDate(1, actualEffort.getProject_end_date());
            preparedStatement.setInt(2, actualEffort.getActual_effort());
            preparedStatement.setInt(3, actualEffort.getProject_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Effort calculation added successfully.");
            } else {
                System.out.println("Failed to add effort calculation.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to add effort calculation: " + e.getMessage());
            throw e;
        }
    }

    public void updateActualEffort(ActualEffort actualEffort) throws SQLException {
        String updateQuery = "UPDATE ActualEffort SET project_end_date = ?, actual_effort = ? WHERE project_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setDate(1, actualEffort.getProject_end_date());
            preparedStatement.setInt(2, actualEffort.getActual_effort());
            preparedStatement.setInt(3, actualEffort.getProject_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Actual Effort updated ☑️.");
            }
        } catch (SQLException e) {
            System.out.println("Actual effort not updated:❌ " + e.getMessage());
            throw e;
        }
    }


    public ActualEffort calculateEffortUsingProjectId(int projectId) throws SQLException {
        String selectQuery = "SELECT * FROM EffortCalculation WHERE project_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, projectId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ActualEffort actualEffort = new ActualEffort();
                    actualEffort.setProject_id(resultSet.getInt("Project_id"));
                    actualEffort.setProject_end_date(resultSet.getDate("Project_end_date"));
                    actualEffort.setActual_effort(resultSet.getInt("Actual_effort"));
                    return actualEffort;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Unable to calculate actual effort❌ " + e.getMessage());
            throw e;
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
