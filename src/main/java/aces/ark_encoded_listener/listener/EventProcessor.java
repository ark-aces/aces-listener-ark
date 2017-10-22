package aces.ark_encoded_listener.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EventProcessor {

    private final RestTemplate callbackRestTemplate;

    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void processEvents() {
        // todo: iterate over un-confirmed events and ensure they are delivered to subscribers
//        try {
//            callbackRestTemplate.postForEntity(message.getCallbackUrl(), transactionMatch, Void.class);
//        } catch (RestClientResponseException e) {
//            log.error("Failed to post to callback url: " + message.getCallbackUrl(), e);
//            throw e;
//        }
    }

}
