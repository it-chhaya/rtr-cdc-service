//package co.istad.cdc.consumer;
//
//import CDC.CORE_BANKING.TRANSACTION.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TransactionConsumer {
//
//    @KafkaListener(topics = "CDC.CORE_BANKING.TRANSACTION",
//        groupId = "${spring.application.name}")
//    public void consumeTransactionEvent(Value transaction) {
//        System.out.println("Received transaction: " + transaction);
//    }
//
//}
