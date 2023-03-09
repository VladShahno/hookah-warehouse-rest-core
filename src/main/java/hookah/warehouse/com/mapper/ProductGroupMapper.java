package hookah.warehouse.com.mapper;

import hookah.warehouse.com.dto.ProductGroupRequestDto;
import hookah.warehouse.com.entity.ProductGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductGroupMapper {
  ProductGroup toProductGroup(ProductGroupRequestDto requestDto);
}
