package hookah.warehouse.com.service;

import hookah.warehouse.com.dto.ProductGroupRequestDto;
import hookah.warehouse.com.entity.ProductGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductGroupService {
  ProductGroup createProductGroup(ProductGroupRequestDto requestDto);

  ProductGroup getProductGroupByName(String id);

  Page<ProductGroup> findAllProductGroups(Pageable pageable);

  void deleteProductGroupByName(String productGroupName);
}
