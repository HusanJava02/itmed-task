package com.exmple.it_med_task.repository;

import com.exmple.it_med_task.entites.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByIdentifierSystemAndIdentifierValue(String system, String value);
}
