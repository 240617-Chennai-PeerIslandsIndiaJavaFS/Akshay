package org.example.service;

import org.example.dao.ProjectsDAO;
import org.example.models.Projects;

import java.sql.SQLException;
import java.util.List;

import static org.example.dao.UserDAO.connection;


public class ProjectsService {private ProjectsDAO projectDAO;

    public ProjectsService() {
        this.projectDAO = new ProjectsDAO(connection);
    }

    public void addProject(Projects project) throws SQLException {
        projectDAO.addProject(project);
    }
    public void updateProject(Projects project) throws SQLException {
        projectDAO.updateProject(project);
    }
    public List<Projects> getAllProjects() throws SQLException {
        return projectDAO.getAllProjects();
    }
    public void deleteProject(int projectId) throws SQLException {
        projectDAO.deleteProject(projectId);
    }

    public Projects getProjectById(int projectId) throws SQLException {
        return projectDAO.getProject(projectId);
    }

    public Projects getProjectByName(String projectName) throws SQLException {
        return projectDAO.getProjectByName(projectName);
    }

    public List<Projects> getProjectsByTeamMember(int memberId) throws SQLException {
        return projectDAO.getProjectsByTeamMember(memberId);
    }
}
