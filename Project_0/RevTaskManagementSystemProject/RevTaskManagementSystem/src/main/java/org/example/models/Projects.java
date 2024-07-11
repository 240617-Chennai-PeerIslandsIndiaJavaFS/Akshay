package org.example.models;

import java.sql.Date;

public class Projects {
    private int project_id;
    private String project_name;
    private int client_id;
    private int team_id;
    private Date start_date;
    private Date date_of_finish;
    private String project_desc;
    private ProjectStatus status_of_project;


    public enum ProjectStatus {
        Assigned,
        In_Progress,
        Completed;

        public static ProjectStatus fromString(String status) {
            try {
                return ProjectStatus.valueOf(status.replace("", " "));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("there is no such option❌ " + status);
            }
        }
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getDate_of_finish() {
        return date_of_finish;
    }

    public void setDate_of_finish(Date date_of_finish) {
        this.date_of_finish = date_of_finish;
    }

    public String getProject_desc() {
        return project_desc;
    }

    public void setProject_desc(String project_desc) {
        this.project_desc = project_desc;
    }

    public ProjectStatus getStatus_of_project() {
        return status_of_project;
    }

    public void setStatus_of_project(ProjectStatus status_of_project) {
        this.status_of_project = status_of_project;
    }
}