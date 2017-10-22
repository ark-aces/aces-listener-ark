package aces.ark_encoded_listener.subscription;

import lombok.Data;

@Data
public class CreateSubscriptionRequest {
    private String callbackUrl;
    private Integer minConfirmations;
}
