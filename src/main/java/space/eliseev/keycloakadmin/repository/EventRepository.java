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
     * Returns all events for a user by userId
     * @param userId user identification
     * @return list of events
     */
    List<Event> findAllByUserId(String userId);
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
     * @param userId user identification
     * @param startDate start date
     * @param endDate end Date
     * @return list of events
     */
    @Query("from Event where userId = :userId and eventTime between :startDate and :endDate")
    List<Event> findByUserIdeAndDateCreatedBetween(@Param("userId") String userId,
                                                    @Param("startDate") Long startDate,
                                                    @Param("endDate") Long endDate);
}
