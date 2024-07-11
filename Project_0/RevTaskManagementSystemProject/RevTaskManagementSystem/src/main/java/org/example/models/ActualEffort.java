package org.example.models;

import java.sql.Date;

public class ActualEffort {
    private int project_id;
    private Date project_end_date;
    private int actual_effort;


    public Date getProject_end_date() {
        return project_end_date;
    }

    public void setProject_end_date(Date project_end_date) {
        this.project_end_date = project_end_date;
    }

    public int getActual_effort() {
        return actual_effort;
    }

    public void setActual_effort(int actual_effort) {
        this.actual_effort = actual_effort;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}
