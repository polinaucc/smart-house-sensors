package ua.polina.smart_house_sensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.smart_house_sensors.entity.RoomParameter;

public interface RoomParameterRepository extends JpaRepository<RoomParameter, Long> {
}
