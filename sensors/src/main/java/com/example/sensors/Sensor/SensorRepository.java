package com.example.sensors.Sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Sensor Entity.
 */
@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
