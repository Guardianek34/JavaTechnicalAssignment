package com.example.sensors.Engine;

import com.example.sensors.Sensor.Sensor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Engine Entity
 * ID are assigned manually based on the given pressure sensor's objects.
 * All the relations to other entities are bidirectional.
 */
@Entity
@Getter
@Setter
@Table(name = "Engine")
@AllArgsConstructor
@NoArgsConstructor
public class Engine {

    /**
     * ID - manually assigned
     */
    @Id
    private Long id;

    /**
     * Engine's name
     */
    private String name;

    /**
     * Relation 1-N with different Sensors
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "engine", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sensor> sensors = new ArrayList<>();

    /**
     * Using @Version annotation in order to create an Optimistic Lock.
     * So that ID is being interpreted correctly when added manually.
     */
    @JsonIgnore
    @Version Integer version;

    /**
     * Constructor with id and name params.
     * Created due to the fact we assign sensors by using addSensor() method.
     * @param id Id
     * @param name Name
     */
    public Engine(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Utility method to map relationship correctly.
     * @param sensor Sensor to be assigned to Engine.
     */
    public void addSensor(Sensor sensor){
        this.sensors.add(sensor);
        sensor.setEngine(this);
    }

}
