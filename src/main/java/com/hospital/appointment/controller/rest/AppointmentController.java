package com.hospital.appointment.controller.rest;

import com.hospital.appointment.constant.UserType;
import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentSchedulerDTO;
import com.hospital.appointment.model.AppointmentEntity;
import com.hospital.appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/appointment/")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;


    @PostMapping("add")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentDTO payload, Principal principal) {

        String username = principal.getName();

        AppointmentEntity appointmentEntity = appointmentService.createAppointment(payload, username);
        return ResponseEntity.ok().body(appointmentEntity);
    }

    @GetMapping("patient")
    @PreAuthorize("hasAnyRole('PATIENT','PROVIDER')")
    public ResponseEntity<List<AppointmentDTO>> getPatientAppointments(Principal principal) {

        String username = principal.getName();

        List<AppointmentDTO> appointments = appointmentService.getAppointment(username);
        return ResponseEntity.ok().body(appointments);
    }

    @PutMapping("assign/{id}")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<AppointmentEntity> assignAppointments(@PathVariable("id")Long appointmentId, Principal principal) {

        String username = principal.getName();

        AppointmentEntity appointment = appointmentService.assignAppointment(appointmentId,username);
        return ResponseEntity.ok().body(appointment);
    }

    @PutMapping("shchedule/{id}")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<AppointmentEntity> changeAppointments(@RequestBody AppointmentSchedulerDTO appointmentSchedulerDTO, Principal principal) {

//        String username = principal.getName();
//
//        AppointmentEntity appointment = appointmentService.assignAppointment(appointmentId,username);
        return null;
    }

}