package com.hospital.appointment.model;

import lombok.Data;

import javax.persistence.*;

/**
* An Entity which will store doctors/providers data
* */
@Entity
@Data
@Table(name = "provider")
public class ProviderEntity {
 
    @Id
    @Column(name = "provider_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;
     
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}