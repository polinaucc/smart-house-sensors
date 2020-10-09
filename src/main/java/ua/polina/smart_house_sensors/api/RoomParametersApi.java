package ua.polina.smart_house_sensors.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.smart_house_sensors.entity.Room;

/**
 * The type Room parameters api to send room parameters and room.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomParametersApi {
    private RoomParameterDto roomParameterDto;
    private Room room;
}
