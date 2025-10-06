package co.istad.cdc.consumer;

import co.istad.cdc.model.CustomerSegment;
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
public class CustomerSegmentConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "CDC.CORE_BANKING.CUSTOMER_SEGMENT", groupId = "${spring.application.name}")
    public void consumerCustomerSegmentEvent(ConsumerRecord<String, Object> record) {
        try {
            DebeziumEnvelope<CustomerSegment> customerSegmentEvent = objectMapper.readValue(record.value().toString(),
                    new TypeReference<>(){});
            System.out.println("Received customer segment event: " + customerSegmentEvent.getAfter());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
