package hookah.warehouse.com.controller;

import hookah.warehouse.com.dto.request.ProductGroupRequestDto;
import hookah.warehouse.com.entity.ProductGroup;
import hookah.warehouse.com.service.ProductGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@Tag(name = "Product group Controller", description = "Provides general operation with Product group")
@RequestMapping(path = "/v1/product-group", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductGroupController {
  private ProductGroupService productGroupService;

  @PostMapping("/create-product-group")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Endpoint allows to create product group")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successfully created product group")
  })
  public ProductGroup createProductGroup(@RequestBody @Valid ProductGroupRequestDto requestDto) {
    return productGroupService.createProductGroup(requestDto);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Endpoint allows to get all product groups")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Product groups received successfully",
          content = {@Content(schema = @Schema(implementation = ProductGroup.class))}),
  })
  public Page<ProductGroup> findAllProductGroups(@PageableDefault(sort = "productGroupName", size = 25) Pageable pageable) {
    return productGroupService.findAllProductGroups(pageable);
  }
}
