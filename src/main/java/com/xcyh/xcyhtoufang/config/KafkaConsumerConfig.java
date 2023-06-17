package com.xcyh.xcyhtoufang.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    /**
     * kafka集群地址  （地址逗号分割）
     */
    @Value("${kafka.consumer.bootstrap.servers}")
    private String servers;

    /**
     *  value.deserializer
     */
    @Value("${kafka.consumer.value.deserializer}")
    private String valueDeserializer;

    /**
     *  key.deserializer
     */
    @Value("${kafka.consumer.key.deserializer}")
    private String keyDeserializer;

    /**
     * 消费者组id
     */
    @Value("${kafka.consumer.group.id}")
    private int groupId;

    /**
     * client id
     */
    @Value("${kafka.consumer.client.id}")
    private int clientId;

    /**
     * session超时时间  ,毫秒
     */
    @Value("${kafka.consumer.session.timeout.ms}")
    private int sessionTimeout;

    /**
     * kafka偏移量是否自动提交
     */
    @Value("${kafka.consumer.enable.auto.commit}")
    private boolean enableAutoCommit;

    /**
     * kafka偏移量是否自动提交
     */
    @Value("${kafka.consumer.max.partition.fetch.bytes}")
    private int maxPartitionFetchBytes = 10 * 1024 * 1024;

    /**
     * 批量拉取消息的最大数量
     */
    @Value("${kafka.consumer.max.poll.records}")
    private int maxPollRecords;

    /**
     * 消费offset的方式
     * 可填写两个值 latest 与 earliest。分别代表，最新与最早的数据
     */
    @Value("${kafka.consumer.auto.offset.reset}")
    private String autoOffsetReset;

    /**
     * topic名称
     */
    @Value("${kafka.consumer.topic.name}")
    private String topicName;

    /**
     * 消费者的数量
     */
    @Value("${kafka.consumer.concurrency}")
    private int concurrency;

    /**
     * 拉取消息的超时时间
     */
    @Value("${kafka.consumer.poll.timeout}")
    private int pollTimeout;

    /**
     * 自动提交偏移量时间间隔， 默认1000 毫秒
     */
    @Value("${kafka.consumer.auto.commit.interval.ms}")
    private String autoCommitInterval;


    /**
     * 封装kafka消费者配置信息
     * @return map
     */
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> propsMap = new HashMap<String, Object>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, StringUtils.join(servers, ','));
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, RandomStringUtils.randomAlphabetic(groupId));
        propsMap.put(ConsumerConfig.CLIENT_ID_CONFIG, RandomStringUtils.randomAlphabetic(clientId));
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        propsMap.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, maxPartitionFetchBytes);
        propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset); // 可填写两个值 latest 与 earliest。分别代表，最新与最早的数据
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        return propsMap;
    }

    /**
     * 创建kafka消费工厂
     * @return ConsumerFactory
     */
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<String, String>(consumerConfigs());
    }


    /**
     * 实例化kafka消费监听工厂bean
     * @return MessageListenerContainer
     */
    @Bean(value = "kafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(pollTimeout);
        factory.setBatchListener(true); //@KafkaListener 批量消费  每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);//设置提交偏移量的方式
        return factory;
    }



//    /**
//     * 封装kafka消费者配置信息
//     * @return map
//     */
//    public KafkaConsumer<String, String> getConsumerConfigs() {
//        Properties properties = new Properties();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, StringUtils.join(servers, ','));
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, RandomStringUtils.randomAlphabetic(groupId));
//        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, RandomStringUtils.randomAlphabetic(clientId));
//        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
//        properties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, maxPartitionFetchBytes);
//        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset); // 可填写两个值 latest 与 earliest。分别代表，最新与最早的数据
//        return new KafkaConsumer<>(properties);
//    }

}
