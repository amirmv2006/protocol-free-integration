package ir.amv.os.protocol.free.integration.api;

import ir.amv.os.protocol.free.integration.api.order.Order;
import ir.amv.os.protocol.free.integration.api.order.OrderGateway;
import java.util.UUID;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

@Configuration
@IntegrationComponentScan
@EnableIntegration
@Import(RabbitAutoConfiguration.class)
public class ProtocolFreeApiConfig {

  @Bean
  AsyncRabbitTemplate template(RabbitTemplate template) {
    AsyncRabbitTemplate asyncRabbitTemplate = new AsyncRabbitTemplate(template);
    return asyncRabbitTemplate;
  }

  @Bean
  public IntegrationFlow asyncSubmitOrder(AsyncRabbitTemplate asyncRabbitTemplate) {
    return f -> f
        .handle(Amqp.asyncOutboundGateway(asyncRabbitTemplate)
            .exchangeName("order"));
  }


  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(ProtocolFreeApiConfig.class);
    OrderGateway orderGateway = applicationContext.getBean(OrderGateway.class);
    orderGateway.submitOrder(Order.builder()
        .orderId(UUID.randomUUID())
        .username("amir")
        .cost(100)
        .build());
  }
}
