package com.pamelanakano.proposta_app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.propostapendente.exchange}")
    private String propostaPendenteExchange;

    @Value("${rabbitmq.propostapendente.analiseCredito}")
    private String propostaPendenteAnaliseCredito;

    @Value("${rabbitmq.propostapendente.notificacao}")
    private String propostaPendenteNotificacao;

    @Value("${rabbitmq.propostaconcluida.notificacao}")
    private String propostaConcluidaNotificacao;

    @Value("${rabbitmq.propostaconcluida.proposta}")
    private String propostaConcluidaProposta;

    @Bean
    public Queue createQueuePropostaPendenteMsAnaliseCredito() {
        return QueueBuilder.durable(propostaPendenteAnaliseCredito).build();
    }
    @Bean
    public Queue createQueuePropostaPendenteMsNotificacao() {
        return QueueBuilder.durable(propostaPendenteNotificacao).build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsNotificacao() {
        return QueueBuilder.durable(propostaConcluidaNotificacao).build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsProposta() {
        return QueueBuilder.durable(propostaConcluidaProposta).build();
    }


    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange createFanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(propostaPendenteExchange).build();
    }

    @Bean
    public Binding createBindingPropostaPendenteAnaliseCredito(){
        return BindingBuilder.bind(createQueuePropostaPendenteMsAnaliseCredito()).to(createFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding createBindingPropostaPendenteMsNotificacao(){
        return BindingBuilder.bind(createQueuePropostaPendenteMsNotificacao()).to(createFanoutExchangePropostaPendente());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
