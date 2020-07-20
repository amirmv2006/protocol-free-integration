package ir.amv.os.protocol.free.integration.app;

import ir.amv.os.protocol.free.integration.api.order.Order;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class ProtocolFreeApplication {

  @Bean
  public Consumer<Order> order() {
    return order -> log.info("Order received: {}", order);
  }

  public static void main(String[] args) {
    SpringApplication.run(ProtocolFreeApplication.class, args);
  }
}
