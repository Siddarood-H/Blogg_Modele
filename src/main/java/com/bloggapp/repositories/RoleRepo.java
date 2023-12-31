package com.bloggapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggapp.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
