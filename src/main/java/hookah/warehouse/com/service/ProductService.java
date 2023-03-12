package hookah.warehouse.com.service;

import hookah.warehouse.com.dto.request.ProductRequestDto;
import hookah.warehouse.com.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  Product createProduct(ProductRequestDto requestDto);

  Product updateProduct(String productName, ProductRequestDto requestDto);

  Product getProductByName(String productName);

  Page<Product> findAllProducts(Pageable pageable);

  void deleteProductByName(String productName);
}
