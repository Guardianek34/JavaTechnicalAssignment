package com.example.sensors.Engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Engines.
 */
@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {
}
