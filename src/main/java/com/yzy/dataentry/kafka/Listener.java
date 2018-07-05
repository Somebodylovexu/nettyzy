package com.yzy.dataentry.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
public class Listener {

/*    @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("kafka key: " + record.key());
        log.info("kafka value: " + record.value().toString());
    }*/
}
