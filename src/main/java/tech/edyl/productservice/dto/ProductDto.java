package tech.edyl.productservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDto {

    private Long id;
    private String description;
    private String image;
    private Integer price;
}
