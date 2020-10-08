package ua.polina.smart_house_sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.polina.smart_house_sensors.api.DeviceRoomListApi;
import ua.polina.smart_house_sensors.api.ResponseOnApi;
import ua.polina.smart_house_sensors.api.SetUpParameterDto;
import ua.polina.smart_house_sensors.entity.DeviceParameter;
import ua.polina.smart_house_sensors.exception.NoParameterException;
import ua.polina.smart_house_sensors.service.DeviceParameterService;
import ua.polina.smart_house_sensors.service.DeviceRoomService;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    DeviceRoomService deviceRoomService;
    DeviceParameterService deviceParameterService;

    @Autowired
    public SensorController(DeviceRoomService deviceRoomService,
                            DeviceParameterService deviceParameterService) {
        this.deviceRoomService = deviceRoomService;
        this.deviceParameterService = deviceParameterService;
    }

    @ResponseBody
    @GetMapping("/on-device/{device-room-id}")
    public ResponseOnApi onDevice(@PathVariable("device-room-id") Long deviceRoomId) {
        try {
            if (deviceRoomService.onDevice(deviceRoomId) != null)
                return new ResponseOnApi(true, HttpStatus.OK);
            else return new ResponseOnApi(false, HttpStatus.OK);
        } catch (NoParameterException e) {
            return new ResponseOnApi(false, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping("/off-device/{device-room-id}")
    public Boolean offDevice(@PathVariable("device-room-id") Long deviceRoomId) {
        if (deviceRoomService.offDevice(deviceRoomId) != null) return true;
        else return false;
    }

    @ResponseBody
    @PostMapping("/set-up-parameter-value")
    public DeviceParameter setUpParameterValue(@RequestBody SetUpParameterDto setUpParameterDto){
        try {
            return deviceParameterService.setUpParameterValue(setUpParameterDto);
        }
        catch (IllegalArgumentException e){
            return null;
        }
    }
}
