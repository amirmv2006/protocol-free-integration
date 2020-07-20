package ir.amv.os.sample.application;

import ir.amv.os.protocol.free.integration.api.EnableProtocolFreeApi;
import ir.amv.os.protocol.free.integration.api.order.Order;
import ir.amv.os.protocol.free.integration.api.order.OrderGateway;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableProtocolFreeApi
public class SampleApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(SampleApplication.class, args);
    OrderGateway orderGateway = applicationContext.getBean(OrderGateway.class);
    orderGateway.submitOrder(Order.builder()
        .orderId(UUID.randomUUID())
        .username("amir")
        .cost(100)
        .build());
  }
}
