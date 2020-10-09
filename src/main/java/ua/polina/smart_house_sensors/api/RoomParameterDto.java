package ua.polina.smart_house_sensors.api;

import lombok.Data;

@Data
public class RoomParameterDto {
    public Double temperature;
    public Double humidity;
    public Double smokeLevel;
    public Double waterLevel;
}
