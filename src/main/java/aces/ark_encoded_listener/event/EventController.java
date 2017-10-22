package aces.ark_encoded_listener.event;

import aces.ark_encoded_listener.common.Page;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class EventController {

    @GetMapping("/subscriptions/{id}/events")
    public Page<Event> getEvents(@PathVariable String subscriptionIdentifier) {
        throw new NotImplementedException();
    }

}
