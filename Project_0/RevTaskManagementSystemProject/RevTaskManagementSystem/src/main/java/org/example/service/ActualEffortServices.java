package org.example.service;

import org.example.dao.ActualEffortDAO;
import org.example.models.ActualEffort;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ActualEffortServices {
    private ActualEffortDAO actualEffortDAO;

    public ActualEffortServices() throws SQLException {
        this.actualEffortDAO = new ActualEffortDAO();
    }
    public void addActualEffort(ActualEffort actualEffort) throws SQLException {
        actualEffortDAO.addActualEffort(actualEffort);
    }
    public void updateActualEffort(ActualEffort actualEffort) throws SQLException {
        actualEffortDAO.updateActualEffort(actualEffort);
    }

    public ActualEffort calculateEffortUsingProjectId(int projectId) throws SQLException {
        return actualEffortDAO.calculateEffortUsingProjectId(projectId);
    }
    public void updateEffortCalculationEndDate(int projectId, LocalDate endDate) throws SQLException {
        ActualEffort actualEffort = calculateEffortUsingProjectId(projectId);
        if (actualEffort == null) {
            actualEffort = new ActualEffort();
            actualEffort.setProject_id(projectId);
            actualEffort.setProject_end_date(Date.valueOf(endDate));
            addActualEffort(actualEffort);
        } else {
            actualEffort.setProject_end_date(Date.valueOf(endDate));
            updateActualEffort(actualEffort);
        }
    }

    public void close() throws SQLException {
        actualEffortDAO.close();
    }

}
