package hu.nye.progkor.basketball.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Team entity.
 * Team consist of multiple player.
 * Team ppg, apg, rpg fields update with players value
 */
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Must have a name")
    private String name;

    @Column(unique = true)
    @Size(max = 3, message = "Contraction must be max 3 long")
    @NotBlank(message = "Must have a contraction")
    private String contraction;

    @NotNull(message = "Must belong to a division")
    private Division division;

    @Column(unique = true)
    @NotBlank(message = "Must have a head coach")
    private String headCoach;

    private double ppg;

    private double apg;

    private double rpg;
    @OneToMany(mappedBy = "teamId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> players;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContraction() {
        return contraction;
    }

    public void setContraction(String contraction) {
        this.contraction = contraction;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(String headCoach) {
        this.headCoach = headCoach;
    }

    public double getPpg() {
        return ppg;
    }

    public void setPpg(double ppg) {
        this.ppg = ppg;
    }

    public double getApg() {
        return apg;
    }

    public void setApg(double apg) {
        this.apg = apg;
    }

    public double getRpg() {
        return rpg;
    }

    public void setRpg(double rpg) {
        this.rpg = rpg;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}