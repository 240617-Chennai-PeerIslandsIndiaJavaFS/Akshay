

import org.example.dao.ProjectTeamMembersDAO;
import org.example.models.TeamMembers;
import org.example.service.TeamMembersService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TeamMembersServiceTest {
    private static ProjectTeamMembersDAO projectTeamMembersDAO;
    private static TeamMembersService teamMembersService;

    @BeforeAll
    public static void setup() {
        projectTeamMembersDAO = mock(ProjectTeamMembersDAO.class);
        teamMembersService = new TeamMembersService();
        teamMembersService.projectTeamMembersDAO = projectTeamMembersDAO;
    }

    @Test
    public void testAddMemberToTeam() throws SQLException {
        String memberName = "John";
        String teamName = "Devops";

        doNothing().when(projectTeamMembersDAO).addMemberByName(memberName, teamName);

        teamMembersService.addMemberToTeam(memberName, teamName);

        verify(projectTeamMembersDAO, times(1)).addMemberByName(memberName, teamName);
    }

    @Test
    public void testRemoveMemberFromTeam() throws SQLException {
        String memberName = "John";

        doNothing().when(projectTeamMembersDAO).removeMemberByName(memberName);

        teamMembersService.removeMemberFromTeam(memberName);

        verify(projectTeamMembersDAO, times(1)).removeMemberByName(memberName);
    }

    @Test
    public void testIsMemberByName() throws SQLException {
        String memberName = "John";
        TeamMembers member = new TeamMembers(1, 1, memberName);

        when(projectTeamMembersDAO.getMemberByName(memberName)).thenReturn(member);

        boolean exists = teamMembersService.isMemberByName(memberName);

        assertTrue(exists);
        verify(projectTeamMembersDAO, times(1)).getMemberByName(memberName);
    }

    @Test
    public void testGetMemberByName() throws SQLException {
        String memberName = "John";
        TeamMembers expectedMember = new TeamMembers(1, 1, memberName);

        when(projectTeamMembersDAO.getMemberByName(memberName)).thenReturn(expectedMember);

        TeamMembers actualMember = teamMembersService.getMemberByName(memberName);

        assertEquals(expectedMember, actualMember);
        verify(projectTeamMembersDAO, times(1)).getMemberByName(memberName);
    }

    @Test
    public void testGetAllMembersForTeam() throws SQLException {
        String teamName = "Devops";
        List<String> expectedMembers = Arrays.asList("John", "Jake");

        when(projectTeamMembersDAO.getTeamIdByName(teamName)).thenReturn(1);
        when(projectTeamMembersDAO.getAllMembersByTeamId(1)).thenReturn(expectedMembers);

        List<String> actualMembers = teamMembersService.getAllMembersForTeam(teamName);

        assertEquals(expectedMembers, actualMembers);
        verify(projectTeamMembersDAO, times(1)).getTeamIdByName(teamName);
        verify(projectTeamMembersDAO, times(1)).getAllMembersByTeamId(1);
    }
}
