package hookah.warehouse.com.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public record ProductGroupRequestDto(String productGroupName) {
}
