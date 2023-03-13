package hookah.warehouse.com.service;

import hookah.warehouse.com.dto.request.WarehouseRequestDto;
import hookah.warehouse.com.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {
  Warehouse createWarehouse(WarehouseRequestDto requestDto);

  Warehouse getWarehouseByName(String warehouseName);

  Page<Warehouse> findAllWarehouses(Pageable pageable);

  void deleteWarehouseByName(String warehouseName);

  boolean existsByName(String warehouseName);
}
