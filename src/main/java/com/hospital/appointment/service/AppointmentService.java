package com.hospital.appointment.service;


import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.model.AppointmentEntity;


import java.util.List;

public interface AppointmentService {

    /**+
     * An interface to manage appointment schedule
     * @param appointmentDTO
     * @param username
     */
    AppointmentEntity createAppointment (AppointmentDTO appointmentDTO, String username);
    List<AppointmentDTO> getAppointment (String username);

    AppointmentEntity assignAppointment(Long appointmentId, String username);

}
