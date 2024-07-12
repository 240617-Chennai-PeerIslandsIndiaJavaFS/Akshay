package org.example.models;

import java.sql.Timestamp;
import java.sql.Date;

public class Tasks {
    private int task_id;
    private String task_name;
    private String task_description;
    private Date start_date;
    private Date end_date;
    private int project_id;
    private int assigned_to;
    private TaskStatus taskStatus;

    public Tasks() {

    }

    public enum TaskStatus {
        Started, In_Progress, Completed, Paused,Terminated
    }

    public Tasks(int task_id, String task_name, String task_description, Date start_date, Date end_date, int project_id, int assigned_to, TaskStatus taskStatus) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_description = task_description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.project_id = project_id;
        this.assigned_to = assigned_to;
        this.taskStatus = taskStatus;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(int assigned_to) {
        this.assigned_to = assigned_to;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
