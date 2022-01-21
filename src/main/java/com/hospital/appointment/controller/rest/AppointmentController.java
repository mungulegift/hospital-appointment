package com.hospital.appointment.controller.rest;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentSchedulerDTO;
import com.hospital.appointment.model.AppointmentEntity;
import com.hospital.appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


/**
* Class to handle the appointment scheduling apis
* */

@RestController
@RequestMapping("api/appointment/")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

/**
* Start: api to create an Appointment by Patient
 *@param payload
 *@param principal
* */
    @PostMapping("add")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentDTO payload, Principal principal) {

        String username = principal.getName();

        AppointmentEntity appointmentEntity = appointmentService.createAppointment(payload, username);
        return ResponseEntity.ok().body(appointmentEntity);
    }

/**
* End:api to create an  appointment by patient
* */


/**
* Start: An endpoint to get appointments submitted
 * @param principal
* */
    @GetMapping("patient")
    @PreAuthorize("hasAnyRole('PATIENT','PROVIDER')")
    public ResponseEntity<List<AppointmentDTO>> getPatientAppointments(Principal principal) {

        String username = principal.getName();

        List<AppointmentDTO> appointments = appointmentService.getAppointment(username);
        return ResponseEntity.ok().body(appointments);
    }

/**
* End: Endpoint to get appointments
* */


/**
 * Start: api for doctors/providers to assign an appointment submitted by patients
 * @param principal
 * @param appointmentId
 * */
    @PutMapping("assign/{id}")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<AppointmentEntity> assignAppointments(@PathVariable("id")Long appointmentId, Principal principal) {

        String username = principal.getName();

        AppointmentEntity appointment = appointmentService.assignAppointment(appointmentId,username);
        return ResponseEntity.ok().body(appointment);
    }
/**
* End: api for doctors/providers to assign an appointment submitted by patients
* */



/**
* Start: api for doctors/providers to change appointments submitted by patients
 * @param principal
* */
    @PutMapping("shchedule/{id}")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<AppointmentEntity> changeAppointments(@RequestBody AppointmentSchedulerDTO appointmentSchedulerDTO, Principal principal) {
        return null;
    }
 /**
 * End: api for doctors/providers to change appointments submitted by patients
 * */

}

/**
*  End of class
* */