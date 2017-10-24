package aces.ark_encoded_listener.listener;

import aces.ark_encoded_listener.subscription.SubscriptionEntity;
import aces.ark_encoded_listener.subscription.SubscriptionRepository;
import ark_java_client.ArkClient;
import ark_java_client.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EventListener {

    private final Integer scanDepthTransactions;
    private final ArkClient arkClient;
    private final SubscriptionRepository subscriptionRepository;
    private final EventDeliveryService eventDeliveryService;

    @Scheduled(fixedDelay = 2000)
    public void scanTransactions() {
        try {
            List<SubscriptionEntity> subscriptionEntities = subscriptionRepository.findAll();

            // todo: review this scanning so that we don't miss any transactions
            Integer limit = 500;
            for (Integer offset = 0; offset < scanDepthTransactions; offset += limit) {
                List<Transaction> transactions = arkClient.getTransactions(offset);
                subscriptionEntities.parallelStream().forEach(subscriptionEntity -> {
                    transactions.forEach(transaction -> eventDeliveryService.deliverEvent(subscriptionEntity, transaction));
                });

            }
        }
        catch (Exception e) {
            log.error("Transaction listener threw exception while running", e);
        }
    }

}
