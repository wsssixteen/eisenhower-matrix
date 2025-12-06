package com.ach.eisenhower.repositories;

import com.ach.eisenhower.entities.EisenhowerUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EisenhowerUserRepository extends JpaRepository<EisenhowerUserEntity, UUID> {
    Optional<EisenhowerUserEntity> findByEmail(String username);

    List<EisenhowerUserEntity> findByLastLoginDateBefore(Date date);

    default EisenhowerUserEntity createUser(String email, String password, List<String> roles) {
        var entity = new EisenhowerUserEntity();
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setRoles(roles);
        entity.registrationDate = new Date();
        entity.lastLoginDate = new Date();
        save(entity);
        return entity;
    }
}