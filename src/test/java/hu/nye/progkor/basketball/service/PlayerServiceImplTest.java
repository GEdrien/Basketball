package hu.nye.progkor.basketball.service;

import hu.nye.progkor.basketball.exception.GlobalException;
import hu.nye.progkor.basketball.model.Player;
import hu.nye.progkor.basketball.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

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
    public void testGetPlayer_NonExistingPlayerId_ThrowWxception() {
        Long nonExistingPlayerId = 2L;
        when(playerRepositoryMock.findById(nonExistingPlayerId)).thenReturn(Optional.empty());

        assertThrows(GlobalException.class, ()-> playerService.getPlayer(nonExistingPlayerId));
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