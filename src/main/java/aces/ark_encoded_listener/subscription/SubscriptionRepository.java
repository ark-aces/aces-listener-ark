package aces.ark_encoded_listener.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

}
