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

@Service
public class DeviceRoomService {
    DeviceRoomRepository deviceRoomRepository;
    DeviceParameterRepository deviceParameterRepository;

    @Autowired
    public DeviceRoomService(DeviceRoomRepository deviceRoomRepository,
                             DeviceParameterRepository deviceParameterRepository) {
        this.deviceRoomRepository = deviceRoomRepository;
        this.deviceParameterRepository = deviceParameterRepository;
    }

    public List<DeviceRoom> getAll() {
        return deviceRoomRepository.findAll();
    }

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

    public DeviceRoom offDevice(Long deviceRoomId) {
        DeviceRoom deviceRoom = deviceRoomRepository.findById(deviceRoomId)
                .orElseThrow(() -> new IllegalArgumentException("No such device"));
        deviceRoom.setState(State.OFF);
        deviceRoom.setOffTime(LocalTime.now());
        return deviceRoomRepository.save(deviceRoom);
    }
}
