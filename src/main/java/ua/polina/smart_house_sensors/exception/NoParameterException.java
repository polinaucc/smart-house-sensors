package ua.polina.smart_house_sensors.exception;

/**
 * The type No parameter exception. It can be used when the device has no
 * parameters to be turned on,
 */
public class NoParameterException extends Exception {
    /**
     * Instantiates a new No parameter exception.
     *
     * @param message the message
     */
    public NoParameterException(String message) {
        super(message);
    }
}
