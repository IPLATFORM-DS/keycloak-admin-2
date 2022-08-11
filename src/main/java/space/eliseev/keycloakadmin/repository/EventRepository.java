package space.eliseev.keycloakadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import space.eliseev.keycloakadmin.entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    @Query("from Event where userId = :username")
    List<Event> findAllByUsername(@Param("username") String username);

    @Query("from Event where eventTime between :startDate and :endDate")
    List<Event> findByDateCreatedBetween(@Param("startDate") Long startDate,
                                         @Param("endDate") Long endDate);

    @Query("from Event where userId = :username and eventTime between :startDate and :endDate")
    List<Event> findByUserIdeAndDateCreatedBetween(@Param("username") String username,
                                                    @Param("startDate") Long startDate,
                                                    @Param("endDate") Long endDate);
}
