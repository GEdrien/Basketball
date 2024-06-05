package hu.nye.progkor.basketball.repository;

import java.util.Optional;

import hu.nye.progkor.basketball.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Team Entity.
 * Has findByContributor sql query. for service usage.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByContraction(String contraction);
}