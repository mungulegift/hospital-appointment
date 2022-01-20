package com.hospital.appointment.service;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.model.AppointmentEntity;
import com.hospital.appointment.model.PatientEntity;
import com.hospital.appointment.model.ProviderEntity;
import com.hospital.appointment.repo.AppointmentRepository;
import com.hospital.appointment.repo.PatientRepository;
import com.hospital.appointment.repo.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ProviderRepository providerRepository;


    @Override
    public AppointmentEntity createAppointment(AppointmentDTO appointmentDTO, String username) {

        PatientEntity patientEntity = patientRepository.findByUsername(username);

        AppointmentEntity appointmentEntity = new AppointmentEntity();

        appointmentEntity.setPatient(patientEntity);

        LocalDateTime localDateTime = LocalDateTime.of(appointmentDTO.getDate(), appointmentDTO.getTime());

        appointmentEntity.setAppointmentDate(localDateTime);
        appointmentEntity.setReason(appointmentDTO.getReason());

        AppointmentEntity  savedAppointment = appointmentRepository.save(appointmentEntity);

        return savedAppointment;
    }

    @Override
    public List<AppointmentDTO> getAppointment(String username) {

        PatientEntity patientEntity = patientRepository.findByUsername(username);

        if(patientEntity == null){
            ProviderEntity providerEntity = providerRepository.findByUsername(username);

            if(providerEntity != null){
               return appointmentRepository.findAll()
                        .stream().map(this::getAppointmentDTO).collect(Collectors.toList());
            }
        }

        return appointmentRepository.findByPatient(patientEntity).stream().map(this::getAppointmentDTO)
        .collect(Collectors.toList());
    }

    private AppointmentDTO getAppointmentDTO(AppointmentEntity appointmentEntity) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setPatient(appointmentEntity.getPatient());
        appointmentDTO.setProvider(appointmentEntity.getProvider());
        appointmentDTO.setReason(appointmentEntity.getReason());
        appointmentDTO.setDate(appointmentEntity.getAppointmentDate().toLocalDate());
        appointmentDTO.setTime(appointmentEntity.getAppointmentDate().toLocalTime());
        appointmentDTO.setId(appointmentEntity.getId());
        return appointmentDTO;
    }

    @Override
    public AppointmentEntity assignAppointment(Long appointmentId, String username) {
        Optional<AppointmentEntity> appointmentEntityOpt = appointmentRepository.findById(appointmentId);
        ProviderEntity providerEntity = providerRepository.findByUsername(username);

        if(appointmentEntityOpt.isPresent() && providerEntity != null){
            AppointmentEntity appointmentEntity = appointmentEntityOpt.get();

            appointmentEntity.setProvider(providerEntity);

            AppointmentEntity savedAppointment = appointmentRepository.save(appointmentEntity);
            return savedAppointment;
        }
        return null;
    }
}
