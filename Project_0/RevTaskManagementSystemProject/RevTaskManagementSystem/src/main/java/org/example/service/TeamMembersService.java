package org.example.service;

import org.example.dao.ProjectTeamMembersDAO;
import org.example.models.TeamMembers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamMembersService {
    public ProjectTeamMembersDAO projectTeamMembersDAO;

    public TeamMembersService() {
        this.projectTeamMembersDAO = new ProjectTeamMembersDAO();
    }

    // Method to add a member to a team by name
    public void addMemberToTeam(String memberName, String teamName) {
        try {
            projectTeamMembersDAO.addMemberByName(memberName, teamName);
            System.out.println("Member added successfully: " + memberName + " to team: " + teamName);
        } catch (SQLException e) {
            System.err.println("Error adding member: " + e.getMessage());
        }
    }

    // Method to remove a member from a team by name
    public void removeMemberFromTeam(String memberName) {
        try {
            projectTeamMembersDAO.removeMemberByName(memberName);
            System.out.println("Member removed successfully: " + memberName);
        } catch (SQLException e) {
            System.err.println("Error removing member: " + e.getMessage());
        }
    }

    // Method to check if a member exists by name
    public boolean isMemberByName(String memberName) {
        try {
            TeamMembers member = projectTeamMembersDAO.getMemberByName(memberName);
            return member != null; // Returns true if member exists, false otherwise
        } catch (SQLException e) {
            System.err.println("Error checking member: " + e.getMessage());
            return false; // Return false in case of exception or no member found
        }
    }

    public TeamMembers getMemberByName(String memberName) {
        try {
            return projectTeamMembersDAO.getMemberByName(memberName);
        } catch (SQLException e) {
            System.err.println("Error retrieving member: " + e.getMessage());
            return null;
        }
    }
    public List<String> getAllMembersForTeam(String teamName) {
        List<String> members = new ArrayList<>();
        try {
            int teamId = projectTeamMembersDAO.getTeamIdByName(teamName);
            members = projectTeamMembersDAO.getAllMembersByTeamId(teamId);
        } catch (SQLException e) {
            System.err.println("Error retrieving members for team " + teamName + ": " + e.getMessage());
        }
        return members;
    }
}
