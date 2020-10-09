package ua.polina.smart_house_sensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.smart_house_sensors.api.RoomParametersApi;
import ua.polina.smart_house_sensors.entity.Room;
import ua.polina.smart_house_sensors.entity.RoomParameter;
import ua.polina.smart_house_sensors.repository.RoomParameterRepository;

@Service
public class RoomParameterService {
    RoomParameterRepository roomParameterRepository;

    @Autowired
    public RoomParameterService(RoomParameterRepository roomParameterRepository) {
        this.roomParameterRepository = roomParameterRepository;
    }

    public RoomParameter save(RoomParametersApi roomParametersApi) {
        RoomParameter roomParameter = RoomParameter.builder()
                .room(roomParametersApi.getRoom())
                .temperature(roomParametersApi.getRoomParameterDto().getTemperature())
                .humidity(roomParametersApi.getRoomParameterDto().getHumidity())
                .smokeLevel(roomParametersApi.getRoomParameterDto().getSmokeLevel())
                .waterLevel(roomParametersApi.getRoomParameterDto().getWaterLevel())
                .build();

        return roomParameterRepository.save(roomParameter);
    }
}
