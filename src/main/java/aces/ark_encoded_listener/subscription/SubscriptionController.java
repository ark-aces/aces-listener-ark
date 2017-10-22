package aces.ark_encoded_listener.subscription;

import aces.ark_encoded_listener.common.IdentifierGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RestController
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubscriptionController {

    private final IdentifierGenerator identifierGenerator;
    private final SubscriptionRepository subscriptionRepository;

    @PostMapping("/subscriptions")
    public Subscription postSubscription(CreateSubscriptionRequest createSubscriptionRequest) {
        String identifier = identifierGenerator.generate();

        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setIdentifier(identifier);
        subscriptionEntity.setCallbackUrl(createSubscriptionRequest.getCallbackUrl());
        subscriptionEntity.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC));
        subscriptionEntity.setStatus(SubscriptionStatus.active);
        subscriptionRepository.save(subscriptionEntity);

        Subscription subscription = new Subscription();
        subscription.setIdentifier(subscriptionEntity.getIdentifier());
        subscription.setCallbackUrl(subscriptionEntity.getCallbackUrl());
        subscription.setMinConfirmations(subscriptionEntity.getMinConfirmations());
        subscription.setCreatedAt(subscriptionEntity.toString());

        return subscription;
    }

}
