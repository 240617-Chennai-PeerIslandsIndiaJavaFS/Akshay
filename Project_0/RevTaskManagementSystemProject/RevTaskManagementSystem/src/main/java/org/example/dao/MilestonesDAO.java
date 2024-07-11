package org.example.dao;

import org.example.connection.DBConnection;
import org.example.models.Milestones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MilestonesDAO {private final Connection connection;

    public MilestonesDAO(Connection connection) {
        this.connection = connection;
    }
    public void addMilestone(Milestones milestone) throws SQLException {
        String sql = "INSERT INTO milestones (milestone_id, milestone_name, milestone_description, project_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, milestone.getMilestoneId());
            stmt.setString(2, milestone.getMilestoneName().name());
            stmt.setString(3, milestone.getMilestoneDescription());
            stmt.setInt(4, milestone.getProjectId());
            stmt.executeUpdate();
        }
    }
    public void updateMilestone(Milestones milestone) throws SQLException {
        String query = "UPDATE Milestones SET milestone_name = ?, milestone_description = ?, project_id = ? WHERE milestone_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, milestone.getMilestoneName().name());
            statement.setString(2, milestone.getMilestoneDescription());
            statement.setInt(4, milestone.getProjectId());
            statement.setInt(5, milestone.getMilestoneId());
            statement.executeUpdate();
        }
    }
    public void deleteMilestone(int milestoneId) throws SQLException {
        String query = "DELETE FROM Milestones WHERE milestone_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, milestoneId);
            statement.executeUpdate();
        }
    }

    public Milestones getMilestone(int id) throws SQLException {
        String sql = "SELECT * FROM milestones WHERE milestone_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Milestones(
                            rs.getInt("milestone_id"),
                            Milestones.MilestoneName.valueOf(rs.getString("milestone_name")),
                            rs.getString("milestone_description"),
                            rs.getInt("project_id")
                    );
                }
            }
        }
        return null;
    }

    public List<Milestones> getAllMilestones() throws SQLException {
        List<Milestones> milestones = new ArrayList<>();
        String sql = "SELECT * FROM milestones";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                milestones.add(new Milestones(
                        rs.getInt("milestone_id"),
                        Milestones.MilestoneName.valueOf(rs.getString("milestone_name")),
                        rs.getString("milestone_description"),
                        rs.getInt("project_id")
                ));
            }
        }
        return milestones;
    }
    public int getProjectIdByName(String projectName) throws SQLException {
        String query = "SELECT project_id FROM Projects WHERE project_name = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, projectName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("project_id");
            } else {
                throw new SQLException("There is no such project❌ " + projectName);
            }
        }
    }
}
