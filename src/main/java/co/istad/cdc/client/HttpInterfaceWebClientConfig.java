package co.istad.cdc.client;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HttpInterfaceWebClientConfig {

    private final HttpInterfaceWebClient httpInterfaceWebClient;

    @Bean
    public CustomerClient customerClient() {
        return httpInterfaceWebClient
                .createClient(
                        "http://localhost:16800/api/v1",
                        CustomerClient.class
                );
    }

}
