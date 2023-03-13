package hookah.warehouse.com.service.impl;

import static hookah.warehouse.com.common.Constants.NAME;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

import hookah.warehouse.com.dto.request.ProductRequestDto;
import hookah.warehouse.com.entity.Product;
import hookah.warehouse.com.entity.ProductGroup;
import hookah.warehouse.com.entity.Warehouse;
import hookah.warehouse.com.mapper.ProductMapper;
import hookah.warehouse.com.repository.ProductRepository;
import hookah.warehouse.com.service.ProductGroupService;
import hookah.warehouse.com.service.ProductService;
import hookah.warehouse.com.service.WarehouseService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductGroupService productGroupService;
  private final WarehouseService warehouseService;
  private final ProductMapper productMapper;

  @Override
  public Product createProduct(ProductRequestDto requestDto) {
    validateCreateRequestDto(requestDto);

    var productGroup = productMapper.toProduct(requestDto);
    productRepository.save(productGroup);

    return productGroup;
  }

  @Transactional
  @Override
  public Product updateProduct(String productName, ProductRequestDto requestDto) {
    var updatedProduct = updateProduct(getProductByName(productName), requestDto);
    productRepository.save(updatedProduct);
    return updatedProduct;
  }

  @Override
  public Product getProductByName(String productName) {
    log.debug("Getting product with {}", keyValue(NAME, productName));
    return productRepository.getReferenceById(productName);
  }

  @Override
  public Page<Product> findAllProducts(Pageable pageable) {
    log.debug("Getting all products");
    return productRepository.findAll(pageable);
  }

  @Override
  public void deleteProductByName(String productName) {
    log.debug("Deleting product {}", keyValue(NAME, productName));
    productRepository.deleteById(productName);
  }

  private void validateCreateRequestDto(ProductRequestDto requestDto) {
    if (productRepository.existsById(requestDto.getProductName())) {
      throw new EntityExistsException("Product already exists");
    }
    if (!productGroupService.existsByName(requestDto.getProductGroupName())) {
      throw new EntityNotFoundException("Product group does not exist");
    }
    if (!warehouseService.existsByName(requestDto.getWarehouseName())) {
      throw new EntityNotFoundException("Warehouse does not exist");
    }
  }

  private Product updateProduct(Product productToUpdate, ProductRequestDto requestDto) {
    validateUpdateRequest(productToUpdate, requestDto);

    productToUpdate.setProductName(requestDto.getProductName());
    productToUpdate.setWarehouse(new Warehouse(requestDto.getWarehouseName()));
    productToUpdate.setProductGroup(new ProductGroup(requestDto.getProductGroupName()));
    productToUpdate.setAmount(requestDto.getAmount());
    productToUpdate.setArticle(requestDto.getArticle());
    productToUpdate.setPurchasePrice(requestDto.getPurchasePrice());
    productToUpdate.setSalePrice(requestDto.getSalePrice());
    return productToUpdate;
  }

  private void validateUpdateRequest(Product productToUpdate, ProductRequestDto requestDto) {
    if (productRepository.existsById(requestDto.getProductName()) &&
        !requestDto.getProductName().equalsIgnoreCase(productToUpdate.getProductName())) {
      throw new EntityExistsException("Product with such name already exist");
    }
    if (!warehouseService.existsByName(requestDto.getWarehouseName())) {
      throw new EntityNotFoundException("Warehouse does not exist");
    }
    if (!productGroupService.existsByName(requestDto.getProductGroupName())) {
      throw new EntityNotFoundException("Product group does not exist");
    }
  }
}
