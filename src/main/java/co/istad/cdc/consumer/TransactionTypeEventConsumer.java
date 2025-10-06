package co.istad.cdc.consumer;

import co.istad.cdc.model.DebeziumEnvelope;
import co.istad.cdc.model.TransactionType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionTypeEventConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "CDC.CORE_BANKING.TRANSACTION_TYPE", groupId = "${spring.application.name}")
    public void consumerTransactionTypeEvent(ConsumerRecord<String, Object> record) {
        try {
            DebeziumEnvelope<TransactionType> transactionTypeEvent = objectMapper.readValue(
                    record.value().toString(),
                    new TypeReference<>() {}
            );
            System.out.println("Received transaction type event: " + transactionTypeEvent.getAfter());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
