package co.istad.cdc.client;

import co.istad.cdc.model.Customer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface CustomerClient {

    @PostExchange("/customers/sync")
    void syncCustomer(@RequestBody Customer customer);

}
