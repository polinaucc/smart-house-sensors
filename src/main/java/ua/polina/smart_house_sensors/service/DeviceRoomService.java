package ua.polina.smart_house_sensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.smart_house_sensors.entity.DeviceRoom;
import ua.polina.smart_house_sensors.repository.DeviceRoomRepository;

import java.util.List;

@Service
public class DeviceRoomService {
    DeviceRoomRepository deviceRoomRepository;

    @Autowired
    public DeviceRoomService(DeviceRoomRepository deviceRoomRepository) {
        this.deviceRoomRepository = deviceRoomRepository;
    }

    public List<DeviceRoom> getAll(){
        return deviceRoomRepository.findAll();
    }
}
