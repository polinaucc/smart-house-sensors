package ua.polina.smart_house_sensors.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.smart_house_sensors.entity.Room;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomParametersApi {
    private RoomParameterDto roomParameterDto;
    private Room room;
}
