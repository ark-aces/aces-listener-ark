package aces.ark_encoded_listener.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Health getHealth() {
        Health health = new Health();
        health.setStatus("up");
        return health;
    }

}
