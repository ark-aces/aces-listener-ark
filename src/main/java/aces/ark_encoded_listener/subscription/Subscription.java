package aces.ark_encoded_listener.subscription;

import lombok.Data;

@Data
public class Subscription {
    private String identifier;
    private String callbackUrl;
    private Integer minConfirmations;
    private String createdAt;
}
