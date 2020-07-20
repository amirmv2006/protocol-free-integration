package ir.amv.os.protocol.free.integration.api.order;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private UUID orderId;
  private String username;
  private Integer cost;
}
