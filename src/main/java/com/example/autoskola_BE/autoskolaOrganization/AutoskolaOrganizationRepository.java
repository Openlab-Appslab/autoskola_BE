package com.example.autoskola_BE.autoskolaOrganization;

import com.example.autoskola_BE.security.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoskolaOrganizationRepository extends CrudRepository<AutoskolaOrganization, Long> {



       AutoskolaOrganization findByUserEntity(UserEntity userEntity);
}
