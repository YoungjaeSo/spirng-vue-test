package org.scoula.security.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j2
class JwtProcessorTest {

    @Autowired
    JwtProcessor jwtProcessor;

    @Test
    void generateToken() {
        String username = "user0";
        String token = jwtProcessor.generateToken(username);
        log.info(token);
        assertNotNull(token);
    }

    @Test
    void getUsername() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDcyOTA1NCwiZXhwIjoxNzUwNzI5MzU0fQ.8IoZGEXhuChFlLRNNBZszD-9mGx1N7rPfcMAGpYRC6fCOBDM0DwcJmrpnFUd95fBvShzDxcPSNpLg-Jw-YeCxw";
        String username = jwtProcessor.getUsername(token);
        log.info(username);
        assertNotNull(username);
    }

    @Test
    void validateToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDcyOTA1NCwiZXhwIjoxNzUwNzI5MzU0fQ.8IoZGEXhuChFlLRNNBZszD-9mGx1N7rPfcMAGpYRC6fCOBDM0DwcJmrpnFUd95fBvShzDxcPSNpLg-Jw-YeCxw";
        boolean isValid = jwtProcessor.validateToken(token);
        log.info(isValid);
        assertTrue(isValid);
    }
}