package ir.amv.os.sample.application;

import ir.amv.os.protocol.free.integration.api.EnableProtocolFreeApi;
import ir.amv.os.protocol.free.integration.api.order.Order;
import ir.amv.os.protocol.free.integration.api.order.OrderGateway;
import java.security.SecureRandom;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableProtocolFreeApi
public class SampleApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication
        .run(SampleApplication.class, args);
    OrderGateway orderGateway = applicationContext.getBean(OrderGateway.class);
    SecureRandom random = new SecureRandom();
    new Timer()
        .scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
            orderGateway.submitOrder(Order.builder()
                .orderId(UUID.randomUUID())
                .username("amir")
                .cost(random.nextInt(1000))
                .build());
          }
        }, 100, 1000);
  }
}
