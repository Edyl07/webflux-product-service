package tech.edyl.productservice.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class CategoryDto {

    @Id
    private Long id;
    private String description;
    private Integer price;
    private String image;
}
