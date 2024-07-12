import org.example.dao.MilestonesDAO;
import org.example.models.Milestones;
import org.example.service.MilestonesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MilestonesServiceTest {

    @Mock
    private static MilestonesDAO milestonesDAO;

    private MilestonesService milestonesService;

    @BeforeAll
    public static void setup() {
        milestonesDAO= Mockito.mock();
        MockitoAnnotations.openMocks(MilestonesServiceTest.class);
    }

    @Test
    public void testAddMilestone() throws SQLException {
        Milestones milestoneToAdd = new Milestones(1, Milestones.MilestoneName.Start, "Milestone 1", 1);

        doNothing().when(milestonesDAO).addMilestone(milestoneToAdd);

        milestonesService = new MilestonesService(milestonesDAO);
        milestonesService.addMilestone(milestoneToAdd);

        verify(milestonesDAO, times(1)).addMilestone(milestoneToAdd);
    }

    @Test
    public void testUpdateMilestone() throws SQLException {
        Milestones milestoneToUpdate = new Milestones(1, Milestones.MilestoneName.Designing, "Updated Milestone", 2);

        doNothing().when(milestonesDAO).updateMilestone(milestoneToUpdate);

        milestonesService = new MilestonesService(milestonesDAO);
        milestonesService.updateMilestone(milestoneToUpdate);

        verify(milestonesDAO, times(1)).updateMilestone(milestoneToUpdate);
    }

    @Test
    public void testDeleteMilestone() throws SQLException {
        int milestoneIdToDelete = 1;

        doNothing().when(milestonesDAO).deleteMilestone(milestoneIdToDelete);

        milestonesService = new MilestonesService(milestonesDAO);
        milestonesService.deleteMilestone(milestoneIdToDelete);

        verify(milestonesDAO, times(1)).deleteMilestone(milestoneIdToDelete);
    }

    @Test
    public void testGetMilestoneById() throws SQLException {
        int milestoneId = 1;
        Milestones expectedMilestone = new Milestones(milestoneId, Milestones.MilestoneName.Integrating, "Milestone 1", 1);

        when(milestonesDAO.getMilestone(milestoneId)).thenReturn(expectedMilestone);

        milestonesService = new MilestonesService(milestonesDAO);
        Milestones actualMilestone = milestonesService.getMilestoneById(milestoneId);

        Assertions.assertEquals(expectedMilestone, actualMilestone);
    }

    @Test
    public void testGetAllMilestones() throws SQLException {
        List<Milestones> expectedMilestones = new ArrayList<>();
        expectedMilestones.add(new Milestones(1, Milestones.MilestoneName.Currently_Testing, "Milestone 1", 1));
        expectedMilestones.add(new Milestones(2, Milestones.MilestoneName.Maintenance, "Milestone 2", 2));

        when(milestonesDAO.getAllMilestones()).thenReturn(expectedMilestones);

        milestonesService = new MilestonesService(milestonesDAO);
        List<Milestones> actualMilestones = milestonesService.getAllMilestones();

        Assertions.assertEquals(expectedMilestones.size(), actualMilestones.size());
        Assertions.assertEquals(expectedMilestones, actualMilestones);
    }

    @Test
    public void testGetProjectIdByName() throws SQLException {
        String projectName = "Test Project";
        int expectedProjectId = 1;

        when(milestonesDAO.getProjectIdByName(projectName)).thenReturn(expectedProjectId);

        milestonesService = new MilestonesService(milestonesDAO);
        int actualProjectId = milestonesService.getProjectIdByName(projectName);

        Assertions.assertEquals(expectedProjectId, actualProjectId);
    }
}