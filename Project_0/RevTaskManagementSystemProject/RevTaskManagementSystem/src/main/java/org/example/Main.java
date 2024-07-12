package org.example;

import org.example.controller.ControllerClass;
import org.example.dao.UserDAO;
import org.example.service.*;


import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService(new UserDAO());
        ClientsService clientsService = new ClientsService();
        ProjectTeamsService projectTeamsService = new ProjectTeamsService();
        TeamMembersService projectTeamMembersService= new TeamMembersService();
        ProjectsService projectService= new ProjectsService();
        MilestonesService milestoneService=new MilestonesService();
        ActualEffortServices effortCalculationService= new ActualEffortServices();
        TasksService taskService= new TasksService();
        MessagesServices messagesServices = new MessagesServices();


        ControllerClass userController = new ControllerClass(userService, clientsService, projectTeamsService, projectTeamMembersService, projectService, milestoneService, effortCalculationService, taskService, messagesServices, scanner);

        userController.run();
    }
}