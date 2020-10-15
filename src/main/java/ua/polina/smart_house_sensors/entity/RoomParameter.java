package ua.polina.smart_house_sensors.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * The entity for Room parameter.
 *
 * @author Polina Serhiienko
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "room_parameter")
public class RoomParameter {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "temparature")
    private Double temperature;

    @Column(name = "humidity")
    private Double humidity;

    @Column(name = "smole_level")
    private Double smokeLevel;

    @Column(name = "waterLevel")
    private Double waterLevel;

    @OneToOne
    @JoinColumn(name = "room_id", unique = true)
    private Room room;

    @Override
    public String toString() {
        return "RoomParameter{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", smokeLevel=" + smokeLevel +
                ", waterLevel=" + waterLevel +
                ", room=" + room.getName() +
                ", house=" + room.getHouse() +
                '}';
    }
}
