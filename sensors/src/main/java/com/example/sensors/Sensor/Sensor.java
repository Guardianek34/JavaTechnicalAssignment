package com.example.sensors.Sensor;
import com.example.sensors.Engine.Engine;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Sensor Entity containing information about the Sensor.
 * Uses self-reference in order to model hierarchy between sensors
 * (in this example pressure sensors uses temperature sensors to compute -
 * at least that was my assumption while thinking about the problem itself)
 *
 * All the relations to other entities are bidirectional.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Sensor")
public class Sensor {


    /**
     * Id of the object (not generated automatically)
     */
    @Id
    private Long id;

    /**
     * Type of the object (in the example can be
     * "pressure" or "temperature")
     */
    private String type;

    /**
     * Value measured by the sensor.
     */
    private Integer value;

    /**
     * Maximum value that cannot be exceeded.
     */
    private Integer max_value;

    /**
     * Minimum value that cannot be exceeded.
     */
    private Integer min_value;

    /**
     * Reference to Engine - every Sensor is related to
     * an Engine.
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Engine engine;

    /**
     * Self-reference - in our example Temperature Sensors
     * got reference to the pressure ones.
     */
    @JsonIgnore
    @ManyToOne
    @Nullable
    private Sensor parentSensor;

    /**
     * Self-reference - in our hierarchy, Pressure Sensors
     * got reference to all of the Temperature Sensors.
     */
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "parentSensor", fetch = FetchType.LAZY)
    @Nullable
    private List<Sensor> childSensors = new ArrayList<>();

    /**
     * Using @Version annotation in order to create an Optimistic Lock.
     * So that ID is being interpreted correctly when added manually.
     */
    @JsonIgnore
    @Version Integer version;

    /**
     * Constructor without fields about self-relation. We manage these fields
     * by our utility methods.
     * @param id Id
     * @param type Type of the sensor
     * @param value Measured value
     * @param max_value Maximum measurable value
     * @param min_value Minimum measurable value
     * @param engine Relation to the engine object
     */
    public Sensor(Long id, String type, Integer value, Integer max_value, Integer min_value, Engine engine) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.max_value = max_value;
        this.min_value = min_value;
        this.engine = engine;
    }

    /**
     * Utility function to add the child to the Sensor.
     * It adds the parent to the child.
     * @param child Child object.
     */
    public void addChildSensor(Sensor child){
        this.childSensors.add(child);
        child.setParentSensor(this);
    }

    /**
     * Utility function to add the parent to the child.
     * It adds the child to the parent.
     * @param parent Parent Object.
     */
    public void addParentSensor(Sensor parent){
        this.parentSensor = parent;
        parent.childSensors.add(this);
    }

    /**
     * Utility function to add the engine to the sensor.
     * @param engine Engine object.
     */
    public void addEngine(Engine engine){
        engine.getSensors().add(this);
        this.engine = engine;
    }
}
