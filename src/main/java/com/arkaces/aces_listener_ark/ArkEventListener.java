package com.arkaces.aces_listener_ark;

import ark_java_client.ArkClient;
import ark_java_client.Transaction;
import com.arkaces.aces_server.aces_listener.event_delivery.EventDeliveryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ArkEventListener {

    private final Integer scanTransactionDepth;
    private final ArkClient arkClient;
    private final EventDeliveryService eventDeliveryService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedDelayString = "${scanIntervalSec:2}000")
    public void scanTransactions() {
        log.info("Scanning for transactions");
        try {
            // todo: review this scanning so that we don't miss any transactions since limit/offset doesn't
            // give stable pagination when elements are being actively added
            Integer limit = 50;
            for (Integer offset = 0; offset < scanTransactionDepth; offset += limit) {
                List<Transaction> transactions = arkClient.getTransactions(limit, offset);
                for (Transaction transaction : transactions) {
                    String transactionId = transaction.getId();
                    String recipientAddress = transaction.getRecipientId();
                    JsonNode data = objectMapper.convertValue(transaction, JsonNode.class);
                    Integer confirmations = transaction.getConfirmations();
                    log.info("Found transaction " + transactionId + ", confirmations = " + confirmations);
                    eventDeliveryService.saveSubscriptionEvents(transactionId, recipientAddress, confirmations, data);
                }
            }
        }
        catch (Exception e) {
            log.error("Transaction listener threw exception while running", e);
        }
    }

}
