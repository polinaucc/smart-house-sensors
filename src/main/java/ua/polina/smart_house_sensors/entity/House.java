package ua.polina.smart_house_sensors.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * The entity for house presentation.
 *
 * @author Polina Serhiienko
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", unique = true)
    private Address address;

    @Column(name = "size")
    private Double size;

    @Column(name = "amount_of_rooms")
    private Integer amountOfRooms;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Room> rooms;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Resident> residents;

    @Override
    public String toString() {
        return "House{" +
                "address=" + address +
                ", size=" + size +
                ", amountOfRooms=" + amountOfRooms +
                '}';
    }
}
