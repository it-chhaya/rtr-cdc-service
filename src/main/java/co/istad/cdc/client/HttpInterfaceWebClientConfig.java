package co.istad.cdc.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HttpInterfaceWebClientConfig {

    private final HttpInterfaceWebClient httpInterfaceWebClient;

    @Value("${service.customer}")
    private String customerService;

    @Bean
    public CustomerClient customerClient() {
        return httpInterfaceWebClient
                .createClient(
                        "http://" + customerService,
                        CustomerClient.class
                );
    }

}
