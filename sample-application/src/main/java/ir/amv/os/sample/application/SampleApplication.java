package ir.amv.os.sample.application;

import ir.amv.os.protocol.free.integration.api.EnableProtocolFreeApi;
import ir.amv.os.protocol.free.integration.api.order.Order;
import ir.amv.os.protocol.free.integration.api.order.OrderGateway;
import java.util.UUID;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableProtocolFreeApi
public class SampleApplication {

  @Bean
  public InitializingBean afterStartup(
      OrderGateway orderGateway
  ) {
    return () -> {
      Order order = Order.builder()
          .orderId(UUID.randomUUID())
          .username("amir")
          .cost(10)
          .build();
      orderGateway.submitOrder(order);
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(SampleApplication.class, args);
  }
}
