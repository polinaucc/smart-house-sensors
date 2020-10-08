package ua.polina.smart_house_sensors.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseOnApi {
    private Boolean isSuccess;
    private HttpStatus httpStatus;
}
