package co.istad.cdc.consumer;

import co.istad.cdc.model.DebeziumEnvelope;
import co.istad.cdc.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionEventConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "CDC.CORE_BANKING.TRANSACTION", groupId = "${spring.application.name}")
    public void consumeTransactionEvent(ConsumerRecord<String, Object> record) {
        System.out.println(record.key() + ":" + record.value());
        try {
            DebeziumEnvelope<Transaction> transactionRecord =
                    objectMapper.readValue(record.value().toString(), new TypeReference<>() {
                    });
            System.out.println("TransactionRecord: " + transactionRecord.getAfter());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

}
