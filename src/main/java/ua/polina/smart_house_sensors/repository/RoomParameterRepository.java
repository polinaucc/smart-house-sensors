package ua.polina.smart_house_sensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.smart_house_sensors.entity.Room;
import ua.polina.smart_house_sensors.entity.RoomParameter;

import java.util.Optional;

public interface RoomParameterRepository extends JpaRepository<RoomParameter, Long> {
    Optional<RoomParameter> findByRoom(Room room);
}
