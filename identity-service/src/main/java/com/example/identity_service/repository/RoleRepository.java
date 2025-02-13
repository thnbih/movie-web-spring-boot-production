package com.example.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.identity_service.entiy.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
