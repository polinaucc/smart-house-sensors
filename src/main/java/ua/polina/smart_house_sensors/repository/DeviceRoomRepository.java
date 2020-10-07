package ua.polina.smart_house_sensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.smart_house_sensors.entity.DeviceRoom;

import java.util.List;

/**
 * The Device room repository.
 */
public interface DeviceRoomRepository extends JpaRepository<DeviceRoom, Long> {
}
