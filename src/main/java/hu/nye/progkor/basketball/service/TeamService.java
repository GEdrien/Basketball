package hu.nye.progkor.basketball.service;

import java.util.List;

import hu.nye.progkor.basketball.model.Team;

/**
 * Service interface that define tha functions for the specific implementation.
 */
public interface TeamService {

    List<Team> getAllTeam();

    Team getTeam(Long id);

    Team addTeam(Team team);

    Team updateTeam(Team team, Long id);

    void deleteTeam(Long id);
}