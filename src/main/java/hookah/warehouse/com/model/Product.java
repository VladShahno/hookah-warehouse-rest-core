package hookah.warehouse.com.model;

import lombok.Data;

@Data
public class Product {
  private Long id;
  private String productName;
  private Long amount;
  private Long purchasePrice;
  private Long salePrice;
  private String productGroup;
  private String article;
}
