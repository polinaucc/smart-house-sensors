package ua.polina.smart_house_sensors.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * The entity for presentation of device in the room.
 *
 * @author Polina Serhiienko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "device_room")
public class DeviceRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "on_time")
    private LocalTime onTime;

    @Column(name = "off_time")
    private LocalTime offTime;

    @Override
    public String toString() {
        return "DeviceRoom{" +
                "id=" + id +
                ", room=" + room +
                ", device=" + device +
                ", state=" + state +
                ", onTime=" + onTime +
                ", offTime=" + offTime +
                '}';
    }
}
