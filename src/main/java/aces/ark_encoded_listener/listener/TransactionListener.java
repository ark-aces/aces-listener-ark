package aces.ark_encoded_listener.listener;

import aces.ark_encoded_listener.subscription.SubscriptionEntity;
import aces.ark_encoded_listener.subscription.SubscriptionRepository;
import ark_java_client.ArkClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TransactionListener {

    private final Integer scanDepthTransactions;
    private final ArkClient arkClient;
    private final SubscriptionRepository subscriptionRepository;

    @Scheduled(fixedDelay = 1000)
    public void scanTransactions() {
        try {
            // todo: review this scanning so that we don't miss any transactions
            Integer limit = 500;
            for (Integer offset = 0; offset < scanDepthTransactions; offset += limit) {
                List<SubscriptionEntity> subscriptionEntities = subscriptionRepository.findAll();
                arkClient.getTransactions(offset)
                    .forEach(transaction -> {
                        // todo: bind event to subscribers
                    });
            }
        }
        catch (Exception e) {
            log.error("Transaction listener threw exception while running", e);
        }
    }

}
