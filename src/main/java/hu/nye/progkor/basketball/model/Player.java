package hu.nye.progkor.basketball.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Player entity.
 * Players must belong to a team.
 */
@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Player must have firstName")
    private String firstName;
    @NotBlank(message = "Player must have lastName")
    private String lastName;
    private String team;
    @NotNull(message = "Post need a correct value")
    private Post post;
    @NotNull(message = "Player must have a jersey number")
    private int jersey;
    @NotNull(message = "Player must have weigth property defined")
    @Min(160)
    @Max(250)
    private int height;
    @NotNull(message = "Player must have weight property defined")
    private int weight;
    @NotNull(message = "Player must have salary")
    @Min(100000)
    @Max(100000000)
    private int salary;
    @NotBlank(message = "Player have to belong to a specific country")
    private String country;
    @NotNull(message = "Player must have age")
    @Min(16)
    @Max(50)
    private int age;
    @NotNull(message = "Even if a Rookie needs this information")
    @Min(0)
    @Max(30)
    private int experience;
    @NotNull(message = "Player must have points")
    @Min(0)
    @Max(50)
    private double ppg;
    @NotNull(message = "Player must have rebounds")
    @Min(0)
    @Max(30)
    private double rpg;
    @NotNull(message = "Player must have assists")
    @Min(0)
    @Max(30)
    private double apg;
    @NotNull(message = "Player must have PIE value")
    @Min(0)
    @Max(30)
    private double pie;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "teamId")
    private Team teamId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getJersey() {
        return jersey;
    }

    public void setJersey(int jersey) {
        this.jersey = jersey;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getPpg() {
        return ppg;
    }

    public void setPpg(double ppg) {
        this.ppg = ppg;
    }

    public double getRpg() {
        return rpg;
    }

    public void setRpg(double rpg) {
        this.rpg = rpg;
    }

    public double getApg() {
        return apg;
    }

    public void setApg(double apg) {
        this.apg = apg;
    }

    public double getPie() {
        return pie;
    }

    public void setPie(double pie) {
        this.pie = pie;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

}