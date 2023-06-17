//package com.xcyh.xcyhtoufang.utils;
//
//import com.xcyh.xcyhtoufang.config.KafkaConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.TopicPartition;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Component
//public class KafkaConsumerUtil {
//
//    @Autowired
//    private KafkaConsumerConfig kafkaConsumerConfig;
//
//    private KafkaConsumer<String, String> consumer;
//
//
//    public KafkaConsumer<String, String> getConsumer(){
//        if(consumer == null){
//           // consumer = kafkaConsumerConfig.getConsumerConfigs();
//        }
//        return consumer;
//    }
//
//
//    /**
//     * 通过指定 topic 的方式来订阅数据，订阅进度会有 kafka 来维护
//     *
//     * @param topicNames 需要消费的 topic 名称的集合
//     */
//    public void assignByTopic(Collection<String> topicNames) {
//        this.getConsumer().subscribe(topicNames);
//    }
//
//    /**
//     * 通过精确地指定 topic partition 来订阅指定位置的数据
//     *
//     * @param topicPartitions 可以通过 {@link org.apache.kafka.common.TopicPartition#TopicPartition(String, int)} 来构造一个
//     *                        topic partition
//     */
//    public void assignByTopicPosition(Collection<TopicPartition> topicPartitions) {
//        this.getConsumer().assign(topicPartitions);
//    }
//
//    /**
//     * 指定某一个 topic partition 的订阅位置
//     *
//     * @param topicPartition 要指定的 topic partition
//     * @param offset         要进行访问的 offset
//     */
//    public void resetConsumerPosition(TopicPartition topicPartition, int offset) {
//        this.getConsumer().seek(topicPartition, offset);
//    }
//
//    /**
//     * 获取指定超时时间获取数据
//     *
//     * @param timeoutMillis 超时时间
//     */
//    public List<String> pollRecords(int timeoutMillis) {
//        ConsumerRecords<String, String> consumerRecords = this.getConsumer().poll(timeoutMillis);
//        ArrayList<String> records = new ArrayList<>();
//        for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
//            records.add(consumerRecord.value());
//        }
//        return records;
//    }
//
//
//}
