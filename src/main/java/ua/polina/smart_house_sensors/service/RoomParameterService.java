package ua.polina.smart_house_sensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.smart_house_sensors.api.RoomParameterDto;
import ua.polina.smart_house_sensors.api.RoomParametersApi;
import ua.polina.smart_house_sensors.entity.Room;
import ua.polina.smart_house_sensors.entity.RoomParameter;
import ua.polina.smart_house_sensors.repository.RoomParameterRepository;
import ua.polina.smart_house_sensors.repository.RoomRepository;

import java.util.List;

@Service
public class RoomParameterService {
    RoomParameterRepository roomParameterRepository;
    RoomRepository roomRepository;

    @Autowired
    public RoomParameterService(RoomParameterRepository roomParameterRepository,
                                RoomRepository roomRepository) {
        this.roomParameterRepository = roomParameterRepository;
        this.roomRepository = roomRepository;
    }

    public RoomParameter save(RoomParametersApi roomParametersApi) {
        RoomParameter roomParameter = new RoomParameter();
        if (roomParameterRepository.findByRoom(roomParametersApi.getRoom()).isEmpty()) {
            roomParameter = RoomParameter.builder()
                    .room(roomParametersApi.getRoom())
                    .temperature(roomParametersApi.getRoomParameterDto().getTemperature())
                    .humidity(roomParametersApi.getRoomParameterDto().getHumidity())
                    .smokeLevel(roomParametersApi.getRoomParameterDto().getSmokeLevel())
                    .waterLevel(roomParametersApi.getRoomParameterDto().getWaterLevel())
                    .build();
        } else {
            roomParameter = roomParameterRepository
                    .findByRoom(roomParametersApi.getRoom())
                    .get();
            roomParameter.setTemperature(roomParametersApi.getRoomParameterDto().getTemperature());
            roomParameter.setHumidity(roomParametersApi.getRoomParameterDto().getHumidity());
            roomParameter.setSmokeLevel(roomParametersApi.getRoomParameterDto().getSmokeLevel());
            roomParameter.setWaterLevel(roomParametersApi.getRoomParameterDto().getWaterLevel());
        }
        return roomParameterRepository.save(roomParameter);
    }

    public RoomParameter fire(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("No room with such id"));
        RoomParametersApi roomParametersApi = RoomParametersApi.builder()
                .room(room)
                .roomParameterDto(new RoomParameterDto(
                        50.0, 10.0, 90.0, 0.0))
                .build();
        return save(roomParametersApi);
    }

    public String checkForEmergency(){
        List<RoomParameter> roomParameters = roomParameterRepository.findAll();
        for (RoomParameter rp: roomParameters) {
            if(rp.getSmokeLevel()>=85){
                return "FIRE in " + rp.getRoom().getName();
            }
        }
        return "";
    }
}
