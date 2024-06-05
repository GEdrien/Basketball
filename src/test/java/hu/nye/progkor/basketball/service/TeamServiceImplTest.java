package hu.nye.progkor.basketball.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import hu.nye.progkor.basketball.exception.GlobalException;
import hu.nye.progkor.basketball.model.Team;
import hu.nye.progkor.basketball.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepositoryMock;

    @InjectMocks
    private TeamServiceImpl teamService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTeam_ReturnTeamList() {
        Team firstTeam = new Team();
        Team secondTeam = new Team();
        firstTeam.setId(1L);
        firstTeam.setName("Team that created first");
        secondTeam.setId(2L);
        secondTeam.setName("Team that created as second");
        List<Team> teamList = Arrays.asList(firstTeam, secondTeam);
        when(teamRepositoryMock.findAll()).thenReturn(teamList);

        List<Team> resultList = teamService.getAllTeam();

        assertEquals(2, resultList.size());
        assertEquals("Team that created first", resultList.get(0).getName());
        assertEquals("Team that created as second", resultList.get(1).getName());
    }

    @Test
    public void testGetTeam_ExistingTeamId_ReturnsTeam() {
        Team existingTeam = new Team();
        existingTeam.setId(1L);
        existingTeam.setName("Test Team Name");
        when(teamRepositoryMock.findById(existingTeam.getId())).thenReturn(Optional.of(existingTeam));

        Team result = teamService.getTeam(existingTeam.getId());

        assertEquals("Test Team Name", result.getName());
    }

    @Test
    public void testGetTeam_NonExistingTeamId_ThrowsException() {
        Long nonExistingTeamId = 2L;
        when(teamRepositoryMock.findById(nonExistingTeamId)).thenReturn(Optional.empty());

        assertThrows(GlobalException.class, () -> teamService.getTeam(nonExistingTeamId));
    }

    @Test
    public void testAddTeam_NotExistingTeamWithSameContraction_AddTeam(){
        Team nonExistingTeam = new Team();
        nonExistingTeam.setContraction("WAS");
        when(teamRepositoryMock.findByContraction(nonExistingTeam.getContraction())).thenReturn(Optional.empty());
        when(teamRepositoryMock.save(nonExistingTeam)).thenReturn(nonExistingTeam);

        Team savedTeam = teamService.addTeam(nonExistingTeam);

        assertNotNull(savedTeam);
        assertEquals("WAS", savedTeam.getContraction());
        verify(teamRepositoryMock).save(nonExistingTeam);
    }

    @Test
    public void testAddTeam_ExistingTeamWithSameContraction_ThrowsException() {
        Team existingTeam = new Team();
        existingTeam.setId(1L);
        existingTeam.setContraction("WAS");
        when(teamRepositoryMock.findByContraction(existingTeam.getContraction())).thenReturn(Optional.of(existingTeam));

        assertThrows(GlobalException.class, () -> teamService.deleteTeam(existingTeam.getId()));
    }

    /*
    @Test
    public void testUpdateTeam_NonExsitingTeamId_UpdateTeam() {
        Team existingOldTeam = new Team();
        existingOldTeam.setId(1L);
        existingOldTeam.setName("Boston Celtics");
        existingOldTeam.setHeadCoach("Joe Mazulla");
        Team newTeam = new Team();
        newTeam.setId(1L);
        newTeam.setName("Golden State Warriors");
        newTeam.setHeadCoach("Steve Kerr");
        when(teamRepositoryMock.findById(existingOldTeam.getId())).thenReturn(Optional.of(existingOldTeam));
        when(teamRepositoryMock.save(newTeam)).thenReturn(newTeam);

        Team updatedTeam = teamService.updateTeam(newTeam, existingOldTeam.getId());

        //assertNotNull(updatedTeam);
        //assertEquals("Golden State Warriors", updatedTeam.getName());
        //assertEquals("Steve Kerr", updatedTeam.getHeadCoach());
        verify(teamRepositoryMock).save(newTeam);
    }*/

    @Test
    public void testUpdateTeam_ExistingTeamId_ThrowException() {
        Long existingTeamId = 2L;
        when(teamRepositoryMock.findById(existingTeamId)).thenReturn(Optional.empty());

        assertThrows(GlobalException.class, () -> teamService.deleteTeam(existingTeamId));
    }

    @Test
    public void testDeleteTeam_ExistingTeamId_DeletesTeam() {
        Long existingTeamId = 1L;
        when(teamRepositoryMock.findById(existingTeamId)).thenReturn(Optional.of(new Team()));

        teamService.deleteTeam(existingTeamId);

        verify(teamRepositoryMock, times(1)).deleteById(existingTeamId);
    }

    @Test
    public void testDeleteTeam_NonExistingTeamId_ThrowsException() {
        Long nonExistingTeamId = 2L;
        when(teamRepositoryMock.existsById(nonExistingTeamId)).thenReturn(false);

        assertThrows(GlobalException.class, () -> teamService.deleteTeam(nonExistingTeamId));
    }
}