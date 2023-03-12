package hookah.warehouse.com.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRequestDto {
  private String productName;
  private int amount;
  private BigDecimal purchasePrice;
  private BigDecimal salePrice;
  private String productGroupName;
  private String warehouseName;
  private String article;
}
