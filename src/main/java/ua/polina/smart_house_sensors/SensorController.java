package ua.polina.smart_house_sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.polina.smart_house_sensors.api.DeviceRoomListApi;
import ua.polina.smart_house_sensors.service.DeviceRoomService;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    DeviceRoomService deviceRoomService;

    @Autowired
    public SensorController(DeviceRoomService deviceRoomService) {
        this.deviceRoomService = deviceRoomService;
    }

    @ResponseBody
    @GetMapping(value = "/get-on-devices/{room-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceRoomListApi getOnRoomDevices(@PathVariable("room-id") Long roomId) {
        return new DeviceRoomListApi(deviceRoomService.getAll());
    }
}
