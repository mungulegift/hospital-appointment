package com.hospital.appointment.service;

import com.hospital.appointment.constant.UserType;
import com.hospital.appointment.dto.UserDTO;
import com.hospital.appointment.model.PatientEntity;
import com.hospital.appointment.model.ProviderEntity;
import com.hospital.appointment.model.RoleEntity;
import com.hospital.appointment.model.UserEntity;
import com.hospital.appointment.repo.PatientRepository;
import com.hospital.appointment.repo.ProviderRepository;
import com.hospital.appointment.repo.RoleRepository;
import com.hospital.appointment.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AddUserServiceImpl implements AddUserService{

    private final ProviderRepository providerRepository;
    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserEntity addUser(UserDTO userDTO) {

        final int userType = userDTO.getUserType();
        UserEntity userEntity = new UserEntity();

        userEntity.setUserType(userType);
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setEnabled(true);

        if(userType == UserType.PROVIDER.getId()){

            userEntity.setRoles(createRoles(UserType.PROVIDER.name()));

            ProviderEntity providerEntity = new ProviderEntity();
            providerEntity.setFirstName(userDTO.getFirstName());
            providerEntity.setLastName(userDTO.getLastName());
            providerEntity.setSpecialty(userDTO.getSpecialty());
            providerEntity.setUserEntity(userEntity);
            ProviderEntity savedProviderEntity = providerRepository.save(providerEntity);
            return savedProviderEntity.getUserEntity();
        } else if(userType == UserType.PATIENT.getId()){

            userEntity.setRoles(createRoles(UserType.PATIENT.name()));
            PatientEntity patientEntity = new PatientEntity();
            patientEntity.setFirstName(userDTO.getFirstName());
            patientEntity.setLastName(userDTO.getLastName());
            patientEntity.setUserEntity(userEntity);
            PatientEntity savedPatientEntity = patientRepository.save(patientEntity);
            return savedPatientEntity.getUserEntity();
        }

        return null;
    }

    @Override
    public UserDTO getUser(String username) {

       UserEntity userEntity = userRepository.getUserByUsername(username);

        UserDTO userDTO = new UserDTO();

        if(UserType.ADMIN.name().equalsIgnoreCase(username)){

            userDTO.setUsername(username);
            userDTO.setUserType(UserType.ADMIN.getId());
        }else if(userEntity == null){
            return null;
        } else if (UserType.PROVIDER.getId() == userEntity.getUserType()) {

            ProviderEntity provider = providerRepository.findByUserEntity(userEntity);

            userDTO.setFirstName(provider.getFirstName());
            userDTO.setLastName(provider.getLastName());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setUserType(userEntity.getUserType());
            userDTO.setSpecialty(provider.getSpecialty());
        } else if (UserType.PATIENT.getId() == userEntity.getUserType()) {

            PatientEntity patientEntity = patientRepository.findByUserEntity(userEntity);

            userDTO.setFirstName(patientEntity.getFirstName());
            userDTO.setLastName(patientEntity.getLastName());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setUserType(userEntity.getUserType());
        }

        return userDTO;


    }

    @Override
    public List<ProviderEntity> getProviders() {

        return  providerRepository.findAll();
    }

    private Set<RoleEntity> createRoles(String roleName) {

        RoleEntity roleEntity = roleRepository.findByName(roleName);
        Set<RoleEntity> roleEntitySet = new HashSet<>();

        if(roleEntity == null) {
            RoleEntity role = new RoleEntity();
            role.setName(roleName);
            roleEntitySet.add(role);

            return roleEntitySet;
        }else {
            roleEntitySet.add(roleEntity);
            return roleEntitySet;
        }


    }
}
