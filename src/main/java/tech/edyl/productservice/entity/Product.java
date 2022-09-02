package tech.edyl.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class Product {

    @Id
    private @NotNull Long id;
    private @NotNull String name;
    private @NotNull String description;
    private @NotNull Integer price;
    private @NotNull String imageUrl;
    @NotNull
    private Category category;


}
