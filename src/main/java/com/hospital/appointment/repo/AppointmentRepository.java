package com.hospital.appointment.repo;

import com.hospital.appointment.model.AppointmentEntity;
import com.hospital.appointment.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByPatient(PatientEntity patientEntity);
}
