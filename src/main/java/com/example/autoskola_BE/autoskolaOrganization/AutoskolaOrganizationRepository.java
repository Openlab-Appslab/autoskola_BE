package com.example.autoskola_BE.autoskolaOrganization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoskolaOrganizationRepository extends CrudRepository<AutoskolaOrganization, Long> {

}
