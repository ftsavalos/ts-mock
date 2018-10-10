package gr.eurobank.transactions.esbmock.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${activemq.queue}")
    private String queuedDestination;

    @Value("${activemq.topic}")
    private String topicDestination;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(this.queuedDestination);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(this.topicDestination);
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(this.activeMQConnectionFactory());
    }

}
