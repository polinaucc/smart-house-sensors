package ua.polina.smart_house_sensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.smart_house_sensors.api.SetUpParameterDto;
import ua.polina.smart_house_sensors.entity.DeviceParameter;
import ua.polina.smart_house_sensors.repository.DeviceParameterRepository;

@Service
public class DeviceParameterService {
    DeviceParameterRepository deviceParameterRepository;

    @Autowired
    public DeviceParameterService(DeviceParameterRepository deviceParameterRepository) {
        this.deviceParameterRepository = deviceParameterRepository;
    }

    public DeviceParameter setUpParameterValue(SetUpParameterDto setUpParameterDto){
        DeviceParameter deviceParameter
                = deviceParameterRepository.findById(setUpParameterDto.getParameterId())
                .orElseThrow(()->new IllegalArgumentException("No device parameter with such id"));
        deviceParameter.setValue(setUpParameterDto.getValue());
        return deviceParameterRepository.save(deviceParameter);
    }
}
