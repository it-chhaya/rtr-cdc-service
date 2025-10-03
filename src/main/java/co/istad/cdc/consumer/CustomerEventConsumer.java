package co.istad.cdc.consumer;

import co.istad.cdc.config.AvroConverterConfig;
import co.istad.cdc.model.Customer;
import co.istad.cdc.model.DebeziumEnvelope;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventConsumer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, GenericRecord> kafkaTemplate;

    @KafkaListener(topics = "CDC.CORE_BANKING.CUSTOMER",
        groupId = "${spring.application.name}")
    public void consumerCustomerEvent(ConsumerRecord<String, Object> record) {
        System.out.println("Consumer Customer Event");

        try {

            if (record.value() == null) {
                // Operation
                System.out.println("Deleted key: " + record.key());
                return;
            }

            // Read and clean data
            DebeziumEnvelope<Customer> customerEvent =
                    objectMapper.readValue(record.value().toString(), new TypeReference<>(){});

            // YOUR LOGIC..!

            // Send cleaned data into Kafka Topic
            Customer customer = customerEvent.getAfter();
            GenericRecord genericRecord = AvroConverterConfig.toGenericRecord(customer);
            kafkaTemplate.send("customerXmlCleanedEventTopic",
                    customer.getId(),
                    genericRecord);

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
