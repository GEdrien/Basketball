package hu.nye.progkor.basketball.service;

import java.util.List;
import java.util.Optional;

import hu.nye.progkor.basketball.exception.GlobalException;
import hu.nye.progkor.basketball.model.Team;
import hu.nye.progkor.basketball.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Contains the logic for the team Entity CRUD methods.
 */
@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    private final String teamNotFoundMessage = "Team not found with the id of: ";
    private final String teamAlreadyExistMessage = "Team already exist with the contraction of: ";

    @Override
    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeam(Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            return team;
        }
        throw new GlobalException(teamNotFoundMessage + id);
    }

    @Override
    public Team addTeam(Team team) {
        Optional<Team> optionalTeam = teamRepository.findByContraction(team.getContraction());
        if (optionalTeam.isPresent()) {
            throw new GlobalException(teamAlreadyExistMessage + team.getContraction());
        }
        team.setPpg(0);
        team.setRpg(0);
        team.setApg(0);
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team, Long id) {
        Optional<Team> optionalOldTeam = teamRepository.findById(id);
        if (optionalOldTeam.isPresent()) {
            Team oldTeam = optionalOldTeam.get();
            oldTeam.setName(team.getName());
            oldTeam.setHeadCoach(team.getHeadCoach());
            return teamRepository.save(oldTeam);
        }
        throw new GlobalException(teamNotFoundMessage + id);
    }

    @Override
    public void deleteTeam(Long id) {
        if (teamAlreadyExist(id)) {
            teamRepository.deleteById(id);
        } else {
            throw new GlobalException(teamNotFoundMessage + id);
        }
    }

    private boolean teamAlreadyExist(Long id) {
        return teamRepository.findById(id).isPresent();
    }
}