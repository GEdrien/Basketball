package hu.nye.progkor.basketball.repository;

import hu.nye.progkor.basketball.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Player Entity.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}