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
     * Returns all events for a user by username
     * @param userId
     * @return
     */
    @Query("from Event  where userId = :userId")
    List<Event> findAllByUsername(String userId);
    /**
     * Returns events which are created between startDate and endDate
     * @param startDate
     * @param endDate
     * @return
     */
    @Query("from Event where eventTime between :startDate and :endDate")
    List<Event> findByDateCreatedBetween(@Param("startDate") Long startDate,
                                         @Param("endDate") Long endDate);

    /**
     * Returns events for a user by username which are created between startDate and endDate
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */

    @Query("from Event where userId = :userId and eventTime between :startDate and :endDate")
    List<Event> findByUsernameAndDateCreatedBetween(@Param("userId") String userId,
                                                    @Param("startDate") Long startDate,
                                                    @Param("endDate") Long endDate);
}
