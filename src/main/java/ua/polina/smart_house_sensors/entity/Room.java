package ua.polina.smart_house_sensors.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * The entity for Room presentation.
 *
 * @author Polina Serhiienko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room", nullable = false)
    private String name;

    @Column(name = "size")
    private Double size;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @OneToMany(mappedBy = "room")
    private List<DeviceRoom> deviceRooms;

    @OneToMany(mappedBy = "room")
    private List<RoomParameter> roomParameters;

}
