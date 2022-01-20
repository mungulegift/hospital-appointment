package com.hospital.appointment.service;


import com.hospital.appointment.dto.UserDTO;
import com.hospital.appointment.model.ProviderEntity;
import com.hospital.appointment.model.UserEntity;

import java.util.List;

public interface AddUserService {

    /**+
     * This method is used to add new users
     * @param userDTO
     */
    UserEntity addUser(UserDTO userDTO);
    UserDTO getUser(String username);
    List<ProviderEntity> getProviders();

}
