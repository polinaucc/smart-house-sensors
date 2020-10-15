package ua.polina.smart_house_sensors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ua.polina.smart_house_sensors.api.MessageList;
import ua.polina.smart_house_sensors.api.ResponseOnApi;
import ua.polina.smart_house_sensors.api.RoomParametersApi;
import ua.polina.smart_house_sensors.api.SetUpParameterDto;
import ua.polina.smart_house_sensors.entity.DeviceParameter;
import ua.polina.smart_house_sensors.entity.House;
import ua.polina.smart_house_sensors.entity.RoomParameter;
import ua.polina.smart_house_sensors.exception.NoParameterException;
import ua.polina.smart_house_sensors.service.DeviceParameterService;
import ua.polina.smart_house_sensors.service.DeviceRoomService;
import ua.polina.smart_house_sensors.service.RoomParameterService;

/**
 * The type Sensor controller.
 */
@RestController
@RequestMapping("/sensor")
public class SensorController {
    private static final Logger LOGGER = LogManager.getLogger(SensorController.class);
    /**
     * The Device room service.
     */
    DeviceRoomService deviceRoomService;
    /**
     * The Device parameter service.
     */
    DeviceParameterService deviceParameterService;

    /**
     * The room parameter service.
     */
    RoomParameterService roomParameterService;

    /**
     * Instantiates a new Sensor controller.
     *
     * @param deviceRoomService      the device room service
     * @param deviceParameterService the device parameter service
     */
    @Autowired
    public SensorController(DeviceRoomService deviceRoomService,
                            DeviceParameterService deviceParameterService,
                            RoomParameterService roomParameterService) {
        this.deviceRoomService = deviceRoomService;
        this.deviceParameterService = deviceParameterService;
        this.roomParameterService = roomParameterService;
    }

    /**
     * Turns on the device in the room.
     *
     * @param deviceRoomId the device room id
     * @return the response on api
     */
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

    /**
     * Turns off the device in the room.
     *
     * @param deviceRoomId the device room id
     * @return true, if the device turned off successfully
     */
    @ResponseBody
    @GetMapping("/off-device/{device-room-id}")
    public Boolean offDevice(@PathVariable("device-room-id") Long deviceRoomId) {
        if (deviceRoomService.offDevice(deviceRoomId) != null) return true;
        else return false;
    }

    /**
     * Sets up new value for device parameter.
     *
     * @param setUpParameterDto the set up parameter dto
     * @return the device parameter
     */
    @ResponseBody
    @PostMapping("/set-up-parameter-value")
    public DeviceParameter setUpParameterValue(@RequestBody SetUpParameterDto setUpParameterDto) {
        try {
            return deviceParameterService.setUpParameterValue(setUpParameterDto);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Sets up room parameters.
     *
     * @param roomParametersApi
     */
    @ResponseBody
    @PostMapping("set-up-room-parameters")
    public void setUpRoomParameters(@RequestBody RoomParametersApi roomParametersApi) {
        roomParameterService.save(roomParametersApi);
    }

    /**
     * Simulates the fire. Sets up room parameters` values when fire.
     *
     * @param roomId the room id
     */
    @ResponseBody
    @GetMapping("simulate-fire/{room-id}")
    public void simulateFire(@PathVariable("room-id") Long roomId) {
        roomParameterService.fire(roomId);
    }

    /**
     * Simulates the flood. Sets up room parameters` values when flood.
     *
     * @param roomId the room id
     */
    @ResponseBody
    @GetMapping("simulate-flood/{room-id}")
    public void simulateFlood(@PathVariable("room-id") Long roomId) {
        roomParameterService.flood(roomId);
    }

    /**
     * Simulates the open window. Sets up room parameters` values when open
     * window.
     *
     * @param roomId the room id
     */
    @ResponseBody
    @GetMapping("simulate-open-window/{room-id}")
    public void simulateOpenWindow(@PathVariable("room-id") Long roomId) {
        roomParameterService.openWindow(roomId);
    }

    //TODO: filter rooms by the house.

    /**
     * Check rooms for emergencies.
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/check")
    public MessageList checkForEmergencies(@RequestBody House house) {
        for (RoomParameter rp : roomParameterService.getRoomParametersByHouse(house)) {
            LOGGER.info(rp);
        }
        return new MessageList(roomParameterService.checkForEmergency(house));
    }
}
