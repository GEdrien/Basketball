package hu.nye.progkor.basketball.controller;

import java.util.List;

import hu.nye.progkor.basketball.model.Team;
import hu.nye.progkor.basketball.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for team entity CRUD operations.
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    public ResponseEntity<List<Team>> getAllTeam() {
        return new ResponseEntity<>(teamService.getAllTeam(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeam(id), HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<Team> addTeam(@RequestBody @Valid Team team) {
        return new ResponseEntity<>(teamService.addTeam(team), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@RequestBody @Valid Team team, @PathVariable Long id) {
        return new ResponseEntity<>(teamService.updateTeam(team, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

}