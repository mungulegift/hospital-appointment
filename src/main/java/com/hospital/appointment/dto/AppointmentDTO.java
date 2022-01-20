package com.hospital.appointment.dto;


import com.hospital.appointment.model.PatientEntity;
import com.hospital.appointment.model.ProviderEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDTO {

    private Long id;
    private String reason;
    private LocalDate date;
    private LocalTime time;
    ProviderEntity provider;
    PatientEntity patient;
}
