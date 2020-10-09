package ua.polina.smart_house_sensors.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

/**
 * The dto for room parameter object.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomParameterDto {
    private Double temperature;
    private Double humidity;
    private Double smokeLevel;
    private Double waterLevel;
}