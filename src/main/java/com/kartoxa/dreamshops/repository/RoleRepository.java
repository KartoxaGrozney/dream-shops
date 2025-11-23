package com.kartoxa.dreamshops.repository;

import com.kartoxa.dreamshops.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);
}
