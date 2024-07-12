package org.example.controller;

import org.example.models.*;
import org.example.service.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ControllerClass {
    private MessagesServices messagesServices;
    private UserService userService;
    private ClientsService clientsService;
    private ProjectTeamsService projectTeamsService;
    private TeamMembersService projectTeamMembersService;
    private ProjectsService projectService;
    private MilestonesService milestoneService;
    private ActualEffortServices actualEffortService;
    private TasksService taskService=new TasksService();
    private Scanner scanner;
    private UserModels.UserRole userRole;

    public ControllerClass(UserService userService, ClientsService clientsService, ProjectTeamsService projectTeamsService, TeamMembersService projectTeamMembersService, ProjectsService projectService, MilestonesService milestoneService, ActualEffortServices actualEffortService, TasksService taskService, MessagesServices messagesServices, Scanner scanner) throws SQLException {
        this.userService = userService;
        this.clientsService = clientsService;
        this.projectTeamsService = projectTeamsService;
        this.projectTeamMembersService = projectTeamMembersService;
        this.projectService = projectService;
        this.milestoneService = milestoneService;
        this.actualEffortService = actualEffortService;
        this.taskService = taskService;
        this.messagesServices = messagesServices;
        this.scanner = scanner;

        askForRole();
    }

    private void askForRole() throws SQLException {
        System.out.println("|────────────────────────────────────────────────────────────────────|");
        System.out.println("            Welcome to Revature's Task Management System!");
        System.out.println("|────────────────────────────────────────────────────────────────────|");
        System.out.println("You May Now Select your Role");
        System.out.println("1️⃣. Admin");
        System.out.println("2️⃣. Manager");
        System.out.println("3️⃣. Associate");
        System.out.print("Choose your Role: ");
        int userRoleChoice = scanner.nextInt();
        scanner.nextLine();
        switch (userRoleChoice) {
            case 1:
                userRole = UserModels.UserRole.Admin;
                break;
            case 2:
                userRole = UserModels.UserRole.Manager;
                break;
            case 3:
                userRole = UserModels.UserRole.Associate;
                break;
            default:
                System.out.println("Invalid. Please Try again!");
                askForRole();
                return;
        }
        run();
    }

    public void run() throws SQLException {
        while (true) {
            System.out.println("|────────────────────────────────────────────────────────────────────|");
            System.out.println("   TIME TO GET STARTED!");
            System.out.println("|────────────────────────────────────────────────────────────────────|");
            if (userRole == UserModels.UserRole.Admin) {
                System.out.println("1️⃣. Log into your account");
                System.out.println("2️⃣. Go back");
            } else if (userRole == UserModels.UserRole.Manager) {
                System.out.println("1️⃣. Log into your account");
                System.out.println("2️⃣. Go back");
            } else if (userRole == UserModels.UserRole.Associate) {
                System.out.println("1️⃣. Log into your account");
                System.out.println("2️⃣. Go back");
            }
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Please Re-Check Your Role");
                    askForRole();
                    return;
                default:
                    System.out.println("Invalid❌.Please Try again!");
            }
        }
    }

    private void login() throws SQLException {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        UserModels.User user = userService.login(username, password);
        if (user != null) {
            if (user.getUser_role() == UserModels.UserRole.Admin && userRole == UserModels.UserRole.Admin) {
                user.setStatus(UserModels.Status.Active);
                userService.updateUser(user);
                System.out.println("_____________________________________________________");
                System.out.println("           Admin login successful☑️");
                System.out.println("_____________________________________________________");
                adminMenu(user);
            } else if (user.getUser_role() == UserModels.UserRole.Manager && userRole == UserModels.UserRole.Manager) {
                user.setStatus(UserModels.Status.Active);
                userService.updateUser(user);
                System.out.println("_____________________________________________________");
                System.out.println("           Manager login successful☑️");
                System.out.println("_____________________________________________________");
                managerMenu(user);
            } else if (user.getUser_role() == UserModels.UserRole.Associate && userRole == UserModels.UserRole.Associate) {
                user.setStatus(UserModels.Status.Active);
                userService.updateUser(user);
                System.out.println("_____________________________________________________");
                System.out.println("           Associate login successful☑️");
                System.out.println("_____________________________________________________");
                associateMenu(user);
            } else {
                System.out.println("Login permission DENIED❌❌❌!!");
            }
        } else {
            System.out.println("Invalid❌ username/password.Please Re-Check");
        }
    }

    private void adminMenu(UserModels.User user) throws SQLException {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1️⃣. Create a new user");
            System.out.println("2️⃣. Delete an existing user");
            System.out.println("3️⃣. Update existing user details");
            System.out.println("4️⃣. View all the users");
            System.out.println("5️⃣. Logout");
            System.out.print("Your Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    deleteAccount();
                    break;
                case 3:
                    updateProfile();
                    break;
                case 4:
                    viewAllUsers();
                    break;
                case 5:
                    logout(user);
                    return;
                default:
                    System.out.println("Invalid❌.Please Try again!");
            }
        }
    }

    private void managerMenu(UserModels.User user) throws SQLException {
        while (true) {
            System.out.println("Manager Menu:");
            System.out.println("1️⃣. Manage Clients");
            System.out.println("2️⃣. Manage Project Teams");
            System.out.println("3️⃣. Update your password");
            System.out.println("4️⃣ Manage Projects");
            System.out.println("5️⃣. Logout");
            System.out.print("Your Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageClients();
                    break;
                case 2:
                    manageProjectTeams();
                    break;
                case 3:
                    updatePassword(user);
                    break;
                case 4:
                    manageProjects();
                    break;
                case 5:
                    logout(user);
                    return;
                default:
                    System.out.println("Invalid.❌Please Try again!");
            }
        }
    }

    private void associateMenu(UserModels.User user) throws SQLException {
        while (true) {
            System.out.println(" ");
            System.out.println("_____________________________________________________");
            System.out.println("                 ASSOCIATE Menu:");
            System.out.println("_____________________________________________________");
            System.out.println("1️⃣. Update the current Status of your task");
            System.out.println("2️⃣. Check project details of assigned projects");
            System.out.println("3️⃣. Logout");
            System.out.println("4️⃣. Message your Manager");
            System.out.print("Choose your option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    updateTaskStatus(user);
                    break;
                case 2:
                    viewAssignedProjectDetails(user);
                    break;
                case 3:
                    logout(user);
                    return;
                case 4:
                    messageManager(user);
                    break;
                default:
                    System.out.println("Invalid❌.Please Try again!");
            }
        }
    }
    private void messageManager(UserModels.User user) {
        System.out.print("Enter your manager's username: ");
        String managerUsername = scanner.nextLine();
        UserModels.User manager = userService.getUser(managerUsername);

        if (manager == null || manager.getUser_role() != UserModels.UserRole.Manager) {
            System.out.println("Manager not found or the user is not a manager.");
            return;
        }

        System.out.print("Enter your message: ");
        String content = scanner.nextLine();

        try {
            messagesServices.sendMessage(user.getUser_id(), manager.getUser_id(), content);
            System.out.println("Message sent successfully.");
        } catch (Exception e) {
            System.out.println("Failed to send message: " + e.getMessage());
        }
    }


    private void manageClients() {
        while (true) {
            System.out.println("Manage Clients :");
            System.out.println("1️⃣. ADD a new client");
            System.out.println("2️⃣. View all clients");
            System.out.println("3️⃣. Update existing client details");
            System.out.println("4️⃣. Delete an existing client");
            System.out.println("5️⃣. Go back");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createClient();
                    break;
                case 2:
                    deleteClient();
                    break;
                case 3:
                    updateClient();
                    break;
                case 4:
                    viewAllClients();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid❌.Please Try again!");
            }
        }
    }

    private void manageProjectTeams() {
        while (true) {
            System.out.println("Project Teams Menu:");
            System.out.println("1️⃣. View All The Project Teams");
            System.out.println("2️⃣. Add a New Team");
            System.out.println("3️⃣. Update an existing Team");
            System.out.println("4️⃣. Delete an existing Team");
            System.out.println("5️⃣. Go back");
            System.out.print("Your Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllTeams();
                    break;
                case 2:
                    addProjectTeam();
                    break;
                case 3:
                    updateProjectTeam();
                    break;
                case 4:
                    deleteProjectTeam();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid.❌Please Try again!");
            }
        }
    }


    private void addProjectTeam() {
        System.out.print("Enter the team name: ");
        String teamName = scanner.nextLine().trim();
        while (projectTeamsService.getProjectTeam(teamName) != null) {
            System.out.println("Team name already exists.❌ Please enter another name.");
            System.out.print("Enter the team name: ");
            teamName = scanner.nextLine().trim();
        }
        ProjectTeams projectTeam = new ProjectTeams(teamName);
        projectTeamsService.addProjectTeam(projectTeam);
        System.out.println("Team added successfully☑️.");
        System.out.println("Enter associates' names.");
        System.out.println("OR Type return");
        System.out.println("");
        while (true) {
            System.out.print("Enter associate's name: ");
            String memberName = scanner.nextLine().trim();
            if (memberName.equalsIgnoreCase("return")) {
                break;
            }

            UserModels.User user = UserService.getUser(memberName);
            if (user != null && user.getUser_role() == UserModels.UserRole.Associate) {
                if (!projectTeamMembersService.isMemberByName(memberName)) {
                    projectTeamsService.addTeamMemberByName(teamName, memberName);
                } else {
                    System.out.println(memberName + "  ❌Can't be added to the team.");
                }
            } else {
                System.out.println("Either the User doesn't exist or is not an associate. ❌❌Can't be a part of the  team.");
            }
        }
    }



    private void updateProjectTeam() {
        System.out.print("Enter current team name: ");
        String currentTeamName = scanner.nextLine();

        ProjectTeams projectTeam = projectTeamsService.getProjectTeam(currentTeamName);
        if (projectTeam != null) {
            System.out.println("The team you are looking for is here,tell them what to do!");
            System.out.println("1️⃣. Edit Team Name");
            System.out.println("2️⃣. Add Members to Team");
            System.out.println("3️⃣. Remove Members from Team");
            System.out.println("4️⃣. Go back");
            System.out.print("Choose your option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    editTeamName(projectTeam);
                    break;
                case 2:
                    addMembersToTeam(projectTeam);
                    break;
                case 3:
                    removeMembersFromTeam(projectTeam);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.❌ Try again!");
            }
        } else {
            System.out.println("Team not found.");
        }
    }
    private void editTeamName(ProjectTeams projectTeam) {
        System.out.print("Enter a new team name: ");
        String newTeamName = scanner.nextLine();
        String oldTeamName = projectTeam.getTeamName();

        projectTeam.setTeamName(newTeamName);

        projectTeamsService.updateProjectTeam(oldTeamName, projectTeam);
        System.out.println("Team name has been updated successfully☑️.");
    }


    private void addMembersToTeam(ProjectTeams projectTeam) {
        System.out.println("Enter associates' names to add. Enter 'stop' to stop:");

        List<String> currentMembers = projectTeamMembersService.getAllMembersForTeam(projectTeam.getTeamName());

        while (true) {
            System.out.print("Enter associate's name: ");
            String memberName = scanner.nextLine();
            if (memberName.equalsIgnoreCase("stop")) {
                break;
            }


            UserModels.User user = UserService.getUser(memberName);
            if (user != null && user.getUser_role() == UserModels.UserRole.Associate) {
                if (currentMembers.contains(memberName)) {
                    System.out.println(memberName + " is already a member of the team❌.");
                } else {
                    projectTeamsService.addTeamMemberByName(projectTeam.getTeamName(), memberName);
                }
            } else {
                System.out.println("User either doesn't exist or is not an associate. ❌Can't add to the team.");
            }
        }
    }

    private void removeMembersFromTeam(ProjectTeams projectTeam) {
        System.out.println("Enter associates' names to remove. Type 'stop' to stop:");
        while (true) {
            System.out.print("Enter associates's name: ");
            String memberName = scanner.nextLine();
            if (memberName.equalsIgnoreCase("stop")) {
                break;
            }

            UserModels.User user = userService.getUser(memberName);
            if (user != null && projectTeamMembersService.isMemberByName(memberName)) {
                projectTeamMembersService.removeMemberFromTeam(memberName);
            } else {
                System.out.println("Either the user doesn't exist in the team or is not an associate hence can't remove from the team.❌❌");
            }
        }
    }



    private void deleteProjectTeam() {
        System.out.print("Enter the team to be deleted: ");
        String teamName = scanner.nextLine();

        projectTeamsService.deleteProjectTeam(teamName);
        System.out.println("Team has been deleted successfully☑️.");
    }

    private void viewAllTeams() {
        List<ProjectTeams> teams = projectTeamsService.getAllProjectTeams();
        System.out.println("All formed teams:");
        for (ProjectTeams team : teams) {
            System.out.println(team.getTeamName());
        }
    }


    private void createClient() {
        System.out.print("Enter the new client's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the new client's phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter the new client's email: ");
        String email = scanner.nextLine();

        Clients client = new Clients();
        client.setClient_name(name);
        client.setClient_phone(phone);
        client.setClient_email(email);

        clientsService.addClient(client);
    }
    private void viewAllClients() {
        List<Clients> clients = clientsService.getAllClients();
        System.out.println("All clients:");
        for (Clients client : clients) {
            System.out.println(client.getClient_name() + " | " + client.getClient_phone() + " | " + client.getClient_email());
        }
    }

    private void deleteClient() {
        System.out.print("Enter the name of the client you want to delete: ");
        String clientName = scanner.nextLine();

        Clients client = clientsService.getClientByName(clientName);
        if (client != null) {
            clientsService.deleteClient(clientName);
            System.out.println("Client has been deleted successfully☑️.");
        } else {
            System.out.println("Client is not found❌.");
        }
    }

    private void updateClient() {
        System.out.print("Enter the client name you want to update: ");
        String clientName = scanner.nextLine();

        Clients client = clientsService.getClientByName(clientName);
        if (client != null) {
            System.out.print("Enter the new client name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter the new client phone: ");
            String newPhone = scanner.nextLine();
            System.out.print("Enter the new client email: ");
            String newEmail = scanner.nextLine();

            client.setClient_name(newName);
            client.setClient_phone(newPhone);
            client.setClient_email(newEmail);

            clientsService.updateClient(client);
        } else {
            System.out.println("Client not found❌.");
        }
    }


    private void updatePassword(UserModels.User user) {
        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();

        if (currentPassword.equals(user.getPassword())) {
            System.out.print("Enter your new password: ");
            String newPassword = scanner.nextLine();

            user.setPassword(newPassword);
            boolean updated = userService.updateUser(user);
            System.out.println("Password has been updated successfully☑️: " + updated);
        } else {
            System.out.println("Incorrect password❌. Password can't be updated.");
        }
    }

    private void registerUser() {
        System.out.print("Enter the username: ");
        String username = scanner.nextLine();
        System.out.print("Enter the password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        UserModels.User user = new UserModels.User();
        user.setUser_name(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);

        if (userRole == UserModels.UserRole.Admin) {
            System.out.println("Select the registerant's role:");
            System.out.println("1️⃣. Admin");
            System.out.println("2️⃣. Manager");
            System.out.println("3️⃣. Associate");
            System.out.print("Enter the role : ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine();
            switch (roleChoice) {
                case 1:
                    user.setUser_role(UserModels.UserRole.Admin);
                    break;
                case 2:
                    user.setUser_role(UserModels.UserRole.Manager);
                    break;
                case 3:
                    user.setUser_role(UserModels.UserRole.Associate);
                    break;
                default:
                    System.out.println("Invalid❌. Going to set it as Associate by default.");
                    user.setUser_role(UserModels.UserRole.Associate);
            }
        } else {
            user.setUser_role(userRole);
        }

        user.setStatus(UserModels.Status.Inactive);

        boolean registered = userService.registerUser(user);
        System.out.println("User has been registered: " + registered);
    }

    private void updateProfile() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        UserModels.User user = userService.login(username, password);
        if (user != null) {
            System.out.print("Enter your new username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Enter your new email: ");
            String newEmail = scanner.nextLine();
            System.out.print("Enter your new phone: ");
            String newPhone = scanner.nextLine();

            user.setUser_name(newUsername);
            user.setEmail(newEmail);
            user.setPhone(newPhone);

            boolean updated = userService.updateUser(user);
            System.out.println("Your profile has been updated☑️: " + updated);
        } else {
            System.out.println("Invalid username or password.❌❌");
        }
    }

    private void deleteAccount() throws SQLException {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        UserModels.User user = userService.login(username, password);
        if (user != null) {
            boolean deleted = userService.deleteUser(user.getUser_id());
            System.out.println("The account has been deleted☑️: " + deleted);
            System.out.println("Going back to role selection.");
            askForRole();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void viewAllUsers() {
        List<UserModels.User> users = userService.getAllUsers();
        System.out.println("All users:");
        for (UserModels.User u : users) {
            System.out.println(u.getUser_name());
        }
    }

    private void logout(UserModels.User user) throws SQLException {
        user.setStatus(UserModels.Status.Inactive);
        userService.updateUser(user);
        System.out.println("You have been logged out.☑️");
        askForRole();
    }
    private void viewAssignedProjectDetails(UserModels.User user) throws SQLException {
        List<Projects> assignedProjects = projectService.getProjectsByTeamMember(user.getUser_id());

        if (assignedProjects == null || assignedProjects.isEmpty()) {
            System.out.println("No such projects found❌.");
            return;
        }

        System.out.println("Assigned Projects:");
        for (Projects project : assignedProjects) {
            Clients client = clientsService.getClientById(project.getClient_id());
            List<Tasks> tasks = taskService.getTasksUsingProjectId(project.getProject_id());

            System.out.println("Project Name: " + project.getProject_name());
            System.out.println("Start Date: " + project.getStart_date());
            System.out.println("Client Name: " + (client != null ? client.getClient_name() : "Does not exist"));
            System.out.println("Tasks:");
            for (Tasks task : tasks) {
                System.out.println(" Task Name: " + task.getTask_name());
                System.out.println(" Task Description: " + task.getTask_description());
                System.out.println(" Task Status: " + task.getTaskStatus());
            }
            System.out.println("————————————————————————————————————————————————————");
        }
    }
    private void updateTaskStatus(UserModels.User user) throws SQLException {
        System.out.println("Enter task name: ");
        String taskName = scanner.nextLine();
        Tasks task = taskService.getTaskByName(taskName);

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        if (task.getAssigned_to() != user.getUser_id()) {
            System.out.println("You are not assigned to this task.");
            return;
        }

        System.out.println("Current status: " + task.getTaskStatus());
        System.out.println("Select new status:");
        for (int i = 0; i < Tasks.TaskStatus.values().length; i++) {
            System.out.println((i + 1) + ". " + Tasks.TaskStatus.values()[i]);
        }
        System.out.println("Enter your choice: ");

        int statusChoice = -1;
        while (true) {
            try {
                statusChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (statusChoice < 1 || statusChoice > Tasks.TaskStatus.values().length) {
                    System.out.println("Invalid choice. Try again!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        Tasks.TaskStatus newStatus = Tasks.TaskStatus.values()[statusChoice - 1];
        task.setTaskStatus(newStatus);

        if (newStatus == Tasks.TaskStatus.Completed) {
            task.setEnd_date(Date.valueOf(LocalDate.now()));
        }

        taskService.updateTask(task);
        System.out.println("Task status updated successfully.");
    }
    private void manageProjects() throws SQLException {
        while (true) {
            System.out.println("Projects Overview Menu:");
            System.out.println("1️⃣. View all projects");
            System.out.println("2️⃣. Add a new project");
            System.out.println("3️⃣. Edit status of an ongoing project");
            System.out.println("4️⃣. Edit milestones of an ongoing project");
            System.out.println("5️⃣. Assign tasks to associates");
            System.out.println("6️⃣. Go back");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewAllProjects();
                    break;
                case 2:
                    addNewProject();
                    break;
                case 3:
                    editProjectStatus();
                    break;
                case 4:
                    editProjectMilestones();
                    break;
                case 5:
                    assignTaskToAssociate();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
    private void viewAllProjects() throws SQLException {
        List<Projects> projects = projectService.getAllProjects();
        if (projects == null || projects.isEmpty()) {
            System.out.println("No such projects exist❌.");
            return;
        }
        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Projects:");
        for (Projects project : projects) {
            System.out.println("The Project is: " + project.getProject_name());
            System.out.println("Description: " + project.getProject_desc());
            System.out.println("Start Date: " + project.getStart_date());
            System.out.println("Deadline: " + project.getDate_of_finish());
            System.out.println("Status: " + project.getStatus_of_project());
            System.out.println("Client ID: " + project.getClient_id());
            System.out.println("Team ID: " + project.getTeam_id());
            System.out.println("————————————————————————————————————————————————————————————");
        }
    }
    private void addNewProject() throws SQLException {
        System.out.print("Project name: ");
        String projectName = scanner.nextLine();
        System.out.print("Project desc: ");
        String description = scanner.nextLine();
        System.out.print("Start date in YYYY/MM/DD Format: ");
        String startDate = scanner.nextLine();
        System.out.print("Date of finish in YYYY/MM/DD Format: ");
        String deadline = scanner.nextLine();
        System.out.print("Client's name: ");
        String clientName = scanner.nextLine();
        System.out.print("Project team: ");
        String teamName = scanner.nextLine();


        if (!isValidDate(startDate) || !isValidDate(deadline)) {
            System.out.println("————————————————————————————————————————————————————————————");
            System.out.println("Please adhere to  YYYY-MM-DD format.");
            System.out.println("————————————————————————————————————————————————————————————");
            return;
        }

        Clients client = clientsService.getClientByName(clientName);
        ProjectTeams team = projectTeamsService.getProjectTeam(teamName);

        if (client == null) {
            System.out.println("Enter a Client that does  exist.");
            return;
        }

        if (team == null) {
            System.out.println("No such project team❌");
            return;
        }

        Projects project = new Projects();
        project.setProject_name(projectName);
        project.setProject_desc(description);
        project.setStart_date(Date.valueOf(startDate));
        project.setDate_of_finish(Date.valueOf(deadline));
        project.setClient_id(client.getClient_id());
        project.setTeam_id(team.getTeamId());
        project.setStatus_of_project(Projects.ProjectStatus.Assigned);

        projectService.addProject(project);
        System.out.println("Project has been added successfully☑️");

        int projectId;
        projectId = milestoneService.getProjectIdByName(projectName);

        Milestones milestone = new Milestones(0,Milestones.MilestoneName.Start, "Get a grasp on what the client wants",  projectId);
        milestoneService.addMilestone(milestone);
        System.out.println("Project Start added successfully☑️.");
    }
    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    private void editProjectStatus() throws SQLException {
        System.out.print("Project name: ");
        String projectName = scanner.nextLine();
        Projects project = projectService.getProjectByName(projectName);

        if (project == null) {
            System.out.println("There is no such project❌.");
            return;
        }

        System.out.println("Current status: " + project.getStatus_of_project());
        System.out.println("Move up the status:");
        for (int i = 0; i < Projects.ProjectStatus.values().length; i++) {
            System.out.println((i + 1) + ". " + Projects.ProjectStatus.values()[i]);
        }
        System.out.print("Your choice: ");
        int statusChoice = scanner.nextInt();
        scanner.nextLine();

        if (statusChoice < 1 || statusChoice > Projects.ProjectStatus.values().length) {
            System.out.println("Invalid ❌ Please Try again!");
            return;
        }

        Projects.ProjectStatus newStatus = Projects.ProjectStatus.values()[statusChoice - 1];
        project.setStatus_of_project(newStatus);
        projectService.updateProject(project);
        System.out.println("Project status updated ☑️.");
    }
    private void editProjectMilestones() {
        System.out.print("Project name: ");
        String projectName = scanner.nextLine();

        int projectId = milestoneService.getProjectIdByName(projectName);

        System.out.println("Retrieved project ID: " + projectId);

        System.out.println("Select milestone:");
        for (int i = 0; i < Milestones.MilestoneName.values().length; i++) {
            System.out.println((i + 1) + ". " + Milestones.MilestoneName.values()[i]);
        }
        System.out.print("Enter your choice: ");
        int milestoneChoice = scanner.nextInt();
        scanner.nextLine();

        if (milestoneChoice < 1 || milestoneChoice > Milestones.MilestoneName.values().length) {
            System.out.println("Invalid choice. Try again!");
            return;
        }

        Milestones.MilestoneName milestoneName = Milestones.MilestoneName.values()[milestoneChoice - 1];
        System.out.print("Enter milestone description: ");
        String milestoneDescription = scanner.nextLine();

        Milestones milestone = new Milestones(0, milestoneName, milestoneDescription, projectId);

        milestoneService.addMilestone(milestone);
        System.out.println("Milestone updated successfully☑️.");

        if (milestoneName == Milestones.MilestoneName.Projectcompletion) {
            LocalDate endDate = LocalDate.now();
            updateActualEffort(projectId, endDate);
        }
    }
    private void updateActualEffort(int projectId, LocalDate endDate) {
        try {
            Projects project = projectService.getProjectById(projectId);
            if (project == null) {
                System.out.println("No such Project❌.");
                return;
            }

            LocalDate startDate = project.getStart_date().toLocalDate();
            int actualEffortDays = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);

            ActualEffort actualEffort = new ActualEffort();
            actualEffort.setProject_id(projectId);
            actualEffort.setProject_end_date(Date.valueOf(endDate));
            actualEffort.setActual_effort(actualEffortDays);

            ActualEffort existingEffortCalculation = actualEffortService.calculateEffortUsingProjectId(projectId);
            if (existingEffortCalculation != null) {
                actualEffortService.updateActualEffort(actualEffort);
            } else {
                actualEffortService.addActualEffort(actualEffort);
            }
        } catch (SQLException e) {
            System.out.println("Actual Effort not updated❌ " + e.getMessage());
        }
    }
    private void assignTaskToAssociate() throws SQLException {
        System.out.print("Project name: ");
        String projectName = scanner.nextLine();
        Projects project = projectService.getProjectByName(projectName);

        if (project == null) {
            System.out.println("No such project found❌");
            return;
        }

        System.out.println("Project ID:"+project.getProject_id());
        int projectId = project.getProject_id();
        System.out.println("Project id " + projectId);

        System.out.print("Associate's name: ");
        String teamMemberName = scanner.nextLine();
        UserModels.User teamMember = userService.getUser(teamMemberName);

        if (teamMember == null || teamMember.getUser_role() != UserModels.UserRole.Associate) {
            System.out.println("Associate not found.");
            return;
        }

        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        System.out.print("Task description: ");
        String taskDescription = scanner.nextLine();
        System.out.print("Enter task start date in the YYYY-MM-DD format: ");
        String startDate = scanner.nextLine();

        if (!isValidDate(startDate)) {
            System.out.println("Please adhere to YYYY-MM-DD format.");
            return;
        }

        Tasks.TaskStatus taskStatus = Tasks.TaskStatus.Started;

        Tasks task = new Tasks();
        task.setTask_name(taskName);
        task.setTask_description(taskDescription);
        task.setStart_date(Date.valueOf(startDate));
        task.setProject_id(projectId);
        task.setAssigned_to(teamMember.getUser_id());
        task.setTaskStatus(taskStatus);

        boolean added = taskService.addTask(task);
        System.out.println("Task has been assigned: " + added);
    }
}
