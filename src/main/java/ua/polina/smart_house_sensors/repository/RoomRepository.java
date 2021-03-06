package ua.polina.smart_house_sensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.smart_house_sensors.entity.House;
import ua.polina.smart_house_sensors.entity.Room;

import java.util.List;

/**
 * Room repository.
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHouse(House house);

}
