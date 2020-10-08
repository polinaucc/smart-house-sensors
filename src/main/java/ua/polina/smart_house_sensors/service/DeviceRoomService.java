package ua.polina.smart_house_sensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.smart_house_sensors.entity.DeviceParameter;
import ua.polina.smart_house_sensors.entity.DeviceRoom;
import ua.polina.smart_house_sensors.entity.State;
import ua.polina.smart_house_sensors.exception.NoParameterException;
import ua.polina.smart_house_sensors.repository.DeviceParameterRepository;
import ua.polina.smart_house_sensors.repository.DeviceRoomRepository;

import java.time.LocalTime;
import java.util.List;

/**
 * The Device room service.
 */
@Service
public class DeviceRoomService {
    /**
     * The Device room repository.
     */
    DeviceRoomRepository deviceRoomRepository;
    /**
     * The Device parameter repository.
     */
    DeviceParameterRepository deviceParameterRepository;

    /**
     * Instantiates a new Device room service.
     *
     * @param deviceRoomRepository      the device room repository
     * @param deviceParameterRepository the device parameter repository
     */
    @Autowired
    public DeviceRoomService(DeviceRoomRepository deviceRoomRepository,
                             DeviceParameterRepository deviceParameterRepository) {
        this.deviceRoomRepository = deviceRoomRepository;
        this.deviceParameterRepository = deviceParameterRepository;
    }

    /**
     * Turns on device in the room.
     *
     * @param deviceRoomId the device room id
     * @return the device room
     * @throws IllegalArgumentException if there is no device room with such id
     * @throws NoParameterException     if the device has no parameters to be
     *                                  on with
     */
    public DeviceRoom onDevice(Long deviceRoomId) throws NoParameterException {
        DeviceRoom deviceRoom = deviceRoomRepository.findById(deviceRoomId)
                .orElseThrow(() -> new IllegalArgumentException("No such device"));
        if (!deviceParameterRepository.findByRoomDevice(deviceRoom).isEmpty()) {
            deviceRoom.setState(State.ON);
            deviceRoom.setOnTime(LocalTime.now());
            for (DeviceParameter dp :
                    deviceParameterRepository.findByRoomDevice(deviceRoom)) {
                if (dp.getValue() == null) {
                    dp.setValue(dp.getMinTheoreticalValue());
                }
            }
            return deviceRoomRepository.save(deviceRoom);
        } else {
            throw new NoParameterException("No parameters to turn on the device");
        }
    }

    /**
     * Turns off the device in the room.
     *
     * @param deviceRoomId the device room id
     * @return the device room
     * @throws IllegalArgumentException if deviceRoom with such id doesn't
     *                                  exist.
     */
    public DeviceRoom offDevice(Long deviceRoomId) {
        DeviceRoom deviceRoom = deviceRoomRepository.findById(deviceRoomId)
                .orElseThrow(() -> new IllegalArgumentException("No such device"));
        deviceRoom.setState(State.OFF);
        deviceRoom.setOffTime(LocalTime.now());
        return deviceRoomRepository.save(deviceRoom);
    }
}
