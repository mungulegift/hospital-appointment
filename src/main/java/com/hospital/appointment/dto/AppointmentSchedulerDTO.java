package com.hospital.appointment.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentSchedulerDTO {

    private LocalDate date;
    private LocalTime time;
}
