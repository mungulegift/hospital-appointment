package com.hospital.appointment.dto;


import lombok.Data;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String specialty;
    private String username;
    private String password;
    private int userType;
}
