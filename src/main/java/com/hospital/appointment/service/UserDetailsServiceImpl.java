package com.hospital.appointment.service;

import com.hospital.appointment.dto.UserDetailsDTO;
import com.hospital.appointment.model.UserEntity;
import com.hospital.appointment.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.getUserByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserDetailsDTO(userEntity);
    }
}