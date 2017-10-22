package aces.ark_encoded_listener;

import aces.ark_encoded_listener.common.IdentifierGenerator;
import ark_java_client.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
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
    public RestTemplate callbackRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.indentOutput(true);
        return builder;
    }

    @Bean
    public IdentifierGenerator identifierGenerator() {
        return new IdentifierGenerator();
    }

    @Bean
    public Integer scanTransactionDepth(Environment environment) {
        return environment.getProperty("scanTransactionDepth", Integer.class);
    }

}
