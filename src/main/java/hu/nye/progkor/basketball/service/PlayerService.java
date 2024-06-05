package hu.nye.progkor.basketball.service;

import java.util.List;

import hu.nye.progkor.basketball.model.Player;

/**
 * Service interface that define tha functions for the specific implementation.
 */
public interface PlayerService {

    List<Player> getAllPlayer();

    Player getPlayer(Long id);

    Player addPlayer(Player player, String name);

    Player updatePlayer(Player player, String contraction);

    void deletePlayer(Long id);
}