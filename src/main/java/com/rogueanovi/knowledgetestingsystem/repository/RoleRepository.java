package com.rogueanovi.knowledgetestingsystem.repository;

import com.rogueanovi.knowledgetestingsystem.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
