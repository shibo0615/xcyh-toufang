package com.xcyh.xcyhtoufang.utils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * kafka监听器启动
 * 自动监听是否有消息需要消费
 * @author junjie
 */
@Component
public class KafkaConsumerListener {

    /**
     * 监听 kafka 的topic
     * @param records 消息列表
     * @param ack 消息消费后提交
     */
    //@KafkaListener(topics = "${kafka.consumer.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void userConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        try {
            for (ConsumerRecord<String, String> record : records) {

                String kafkaJson = record.value();
                System.out.println("kafka_message:" + kafkaJson);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ack.acknowledge();  //手动提交偏移量
        }
    }



}



