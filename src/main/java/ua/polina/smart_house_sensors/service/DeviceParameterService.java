package ua.polina.smart_house_sensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.polina.smart_house_sensors.api.SetUpParameterDto;
import ua.polina.smart_house_sensors.entity.DeviceParameter;
import ua.polina.smart_house_sensors.repository.DeviceParameterRepository;

/**
 * DeviceParameter Service
 */
@Service
public class DeviceParameterService {
    /**
     * The deviceParameter repository.
     */
    DeviceParameterRepository deviceParameterRepository;

    /**
     * Instantiates a new Device parameter service.
     *
     * @param deviceParameterRepository the device parameter repository
     */
    @Autowired
    public DeviceParameterService(DeviceParameterRepository deviceParameterRepository) {
        this.deviceParameterRepository = deviceParameterRepository;
    }

    /**
     * Sets up a new value for device parameter, if it is more than minimum
     * value and less than maximum value.
     *
     * @param setUpParameterDto the set up parameter dto
     * @return saved device parameter
     */
    public DeviceParameter setUpParameterValue(SetUpParameterDto setUpParameterDto) {
        DeviceParameter deviceParameter
                = deviceParameterRepository.findById(setUpParameterDto.getParameterId())
                .orElseThrow(() -> new IllegalArgumentException("No device parameter with such id"));
        if (setUpParameterDto.getValue() < deviceParameter.getMinTheoreticalValue()) {
            deviceParameter.setValue(deviceParameter.getMinTheoreticalValue());
        } else if (setUpParameterDto.getValue() > deviceParameter.getMaxTheoreticalValue()) {
            deviceParameter.setValue(deviceParameter.getMaxTheoreticalValue());
        } else {
            deviceParameter.setValue(setUpParameterDto.getValue());
        }
        return deviceParameterRepository.save(deviceParameter);
    }

}
