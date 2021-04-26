package com.mokhtar.redditclone.repository;

import com.mokhtar.redditclone.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
