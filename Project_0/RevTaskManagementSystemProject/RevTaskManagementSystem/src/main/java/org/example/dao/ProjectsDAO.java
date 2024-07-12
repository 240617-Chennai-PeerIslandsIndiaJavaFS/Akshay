package org.example.dao;

import org.example.models.Projects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectsDAO {
    private Connection connection;

    public ProjectsDAO(Connection connection) {
        this.connection = connection;
    }

    public void addProject(Projects project) throws SQLException {
        String sql = "INSERT INTO Projects (Project_id, Project_name, Client_id, Team_id, Start_date,Date_of_finish, Project_desc,Status_of_project) VALUES (?, ?, ?, ?,?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,project.getProject_id());
            statement.setString(2, project.getProject_name());
            statement.setInt(3, project.getClient_id());
            statement.setInt(4, project.getTeam_id());
            statement.setDate(5, project.getStart_date());
            statement.setDate(6, project.getDate_of_finish());
            statement.setString(7, project.getProject_desc());
            statement.setString(8, project.getStatus_of_project().toString());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        project.setProject_id(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }
    public void updateProject(Projects project) throws SQLException {
        String sql = "UPDATE Projects SET Project_name = ?, Client_id = ?, Team_id = ?, Start_date = ?, Date_of_finish = ?, Project_desc = ?, Status_of_project = ? WHERE Project_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, project.getProject_name());
            statement.setInt(2, project.getClient_id());
            statement.setInt(3, project.getTeam_id());
            statement.setDate(4, project.getStart_date());
            statement.setDate(5, project.getDate_of_finish());
            statement.setString(6, project.getProject_desc());
            statement.setString(7, project.getStatus_of_project().toString());
            statement.setInt(8, project.getProject_id());


            statement.executeUpdate();
        }
    }
    public List<Projects> getAllProjects() throws SQLException {
        List<Projects> projects = new ArrayList<>();
        String sql = "SELECT * FROM Projects";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                projects.add(mapRowToProject(resultSet));
            }
        }
        return projects;
    }
    public void deleteProject(int projectId) throws SQLException {
        String sql = "DELETE FROM Projects WHERE Project_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, projectId);
            statement.executeUpdate();
        }
    }

    public Projects getProject(int projectId) throws SQLException {
        String sql = "SELECT * FROM Projects WHERE project_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, projectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToProject(resultSet);
                }
            }
        }
        return null;
    }

    private Projects mapRowToProject(ResultSet resultSet) throws SQLException {
        Projects project = new Projects();
        project.setProject_id(resultSet.getInt("Project_id"));
        project.setProject_name(resultSet.getString("Project_name"));
        project.setClient_id(resultSet.getInt("Client_id"));
        project.setTeam_id(resultSet.getInt("Team_id"));
        project.setStart_date(resultSet.getDate("Start_date"));
        project.setDate_of_finish(resultSet.getDate("Date_of_finish"));
        project.setProject_desc(resultSet.getString("project_desc"));
        project.setStatus_of_project(Projects.ProjectStatus.fromString(resultSet.getString("Status_of_project")));
        return project;
    }
    public Projects getProjectByName(String projectName) {
        String query = "SELECT * FROM projects WHERE Project_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, projectName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Projects project = new Projects();
                project.setProject_id(rs.getInt("Project_id"));
                project.setProject_name(rs.getString("Project_name"));
                project.setClient_id(rs.getInt("Client_id"));
                project.setTeam_id(rs.getInt("Team_id"));
                project.setStart_date(rs.getDate("Start_date"));
                project.setDate_of_finish(rs.getDate("Date_of_finish"));
                project.setProject_desc(rs.getString("Project_desc"));
                project.setStatus_of_project(Projects.ProjectStatus.valueOf(rs.getString("Status_of_project")));
                return project;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Projects> getProjectsByTeamMember(int memberId) throws SQLException {
        List<Projects> projects = new ArrayList<>();
        String query = "SELECT p.* FROM Projects p " +
                "JOIN ProjectTeamMembers ptm ON p.team_id = ptm.team_id " +
                "WHERE ptm.member_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, memberId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Projects project = new Projects();
                project.setProject_id(resultSet.getInt("Project_id"));
                project.setProject_name(resultSet.getString("Project_name"));
                project.setClient_id(resultSet.getInt("Client_id"));
                project.setTeam_id(resultSet.getInt("Team_id"));
                project.setStart_date(resultSet.getDate("Start_date"));
                project.setDate_of_finish(resultSet.getDate("Date_of_finish"));
                project.setProject_desc(resultSet.getString("project_desc"));
                project.setStatus_of_project(Projects.ProjectStatus.valueOf(resultSet.getString("Status_of_project")));
                projects.add(project);
            }
        }
        return projects;
    }
}


