package org.example.service;

import org.example.dao.MilestonesDAO;
import org.example.dao.TasksDAO;
import org.example.models.Milestones;

import java.sql.SQLException;
import java.util.List;

import static org.example.dao.UserDAO.connection;

public class MilestonesService {
    private final MilestonesDAO milestoneDAO;


    public MilestonesService(MilestonesDAO dao){
        this.milestoneDAO=dao;
    }

    public MilestonesService() {
        this.milestoneDAO = new MilestonesDAO(connection);
    }

    public void addMilestone(Milestones milestone) {
        try {
            milestoneDAO.addMilestone(milestone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateMilestone(Milestones milestone) {
        try {
            milestoneDAO.updateMilestone(milestone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteMilestone(int milestoneId) {
        try {
            milestoneDAO.deleteMilestone(milestoneId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Milestones getMilestoneById(int milestoneId) {
        try {
            return milestoneDAO.getMilestone(milestoneId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Milestones> getAllMilestones() {
        try {
            return milestoneDAO.getAllMilestones();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getProjectIdByName(String projectName) {
        try {
            return milestoneDAO.getProjectIdByName(projectName);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return -1;
    }

}
