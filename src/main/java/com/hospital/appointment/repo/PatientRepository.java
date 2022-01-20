package com.hospital.appointment.repo;

import com.hospital.appointment.model.PatientEntity;
import com.hospital.appointment.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<PatientEntity, Long> {
 
//    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
//    public UserEntity getUserByUsername(@Param("username") String username);

    PatientEntity findByUserEntity(UserEntity userEntity);

    @Query("SELECT p FROM PatientEntity p WHERE p.userEntity.username = :username")
    PatientEntity findByUsername(String username);
}