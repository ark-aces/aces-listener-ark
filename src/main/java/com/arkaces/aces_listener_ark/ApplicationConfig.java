package com.arkaces.aces_listener_ark;

import ark_java_client.*;
import com.arkaces.aces_server.aces_listener.config.AcesListenerConfig;
import com.arkaces.aces_server.ark_basic_authorization.ArkBasicAuthorizationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Import({AcesListenerConfig.class, ArkBasicAuthorizationConfig.class})
public class ApplicationConfig {

    @Bean
    public ArkClient arkClient(Environment environment) {
        ArkNetworkFactory arkNetworkFactory = new ArkNetworkFactory();
        String arkNetworkName = environment.getProperty("arkNetwork.name");
        ArkNetwork arkNetwork = arkNetworkFactory.createFromYml("ark-network-config/" + arkNetworkName + ".yml");

        HttpArkClientFactory httpArkClientFactory = new HttpArkClientFactory();
        return httpArkClientFactory.create(arkNetwork);
    }

    @Bean
    public Integer scanTransactionDepth(Environment environment) {
        return environment.getProperty("scanTransactionDepth", Integer.class);
    }

}
