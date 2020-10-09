package ua.polina.smart_house_sensors.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto for setting up device parameter by admin.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetUpParameterDto {
    Long parameterId;
    Double value;
}
