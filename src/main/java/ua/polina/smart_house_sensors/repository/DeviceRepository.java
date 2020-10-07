package ua.polina.smart_house_sensors.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.smart_house_sensors.entity.Device;

/**
 * The Device repository.
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {

}
