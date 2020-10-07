package ua.polina.smart_house_sensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.smart_house_sensors.entity.House;

/**
 * House repository.
 */
public interface HouseRepository extends JpaRepository<House, Long> {
}
