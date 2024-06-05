package hu.nye.progkor.basketball.service;

import hu.nye.progkor.basketball.exception.GlobalException;
import hu.nye.progkor.basketball.model.Player;
import hu.nye.progkor.basketball.model.Post;
import hu.nye.progkor.basketball.model.Team;
import hu.nye.progkor.basketball.repository.PlayerRepository;
import hu.nye.progkor.basketball.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

    @Mock
    private TeamRepository teamRepositoryMock;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPlayer_ReturnPlayerList() {
        Player firstPlayer = new Player();
        Player secondPlayer = new Player();
        firstPlayer.setId(1L);
        firstPlayer.setFirstName("Curry");
        secondPlayer.setId(2L);
        secondPlayer.setFirstName("Doncic");
        List<Player> playerList = Arrays.asList(firstPlayer, secondPlayer);
        when(playerRepositoryMock.findAll()).thenReturn(playerList);

        List<Player> resultList = playerService.getAllPlayer();

        assertEquals(2, resultList.size());
        assertEquals("Curry", resultList.get(0).getFirstName());
        assertEquals("Doncic", resultList.get(1).getFirstName());
    }

    @Test
    public void testGetPlayer_ExistingPlayerId_ReturnPlayer() {
        Player existingPlayer = new Player();
        existingPlayer.setId(1L);
        existingPlayer.setFirstName("Curry");
        when(playerRepositoryMock.findById(existingPlayer.getId())).thenReturn(Optional.of(existingPlayer));

        Player result = playerService.getPlayer(existingPlayer.getId());

        assertEquals("Curry", result.getFirstName());
    }

    @Test
    public void testGetPlayer_NonExistingPlayerId_ThrowException() {
        Long nonExistingPlayerId = 2L;
        when(playerRepositoryMock.findById(nonExistingPlayerId)).thenReturn(Optional.empty());

        assertThrows(GlobalException.class, ()-> playerService.getPlayer(nonExistingPlayerId));
    }

    @Test
    public void testAddPlayer_ExistingTeamContraction_AddPlayer() {
        Team existingTeam = new Team();
        existingTeam.setContraction("XYZ");
        existingTeam.setPlayers(new ArrayList<>());
        Player player = new Player();
        when(teamRepositoryMock.findByContraction(existingTeam.getContraction()))
                .thenReturn(Optional.of(existingTeam));

        Player result = playerService.addPlayer(player, existingTeam.getContraction());

        assertNotNull(result);
        assertEquals(existingTeam.getContraction(), result.getTeam());
        verify(playerRepositoryMock, times(1)).save(player);
        verify(teamRepositoryMock, times(1)).save(existingTeam);
    }

    @Test
    public void testAddPlayer_NonExsitingTeamContraction_ThrowsException() {
        String contraction = "XYZ";
        Player player = new Player();
        when(teamRepositoryMock.findByContraction(contraction)).thenReturn(Optional.empty());

        assertThrows(GlobalException.class, ()-> playerService.addPlayer(player, contraction));
    }

    @Test
    void testUpdatePlayer() {
        Long playerId = 1L;
        Player existingPlayer = new Player();
        existingPlayer.setId(playerId);
        existingPlayer.setFirstName("Stephen");
        existingPlayer.setLastName("Curry");
        existingPlayer.setAge(25);
        existingPlayer.setCountry("USA");
        existingPlayer.setPost(Post.GUARD);
        existingPlayer.setPpg(20);
        existingPlayer.setApg(5);
        existingPlayer.setRpg(10);
        existingPlayer.setPie(25);
        Player updatedPlayer = new Player();
        updatedPlayer.setId(playerId);
        updatedPlayer.setFirstName("Luka");
        updatedPlayer.setLastName("Doncic");
        updatedPlayer.setAge(27);
        updatedPlayer.setCountry("Slovenia");
        updatedPlayer.setPost(Post.GUARD);
        updatedPlayer.setPpg(22);
        updatedPlayer.setApg(6);
        updatedPlayer.setRpg(8);
        updatedPlayer.setPie(30);
        when(playerRepositoryMock.findById(playerId)).thenReturn(Optional.of(existingPlayer));
        when(playerRepositoryMock.save(any())).thenReturn(updatedPlayer);

        Player result = playerService.updatePlayer(updatedPlayer, playerId);

        assertNotNull(result);
        assertEquals(updatedPlayer.getFirstName(), result.getFirstName());
        assertEquals(updatedPlayer.getLastName(), result.getLastName());
        assertEquals(updatedPlayer.getAge(), result.getAge());
        assertEquals(updatedPlayer.getCountry(), result.getCountry());
        assertEquals(updatedPlayer.getPost(), result.getPost());
        assertEquals(updatedPlayer.getPpg(), result.getPpg());
        assertEquals(updatedPlayer.getApg(), result.getApg());
        assertEquals(updatedPlayer.getRpg(), result.getRpg());
        assertEquals(updatedPlayer.getPie(), result.getPie());
    }

    @Test
    public void testDeletePlayer_ExistingPlayerId_DeletePlayer() {
        Long existingPlayerId = 1L;
        when(playerRepositoryMock.findById(existingPlayerId)).thenReturn(Optional.of(new Player()));

        playerService.deletePlayer(existingPlayerId);

        verify(playerRepositoryMock, times(1)).deleteById(existingPlayerId);
    }

    @Test
    public void testDeletePlayer_nonExistingPlayerId_ThrowException() {
        Long nonExistingPlayerId = 2L;
        when(playerRepositoryMock.existsById(nonExistingPlayerId)).thenReturn(false);

        assertThrows(GlobalException.class, ()-> playerService.deletePlayer(nonExistingPlayerId));
    }
}