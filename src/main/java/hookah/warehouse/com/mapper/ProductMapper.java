package hookah.warehouse.com.mapper;

import hookah.warehouse.com.dto.request.ProductGroupRequestDto;
import hookah.warehouse.com.dto.request.ProductRequestDto;
import hookah.warehouse.com.dto.request.WarehouseRequestDto;
import hookah.warehouse.com.entity.Product;
import hookah.warehouse.com.entity.ProductGroup;
import hookah.warehouse.com.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductGroup toProductGroup(ProductGroupRequestDto requestDto);

  Warehouse toProductGroup(WarehouseRequestDto requestDto);

  @Mapping(target = "productGroup.productGroupName", source = "productGroupName")
  @Mapping(target = "warehouse.warehouseName", source = "warehouseName")
  Product toProduct(ProductRequestDto requestDto);
}
