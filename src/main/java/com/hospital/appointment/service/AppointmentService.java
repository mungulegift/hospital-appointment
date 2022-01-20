package com.hospital.appointment.service;


import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.UserDTO;
import com.hospital.appointment.model.AppointmentEntity;
import com.hospital.appointment.model.ProviderEntity;
import com.hospital.appointment.model.UserEntity;

import java.util.List;

public interface AppointmentService {

    /**+
     * This method is used to add new appointments
     * @param appointmentDTO
     */
    AppointmentEntity createAppointment (AppointmentDTO appointmentDTO, String username);
    List<AppointmentDTO> getAppointment (String username);

    AppointmentEntity assignAppointment(Long appointmentId, String username);

}
