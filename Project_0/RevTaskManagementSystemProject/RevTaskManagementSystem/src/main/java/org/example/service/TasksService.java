package org.example.service;

import org.example.dao.TasksDAO;
import org.example.models.Tasks;
import java.sql.SQLException;
import java.util.List;

public class TasksService {
    private TasksDAO taskDAO;

    public TasksService() {
        this.taskDAO = new TasksDAO();
    }

    public boolean addTask(Tasks task) throws SQLException {
        return taskDAO.addTask(task);
    }
    public boolean updateTask(Tasks task) throws SQLException {
        return taskDAO.updateTask(task);
    }
    public boolean deleteTask(int taskId) throws SQLException {
        return taskDAO.deleteTask(taskId);
    }
    public List<Tasks> getAllTasks() throws SQLException {
        return taskDAO.getAllTasks();
    }

    public Tasks getTaskById(int taskId) throws SQLException {
        return taskDAO.getTaskById(taskId);
    }

    public Tasks getTaskByName(String taskName) throws SQLException {
        return taskDAO.getTaskByName(taskName);
    }
    public List<Tasks> getTasksUsingProjectId(int projectId) throws SQLException {
        return taskDAO.getTasksUsingProjectId(projectId);
    }

}
