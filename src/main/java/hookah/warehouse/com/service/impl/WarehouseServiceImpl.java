package hookah.warehouse.com.service.impl;

import static hookah.warehouse.com.common.Constants.NAME;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

import hookah.warehouse.com.dto.request.WarehouseRequestDto;
import hookah.warehouse.com.entity.Warehouse;
import hookah.warehouse.com.mapper.ProductMapper;
import hookah.warehouse.com.repository.WarehouseRepository;
import hookah.warehouse.com.service.WarehouseService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

  private final WarehouseRepository warehouseRepository;
  private final ProductMapper productMapper;

  @Override
  public Warehouse createWarehouse(WarehouseRequestDto requestDto) {
    if (warehouseRepository.existsById(requestDto.warehouseName())) {
      throw new EntityExistsException("Warehouse already exists");
    }
    var warehouse = productMapper.toProductGroup(requestDto);
    warehouseRepository.save(warehouse);
    return warehouse;
  }

  @Override
  public Warehouse getWarehouseByName(String warehouseName) {
    log.debug("Getting warehouse with {}", keyValue(NAME, warehouseName));
    return warehouseRepository.getReferenceById(warehouseName);
  }

  @Override
  public Page<Warehouse> findAllWarehouses(Pageable pageable) {
    log.debug("Getting all warehouses");
    return warehouseRepository.findAll(pageable);
  }

  @Override
  public void deleteWarehouseByName(String warehouseName) {
    log.debug("Deleting warehouse with {}", keyValue(NAME, warehouseName));
    warehouseRepository.deleteById(warehouseName);
  }

  @Override
  public boolean existsByName(String warehouseName) {
    return warehouseRepository.existsById(warehouseName);
  }
}
