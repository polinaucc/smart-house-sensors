package ua.polina.smart_house_sensors.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.polina.smart_house_sensors.entity.DeviceRoom;

import java.util.List;

@Data
@AllArgsConstructor
public class DeviceRoomListApi {
    List<DeviceRoom> deviceRoomList;
}
