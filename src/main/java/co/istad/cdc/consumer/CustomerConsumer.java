package co.istad.cdc.consumer;

import CDC.CORE_BANKING.CUSTOMER.Envelope;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerConsumer {

    @KafkaListener(topics = "CDC.CORE_BANKING.CUSTOMER",
            groupId = "${spring.application.name}")
    public void consumerCustomerEvent(Envelope envelope) {
        System.out.println("Consumer Customer:");
        System.out.println(envelope.getOp());
        System.out.println(envelope.getBefore());
        System.out.println(envelope.getAfter());
    }

}
