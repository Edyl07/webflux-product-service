package tech.edyl.productservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class Category {

    @Id
    private Long id;
    private String categoryName;
    private String description;
    private Integer price;
    private String imageUrl;


    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Category(@NotNull String categoryName, @NotNull String description, @NotNull Integer price) {
        this.categoryName = categoryName;
        this.description = description;
        this.price = price;
    }

    public Category(@NotNull String categoryName, @NotNull String description, @NotNull Integer price, @NotNull String imageUrl) {
        this.categoryName = categoryName;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
