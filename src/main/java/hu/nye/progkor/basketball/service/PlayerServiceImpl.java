package hu.nye.progkor.basketball.service;

import java.util.List;
import java.util.Optional;

import hu.nye.progkor.basketball.exception.GlobalException;
import hu.nye.progkor.basketball.model.Player;
import hu.nye.progkor.basketball.model.Team;
import hu.nye.progkor.basketball.repository.PlayerRepository;
import hu.nye.progkor.basketball.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Contains the logic for the player Entity CRUD methods.
 */
@Service
public class PlayerServiceImpl implements  PlayerService {

    private PlayerRepository playerRepository;

    private TeamRepository teamRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    private final String playerNotFoundMessage = "Player not found with the id of: ";
    private final String teamNotFoundMessage = "Team not found with the contraction of: ";

    @Override
    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayer(Long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        }
        throw new GlobalException(playerNotFoundMessage + id);
    }

    @Override
    public Player addPlayer(Player player, String contraction) {
        Optional<Team> optionalTeam = teamRepository.findByContraction(contraction);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            player.setTeam(team.getContraction());
            player.setTeamId(team);
            playerRepository.save(player);
            List<Player> players = team.getPlayers();
            players.add(player);
            team.setPlayers(players);
            teamRepository.save(team);
            return player;
        }
        throw new GlobalException(teamNotFoundMessage + contraction);
    }

    @Override
    public Player updatePlayer(Player player, String contraction) {
        return player;
        //TODO I need to work in this
    }

    @Override
    public void deletePlayer(Long id) {
        if (playerAlreadyExist(id)) {
            playerRepository.deleteById(id);
        } else {
            throw new GlobalException(playerNotFoundMessage + id);
        }
    }

    private boolean playerAlreadyExist(Long id) {
        return playerRepository.findById(id).isPresent();
    }


}