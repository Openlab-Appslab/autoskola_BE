package com.example.autoskola_BE.security.emailVerification;

import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String > {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}

