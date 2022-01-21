package com.hospital.appointment.repo;

import com.hospital.appointment.model.ProviderEntity;
import com.hospital.appointment.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviderRepository extends CrudRepository<ProviderEntity, Long> {

    List<ProviderEntity> findAll();
    ProviderEntity findByUserEntity(UserEntity userEntity);

    @Query("SELECT p FROM ProviderEntity p WHERE p.userEntity.username = :username")
    ProviderEntity findByUsername(String username);
}