package com.hospital.appointment.controller.rest;

import com.hospital.appointment.constant.UserType;
import com.hospital.appointment.dto.UserDTO;
import com.hospital.appointment.model.ProviderEntity;
import com.hospital.appointment.model.UserEntity;
import com.hospital.appointment.service.AddUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;


/**
* Class to handle user creation and user management apis
* */
@RestController
@RequestMapping("api/user/")
@RequiredArgsConstructor
public class UserController {

    private final AddUserService addUserService;

 /**
 * Start: api for patient sign up
  * @param userDto
 * */

    @PostMapping("patient/add")
    public UserEntity addPatient(@RequestBody UserDTO userDto) {

        if(UserType.PROVIDER.getId() == userDto.getUserType()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return  addUserService.addUser(userDto);
    }
/**
* End: api for patient sign up
* */


/**
* Start: api for adding a doctor/provider by admin
 * @param userDto
* */
    @PostMapping("provider/add")
    @PreAuthorize("hasRole('ADMIN')")
    public UserEntity addProvider(@RequestBody UserDTO userDto) {

        return  addUserService.addUser(userDto);
    }
/**
* End: api for adding a doctor/provider by admin
* */

/**
* Start: api to retrieve all doctors/providers by admin
* */
    @GetMapping("provider/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProviderEntity>> getProvider() {

        List<ProviderEntity> providers = addUserService.getProviders();
        return ResponseEntity.ok().body(providers);
    }
/**
 * End: api to retrieve all doctors/providers by admin
 * */

/**
*Start: api to get the current logged user
 * @param principal
* */
    @GetMapping("name")
    @PreAuthorize("hasAnyRole('PATIENT','PROVIDER','ADMIN')")
    public ResponseEntity<UserDTO> currentUserName(Principal principal) {

        String username = principal.getName();
        if(username != null) {
            UserDTO userDTO = addUserService.getUser(username);
            return ResponseEntity.ok().body(userDTO);
        }else {
            return null;
        }
    }

/**
* End: api to get the current logged user
* */

}

/**
* End of class
* */