import org.example.dao.ProjectTeamsDAO;
import org.example.models.ProjectTeams;
import org.example.service.ProjectTeamsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProjectTeamsServiceTest {

    @Mock
    private ProjectTeamsDAO projectTeamsDAO;

    private ProjectTeamsService projectTeamsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        projectTeamsService = new ProjectTeamsService(projectTeamsDAO);
    }

    @Test
    public void testAddProjectTeam() {
        ProjectTeams projectTeam = new ProjectTeams("Team1");

        Mockito.doNothing().when(projectTeamsDAO).addProjectTeam(projectTeam);

        projectTeamsService.addProjectTeam(projectTeam);

        Mockito.verify(projectTeamsDAO, Mockito.times(1)).addProjectTeam(projectTeam);
    }

    @Test
    public void testGetProjectTeam() {
        ProjectTeams expectedTeam = new ProjectTeams(1, "Team1");

        when(projectTeamsDAO.getProjectTeam(anyString())).thenReturn(expectedTeam);

        ProjectTeams retrievedTeam = projectTeamsService.getProjectTeam("Team1");

        Assertions.assertEquals(expectedTeam, retrievedTeam);
    }

    @Test
    public void testGetAllProjectTeams() {
        List<ProjectTeams> expectedTeams = new ArrayList<>();
        expectedTeams.add(new ProjectTeams(1, "Team1"));
        expectedTeams.add(new ProjectTeams(2, "Team2"));

        when(projectTeamsDAO.getAllProjectTeams()).thenReturn(expectedTeams);

        List<ProjectTeams> retrievedTeams = projectTeamsService.getAllProjectTeams();

        Assertions.assertEquals(expectedTeams.size(), retrievedTeams.size());
        Assertions.assertEquals(expectedTeams, retrievedTeams);
    }

    @Test
    public void testUpdateProjectTeam() {
        String currentTeamName = "Team1";
        ProjectTeams updatedTeam = new ProjectTeams(1, "UpdatedTeam");

        Mockito.doNothing().when(projectTeamsDAO).updateProjectTeam(currentTeamName, updatedTeam);

        projectTeamsService.updateProjectTeam(currentTeamName, updatedTeam);

        Mockito.verify(projectTeamsDAO, Mockito.times(1)).updateProjectTeam(currentTeamName, updatedTeam);
    }

    @Test
    public void testDeleteProjectTeam() {
        String teamName = "Team1";

        Mockito.doNothing().when(projectTeamsDAO).deleteProjectTeam(teamName);
        projectTeamsService.deleteProjectTeam(teamName);

        Mockito.verify(projectTeamsDAO, Mockito.times(1)).deleteProjectTeam(teamName);
    }
}