package org.example.dao;

import org.example.connection.DBConnection;
import org.example.models.Tasks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TasksDAO {
    public boolean addTask(Tasks task) {
        String query = "INSERT INTO tasks (task_name, task_description,start_date,end_date, project_id, assigned_to, task_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, task.getTask_name());
            preparedStatement.setString(2, task.getTask_description());
            preparedStatement.setDate(3, task.getStart_date());
            preparedStatement.setDate(4, task.getEnd_date());
            preparedStatement.setInt(5, task.getProject_id());
            preparedStatement.setInt(6, task.getAssigned_to());
            preparedStatement.setString(7, task.getTaskStatus().name());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateTask(Tasks task) {
        String query = "UPDATE tasks SET task_name = ?, task_description = ?, start_date = ?, end_date = ?, project_id = ?, assigned_to = ?, task_status = ? WHERE task_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, task.getTask_name());
            preparedStatement.setString(2, task.getTask_description());
            preparedStatement.setDate(3, task.getStart_date());
            preparedStatement.setDate(4, task.getEnd_date());
            preparedStatement.setInt(5, task.getProject_id());
            preparedStatement.setInt(6, task.getAssigned_to());
            preparedStatement.setString(7, task.getTaskStatus().name());
            preparedStatement.setInt(8, task.getTask_id());


            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteTask(int taskId) {
        String query = "DELETE FROM tasks WHERE task_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, taskId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Tasks> getAllTasks() {
        List<Tasks> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Tasks task = mapResultSetToTask(resultSet);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    public List<Tasks> getTasksUsingProjectId(int projectId) {
        List<Tasks> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE project_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, projectId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Tasks task = mapResultSetToTask(resultSet);
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public Tasks getTaskById(int taskId) {
        String query = "SELECT * FROM tasks WHERE task_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, taskId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToTask(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Tasks getTaskByName(String taskName) {
        String query = "SELECT * FROM tasks WHERE task_name = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, taskName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToTask(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Tasks mapResultSetToTask(ResultSet resultSet) throws SQLException {
        Tasks task = new Tasks();
        task.setTask_id(resultSet.getInt("Task_id"));
        task.setTask_name(resultSet.getString("Task_name"));
        task.setTask_description(resultSet.getString("Task_description"));
        task.setStart_date(resultSet.getDate("start_date"));
        task.setEnd_date(resultSet.getDate("end_date"));
        task.setProject_id(resultSet.getInt("Project_id"));
        task.setAssigned_to(resultSet.getInt("Assigned_to"));
        task.setTaskStatus(Tasks.TaskStatus.valueOf(resultSet.getString("task_status")));
        return task;
    }

}
