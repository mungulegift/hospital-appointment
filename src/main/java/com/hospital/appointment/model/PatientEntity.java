package com.hospital.appointment.model;

import lombok.Data;

import javax.persistence.*;


/**
* An Entity which will be used to store patient's data
* */
@Entity
@Data
@Table(name = "patient")
public class PatientEntity {
 
    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
     
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}