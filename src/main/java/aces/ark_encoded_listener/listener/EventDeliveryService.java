package aces.ark_encoded_listener.listener;

import aces.ark_encoded_listener.event.Event;
import aces.ark_encoded_listener.subscription.SubscriptionEntity;
import ark_java_client.Transaction;
import ark_java_client.lib.NiceObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EventDeliveryService {

    private final RestTemplate callbackRestTemplate;
    private final NiceObjectMapper dtoObjectMapper;

    public void deliverEvent(SubscriptionEntity subscriptionEntity, Transaction transaction) {
        String callbackUrl = subscriptionEntity.getCallbackUrl();

        Event event = new Event();
        event.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC).toString());
        event.setData(dtoObjectMapper.writeValueAsString(transaction));

        ResponseEntity<String> response = callbackRestTemplate.postForEntity(callbackUrl, event, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            // todo: mark the subscription event as delivered so we don't resend
        } else {
            // todo: the callback URL did not accept the POST successfully, we should retry here or
            // queue up the event for later retries.
            log.info("Failed callback response: " + response.toString());
            log.warn("Post to callback URL for subscription ID '{}' failed");
        }
    }
}
