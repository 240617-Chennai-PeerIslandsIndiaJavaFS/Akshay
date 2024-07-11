package org.example.service;

import org.example.dao.ProjectTeamsDAO;
import org.example.models.TeamMembers;
import org.example.models.ProjectTeams;
import org.example.models.UserModels;

import java.util.List;

public class ProjectTeamsService {
    private ProjectTeamsDAO projectTeamsDAO;
    private TeamMembersService projectTeamMembersService;

    public ProjectTeamsService() {
        this.projectTeamsDAO = new ProjectTeamsDAO();
        this.projectTeamMembersService = new TeamMembersService();

    }

    public void addProjectTeam(ProjectTeams projectTeam) {
        projectTeamsDAO.addProjectTeam(projectTeam);
    }

    public ProjectTeams getProjectTeam(String teamName) {
        return projectTeamsDAO.getProjectTeam(teamName);
    }

    public List<ProjectTeams> getAllProjectTeams() {
        return projectTeamsDAO.getAllProjectTeams();
    }

    public void updateProjectTeam(String currentTeamName, ProjectTeams updatedProjectTeam) {
        projectTeamsDAO.updateProjectTeam(currentTeamName, updatedProjectTeam);
    }

    public void deleteProjectTeam(String teamName) {
        projectTeamsDAO.deleteProjectTeam(teamName);
    }
    public void addTeamMemberByName(String teamName, String memberName) {
        // Retrieve the team ID based on the team name
        ProjectTeams team = projectTeamsDAO.getProjectTeam(teamName);
        if (team == null) {
            System.err.println("Team not found: " + teamName);
            return;
        }
        int teamId = team.getTeamId();


        // Retrieve the user based on the member name
        UserModels.User user = UserService.getUser(memberName);
        if (user == null) {
            System.err.println("User not found: " + memberName);
            return;
        }
        int memberId = user.getUser_id();

        // Create a new ProjectTeamMembers object
        TeamMembers teamMember = new TeamMembers(memberId, teamId, memberName);

        // Add the team member to the team
        projectTeamMembersService.addMemberToTeam(memberName, teamName);
    }

}
