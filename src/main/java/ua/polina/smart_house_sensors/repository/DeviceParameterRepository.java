package ua.polina.smart_house_sensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.smart_house_sensors.entity.DeviceParameter;

/**
 * DeviceParameter repository.
 */
public interface DeviceParameterRepository extends JpaRepository<DeviceParameter, Long> {
}
