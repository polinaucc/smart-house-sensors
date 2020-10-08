package ua.polina.smart_house_sensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.smart_house_sensors.entity.DeviceParameter;
import ua.polina.smart_house_sensors.entity.DeviceRoom;

import java.util.List;

/**
 * DeviceParameter repository.
 */
public interface DeviceParameterRepository extends JpaRepository<DeviceParameter, Long> {
    List<DeviceParameter> findByRoomDevice(DeviceRoom deviceRoom);
}
