package tech.edyl.productservice.util;

import org.springframework.beans.BeanUtils;
import tech.edyl.productservice.dto.CategoryDto;
import tech.edyl.productservice.dto.ProductDto;
import tech.edyl.productservice.entity.Category;
import tech.edyl.productservice.entity.Product;

public class EntityDtoUtil {

    public static ProductDto toDto(Product product){
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public static Product toEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    public static CategoryDto toDtoCategory(Category category){
        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(category, dto);
        return dto;
    }

    public static Category toEntityCategory(CategoryDto categoryDto){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        return category;
    }
}
