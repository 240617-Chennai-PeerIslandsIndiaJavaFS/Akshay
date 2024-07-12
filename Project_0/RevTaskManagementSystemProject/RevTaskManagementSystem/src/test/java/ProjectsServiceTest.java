import org.example.dao.ProjectsDAO;
import org.example.models.Projects;
import org.example.service.ProjectsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
public class ProjectsServiceTest {
    private static ProjectsDAO projectsDAO;
    private static ProjectsService projectsService;

    @BeforeAll
    public static void setup() {
        projectsDAO = mock(ProjectsDAO.class);
        projectsService = new ProjectsService(projectsDAO);
    }

    @Test
    public void addProjectTest() throws SQLException {
        Projects projectToAdd = new Projects();
        projectToAdd.setProject_id(1);
        projectToAdd.setProject_name("Test Project");
        projectToAdd.setClient_id(1);
        projectToAdd.setTeam_id(1);
        projectToAdd.setStart_date(new Date(System.currentTimeMillis()));
        projectToAdd.setDate_of_finish(new Date(System.currentTimeMillis() + 86400000)); // Adding one day (86400000 milliseconds) to current time
        projectToAdd.setProject_desc("Test project description");
        projectToAdd.setStatus_of_project(Projects.ProjectStatus.In_Progress);

        doNothing().when(projectsDAO).addProject(projectToAdd);

        projectsService.addProject(projectToAdd);

        verify(projectsDAO, times(1)).addProject(projectToAdd);
    }

    @Test
    public void updateProjectTest() throws SQLException {
        Projects projectToUpdate = new Projects();
        projectToUpdate.setProject_id(1);
        projectToUpdate.setProject_name("Updated Test Project");
        projectToUpdate.setClient_id(2);
        projectToUpdate.setTeam_id(2);
        projectToUpdate.setStart_date(new Date(System.currentTimeMillis()));
        projectToUpdate.setDate_of_finish(new Date(System.currentTimeMillis() + 86400000));
        projectToUpdate.setProject_desc("Updated test project description");
        projectToUpdate.setStatus_of_project(Projects.ProjectStatus.Completed);

        doNothing().when(projectsDAO).updateProject(projectToUpdate);

        projectsService.updateProject(projectToUpdate);

        verify(projectsDAO, times(1)).updateProject(projectToUpdate);
    }

    @Test
    public void getAllProjectsTest() throws SQLException {
        List<Projects> expectedProjects = new ArrayList<>();
        Projects project1 = new Projects();
        project1.setProject_id(1);
        project1.setProject_name("Project 1");
        project1.setClient_id(1);
        project1.setTeam_id(1);
        project1.setStart_date(new Date(System.currentTimeMillis()));
        project1.setDate_of_finish(new Date(System.currentTimeMillis() + 86400000));
        project1.setProject_desc("Project 1 description");
        project1.setStatus_of_project(Projects.ProjectStatus.Assigned);
        expectedProjects.add(project1);

        Projects project2 = new Projects();
        project2.setProject_id(2);
        project2.setProject_name("Project 2");
        project2.setClient_id(2);
        project2.setTeam_id(2);
        project2.setStart_date(new Date(System.currentTimeMillis()));
        project2.setDate_of_finish(new Date(System.currentTimeMillis() + 172800000));
        project2.setProject_desc("Project 2 description");
        project2.setStatus_of_project(Projects.ProjectStatus.In_Progress);
        expectedProjects.add(project2);

        when(projectsDAO.getAllProjects()).thenReturn(expectedProjects);

        List<Projects> actualProjects = projectsService.getAllProjects();

        Assertions.assertEquals(expectedProjects.size(), actualProjects.size());
        Assertions.assertEquals(expectedProjects, actualProjects);
    }

    @Test
    public void deleteProjectTest() throws SQLException {
        int projectIdToDelete = 1;

        doNothing().when(projectsDAO).deleteProject(projectIdToDelete);

        projectsService.deleteProject(projectIdToDelete);

        verify(projectsDAO, times(1)).deleteProject(projectIdToDelete);
    }

    @Test
    public void getProjectByIdTest() throws SQLException {
        int projectId = 1;
        Projects expectedProject = new Projects();
        expectedProject.setProject_id(projectId);
        expectedProject.setProject_name("Test Project");
        expectedProject.setClient_id(1);
        expectedProject.setTeam_id(1);
        expectedProject.setStart_date(new Date(System.currentTimeMillis()));
        expectedProject.setDate_of_finish(new Date(System.currentTimeMillis() + 86400000));
        expectedProject.setProject_desc("Test project description");
        expectedProject.setStatus_of_project(Projects.ProjectStatus.In_Progress);

        when(projectsDAO.getProject(projectId)).thenReturn(expectedProject);

        Projects actualProject = projectsService.getProjectById(projectId);

        Assertions.assertEquals(expectedProject, actualProject);
    }

    @Test
    public void getProjectByNameTest() throws SQLException {
        String projectName = "Test Project";
        Projects expectedProject = new Projects();
        expectedProject.setProject_id(1);
        expectedProject.setProject_name(projectName);
        expectedProject.setClient_id(1);
        expectedProject.setTeam_id(1);
        expectedProject.setStart_date(new Date(System.currentTimeMillis()));
        expectedProject.setDate_of_finish(new Date(System.currentTimeMillis() + 86400000));
        expectedProject.setProject_desc("Test project description");
        expectedProject.setStatus_of_project(Projects.ProjectStatus.In_Progress);

        when(projectsDAO.getProjectByName(projectName)).thenReturn(expectedProject);

        Projects actualProject = projectsService.getProjectByName(projectName);

        Assertions.assertEquals(expectedProject, actualProject);
    }

    @Test
    public void getProjectsByTeamMemberTest() throws SQLException {
        int memberId = 1;
        List<Projects> expectedProjects = new ArrayList<>();
        Projects project1 = new Projects();
        project1.setProject_id(1);
        project1.setProject_name("Project 1");
        project1.setClient_id(1);
        project1.setTeam_id(1);
        project1.setStart_date(new Date(System.currentTimeMillis()));
        project1.setDate_of_finish(new Date(System.currentTimeMillis() + 86400000));
        project1.setProject_desc("Project 1 description");
        project1.setStatus_of_project(Projects.ProjectStatus.Assigned);
        expectedProjects.add(project1);

        Projects project2 = new Projects();
        project2.setProject_id(2);
        project2.setProject_name("Project 2");
        project2.setClient_id(2);
        project2.setTeam_id(1); // Assuming team_id 1 for member 1
        project2.setStart_date(new Date(System.currentTimeMillis()));
        project2.setDate_of_finish(new Date(System.currentTimeMillis() + 172800000));
        project2.setProject_desc("Project 2 description");
        project2.setStatus_of_project(Projects.ProjectStatus.In_Progress);
        expectedProjects.add(project2);

        when(projectsDAO.getProjectsByTeamMember(memberId)).thenReturn(expectedProjects);

        List<Projects> actualProjects = projectsService.getProjectsByTeamMember(memberId);

        Assertions.assertEquals(expectedProjects.size(), actualProjects.size());
        Assertions.assertEquals(expectedProjects, actualProjects);
    }
}
