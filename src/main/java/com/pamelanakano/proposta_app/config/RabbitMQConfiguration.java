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

    @Value("${rabbitmq.propostapendente-dlq.exchange}")
    private String propostaPendenteDlqExchange;

    @Value("${rabbitmq.propostaconcluida.exchange}")
    private String propostaConcluidaExchange;

    @Value("${rabbitmq.propostaconcluida-dlq.exchange}")
    private String propostaConcluidaDlqExchange;

    @Value("${rabbitmq.propostapendente.analiseCredito}")
    private String propostaPendenteAnaliseCredito;

    @Value("${rabbitmq.propostapendente.dlq}")
    private String propostaPendenteDlq;

    @Value("${rabbitmq.propostaconcluida.dlq}")
    private String propostaConcluidaDlq;

    @Value("${rabbitmq.propostapendente.notificacao}")
    private String propostaPendenteNotificacao;

    @Value("${rabbitmq.propostaconcluida.notificacao}")
    private String propostaConcluidaNotificacao;

    @Value("${rabbitmq.propostaconcluida.proposta}")
    private String propostaConcluidaProposta;

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
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

    @Bean
    public Queue createQueuePropostaPendenteMsNotificacao() {
        return QueueBuilder.durable(propostaPendenteNotificacao).build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsNotificacao() {
        return QueueBuilder.durable(propostaConcluidaNotificacao).build();
    }

    @Bean
    public Queue createQueuePropostaPendenteDlq() {
        return QueueBuilder.durable(propostaPendenteDlq).build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaDlq() {
        return QueueBuilder.durable(propostaConcluidaDlq).build();
    }

    @Bean
    public Queue createQueuePropostaPendenteMsAnaliseCredito() {
        return QueueBuilder.durable(propostaPendenteAnaliseCredito)
                .deadLetterExchange(propostaPendenteDlqExchange)
                .build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsProposta() {
        return QueueBuilder.durable(propostaConcluidaProposta)
                .deadLetterExchange(propostaConcluidaDlqExchange)
                .maxPriority(10)
                .build();
    }

    @Bean
    public FanoutExchange createFanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(propostaPendenteExchange).build();
    }

    @Bean
    public FanoutExchange deadLetterExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(propostaPendenteDlqExchange).build();
    }

    @Bean
    public FanoutExchange createFanoutExchangePropostaConcluida() {
        return ExchangeBuilder.fanoutExchange(propostaConcluidaExchange).build();
    }

    @Bean
    public FanoutExchange deadLetterExchangePropostaConcluida() {
        return ExchangeBuilder.fanoutExchange(propostaConcluidaDlqExchange).build();
    }

    @Bean
    public Binding createBindingPropostaPendenteDlq(){
        return BindingBuilder
                .bind(createQueuePropostaPendenteDlq())
                .to(deadLetterExchangePropostaPendente());
    }

    @Bean
    public Binding createBindingPropostaConcluidaDlq(){
        return BindingBuilder
                .bind(createQueuePropostaConcluidaDlq())
                .to(deadLetterExchangePropostaConcluida());
    }

    @Bean
    public Binding createBindingPropostaPendenteAnaliseCredito(){
        return BindingBuilder
                .bind(createQueuePropostaPendenteMsAnaliseCredito())
                .to(createFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding createBindingPropostaPendenteMsNotificacao(){
        return BindingBuilder
                .bind(createQueuePropostaPendenteMsNotificacao())
                .to(createFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding createBindingPropostaConcluidaMsNotificacao(){
        return BindingBuilder
                .bind(createQueuePropostaConcluidaMsNotificacao())
                .to(createFanoutExchangePropostaConcluida());
    }

    @Bean
    public Binding createBindingPropostaConcluidaMsPropostaApp(){
        return BindingBuilder
                .bind(createQueuePropostaConcluidaMsProposta())
                .to(createFanoutExchangePropostaConcluida());
    }

}
