package com.example.autoskola_BE.security.session;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@Component
public class InMemorySessionRegistry {
    private static final HashMap<String, String> SESSIONS = new HashMap<>();

    public String registerSession(final String username) {
        if (username == null) {
            throw new RuntimeException("Username needs to be provided");
        }

        final String sessionId = generateSessionId();
        SESSIONS.put(sessionId, username);
//        System.out.println("Registered session: " + sessionId + " for user: " + username);
//        System.out.println("Current sessions: " + SESSIONS.get(sessionId));
        return sessionId;
    }

    public String getUsernameForSession(final String sessionId) {
       String sessionId2 = sessionId.substring(7);
        System.out.println("Current sessions: " + SESSIONS.get(sessionId2));
       return SESSIONS.get(sessionId2);

    }

    private String generateSessionId() {
        return new String(
                Base64.getEncoder().encode(
                        UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
                )
        );
    }
}
