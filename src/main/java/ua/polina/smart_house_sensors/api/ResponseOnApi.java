package ua.polina.smart_house_sensors.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * The object to transfer the result of turning on the device operation.
 */
@Data
@AllArgsConstructor
public class ResponseOnApi {
    /**
     * True if the operation was successful.
     */
    private Boolean isSuccess;

    /**
     * Http status of operation.
     */
    private HttpStatus httpStatus;
}
