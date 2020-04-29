package com.turtle.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author lijiayu
 * @date 2020/1/18
 * @description
 */
@Configuration
@Slf4j
public class RabbitMqConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public Queue dirQueue() {
        return new Queue("emailDirect");
    }

    @Bean
    DirectExchange Exchange(){
        return new DirectExchange("Exchange");
    }

    @Bean
    Binding bindingExchangeDirect(@Qualifier("dirQueue")Queue dirQueue, DirectExchange directExchange){
        return  BindingBuilder.bind(dirQueue).to(directExchange).with("emailDirect");
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory);
    }
}