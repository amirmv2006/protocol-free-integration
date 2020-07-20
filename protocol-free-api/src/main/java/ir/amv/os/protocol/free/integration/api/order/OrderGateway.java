package ir.amv.os.protocol.free.integration.api.order;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "OrderGateway")
public interface OrderGateway {

  @Gateway(requestChannel = "asyncSubmitOrder.input")
  void submitOrder(Order order);
}
