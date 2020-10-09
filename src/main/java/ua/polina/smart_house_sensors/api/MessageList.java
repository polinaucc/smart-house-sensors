package ua.polina.smart_house_sensors.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MessageList {
    List<String> messages;
}
