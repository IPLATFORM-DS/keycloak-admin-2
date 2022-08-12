package space.eliseev.keycloakadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import space.eliseev.keycloakadmin.entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    /**
     * Returns events which are created between startDate and endDate
     * @param startDate start date
     * @param endDate end Date
     * @return list of events
     */
    @Query("from Event where eventTime between :startDate and :endDate")
    List<Event> findByDateCreatedBetween(@Param("startDate") Long startDate,
                                         @Param("endDate") Long endDate);

    /**
     * Returns events for a user by username which are created between startDate and endDate
     * @param username user username
     * @param startDate start date
     * @param endDate end Date
     * @return list of events
     */
    @Query("from Event where user.username = :username and eventTime between :startDate and :endDate")
    List<Event> findByUsernameAndDateCreatedBetween(@Param("username") String username,
                                                    @Param("startDate") Long startDate,
                                                    @Param("endDate") Long endDate);

    /**
     * Returns events for a user by username
     * @param username user username
     * @return list of events
     */
    @Query("from Event where user.username = :username")
    List<Event> findAllByUsername(@Param("username") String username);
}
