package com.arkaces.aces_listener_ark;

import ark_java_client.*;
import com.arkaces.aces_server.aces_listener.config.AcesListenerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Import({AcesListenerConfig.class})
@Slf4j
public class ApplicationConfig {

    @Bean
    public ArkClient arkClient(Environment environment) {
        ArkNetworkFactory arkNetworkFactory = new ArkNetworkFactory();
        String arkNetworkConfigPath = environment.getProperty("arkNetworkConfigPath");
        ArkNetwork arkNetwork = arkNetworkFactory.createFromYml(arkNetworkConfigPath);

        HttpArkClientFactory httpArkClientFactory = new HttpArkClientFactory();

        log.info("Bootstrapping ARK client with network peers");
        return httpArkClientFactory.create(arkNetwork);
    }

    @Bean
    public Integer scanTransactionDepth(Environment environment) {
        return environment.getProperty("scanTransactionDepth", Integer.class);
    }

}
