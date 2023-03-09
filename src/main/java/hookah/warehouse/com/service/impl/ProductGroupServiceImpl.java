package hookah.warehouse.com.service.impl;

import static hookah.warehouse.com.common.Constants.NAME;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

import hookah.warehouse.com.dto.ProductGroupRequestDto;
import hookah.warehouse.com.entity.ProductGroup;
import hookah.warehouse.com.mapper.ProductGroupMapper;
import hookah.warehouse.com.repository.ProductGroupRepository;
import hookah.warehouse.com.service.ProductGroupService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProductGroupServiceImpl implements ProductGroupService {

  private final ProductGroupRepository productGroupRepository;
  private final ProductGroupMapper productGroupMapper;

  @Override
  public ProductGroup createProductGroup(ProductGroupRequestDto requestDto) {
    if (productGroupRepository.existsById(requestDto.getProductGroupName())) {
      throw new EntityExistsException("Product group already exists");
    }
    var productGroup = productGroupMapper.toProductGroup(requestDto);
    productGroupRepository.save(productGroup);
    return productGroup;
  }

  @Override
  public ProductGroup getProductGroupByName(String productGroupName) {
    log.debug("Getting product group with {}", keyValue(NAME, productGroupName));
    return productGroupRepository.getReferenceById(productGroupName);
  }

  @Override
  public Page<ProductGroup> findAllProductGroups(Pageable pageable) {
    log.debug("Getting All product groups");
    return productGroupRepository.findAll(pageable);
  }

  @Override
  public void deleteProductGroupByName(String productGroupName) {
    log.debug("Deleting product group with {}", keyValue(NAME, productGroupName));
    productGroupRepository.deleteById(productGroupName);
  }
}
