package ir.amv.os.protocol.free.integration.api;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;

@Configuration
@IntegrationComponentScan
@Import(RabbitAutoConfiguration.class)
public class ProtocolFreeApiConfig {

  @Bean
  AsyncRabbitTemplate template(RabbitTemplate template) {
    return new AsyncRabbitTemplate(template);
  }

  @Bean
  public MessageChannel submitOrderChannel() {
    return new DirectChannel();
  }

  @Bean
  public IntegrationFlow submitOrder(AmqpTemplate amqpTemplate) {
    return f -> f.handle(
        Amqp.outboundGateway(amqpTemplate)
            .exchangeName("order")
    );
  }

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  public static void main(String[] args) {
    SpringApplication.run(ProtocolFreeApiConfig.class);
  }
}
