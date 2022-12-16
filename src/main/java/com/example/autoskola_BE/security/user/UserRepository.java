package com.example.autoskola_BE.security.user;


import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    List<UserEntity> findAllByUserEntityMembers(AutoskolaOrganization autoskolaOrganization);
}
