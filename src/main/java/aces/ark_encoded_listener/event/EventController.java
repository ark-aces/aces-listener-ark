package aces.ark_encoded_listener.event;

import aces.ark_encoded_listener.common.Page;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @GetMapping("/subscriptions/{id}/events")
    public Page<EventEntity> getEvents(@PathVariable String subscriptionIdentifier) {
        throw new NotImplementedException();
    }

}
