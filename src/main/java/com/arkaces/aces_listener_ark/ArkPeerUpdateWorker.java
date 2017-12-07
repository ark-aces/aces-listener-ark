package com.arkaces.aces_listener_ark;

import ark_java_client.ArkClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ArkPeerUpdateWorker {

    private final ArkClient arkClient;

    @Scheduled(initialDelay = 3600000, fixedDelay = 3600000)
    public void updatePeers() {
        log.info("Updating Ark client peers " + ZonedDateTime.now(ZoneOffset.UTC));
        arkClient.updatePeers();
    }

}
