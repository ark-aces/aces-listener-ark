package aces.ark_encoded_listener.common;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class IdentifierGenerator {

    private final Integer length = 20;
    private final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final SecureRandom secureRandom = new SecureRandom();

    public String generate() {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }
        return sb.toString();
    }

}
