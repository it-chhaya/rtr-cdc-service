package co.istad.cdc.consumer;

import co.istad.cdc.model.Customer;
import co.istad.cdc.model.DebeziumEnvelope;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "CDC.CORE_BANKING.CUSTOMER",
        groupId = "${spring.application.name}")
    public void consumerCustomerEvent(ConsumerRecord<String, Object> record) {
        System.out.println("Consumer Customer Event");

        try {

            DebeziumEnvelope<Customer> customerEvent =
                    objectMapper.readValue(record.value().toString(), new TypeReference<>(){});

            System.out.println("After: " + customerEvent.getAfter());
            System.out.println("Before: " + customerEvent.getBefore());

            customerEvent.getAfter().getAddresses().getAddress().forEach(address -> {
                System.out.println("Address: " + address);
            });

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
