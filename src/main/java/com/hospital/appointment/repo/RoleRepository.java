package com.hospital.appointment.repo;

import com.hospital.appointment.model.ProviderEntity;
import com.hospital.appointment.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findByName(String roleName);
}