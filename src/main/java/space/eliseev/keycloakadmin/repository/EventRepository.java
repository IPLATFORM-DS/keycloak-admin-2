package space.eliseev.keycloakadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import space.eliseev.keycloakadmin.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    /**
     * Returns all events of a user by username
     * @param username
     * @return
     */
    List<Event> findAllByUsername(String username);
    /**
     * Returns events which are created between startDate and endDate
     * @param startDate
     * @param endDate
     * @return
     */
    @Query("from Event where dateCreated between :startDate and : endDate")
    List<Event> findByDateCreatedBetween(@Param("startDate") LocalDateTime startDate,
                                         @Param("endDate") LocalDateTime endDate);

    /**
     * Returns events of a user by username which are created between startDate and endDate
     * @param username
     * @param startDate
     * @param endDate
     * @return
     */
    @Query("from Event where User.username = :username and dateCreated between :startDate and : endDate")
    List<Event> findByUsernameAndDateCreatedBetween(@Param("username") String username,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate);
}
