package tech.edyl.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import tech.edyl.productservice.dto.CategoryDto;
import tech.edyl.productservice.entity.Category;
import tech.edyl.productservice.repository.CategoryRepository;
import tech.edyl.productservice.util.EntityDtoUtil;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Flux<CategoryDto> listCategories() {
        return this.categoryRepository
                .findAll()
                .map(EntityDtoUtil::toDtoCategory);
    }

    public Mono<CategoryDto> insertCategory(Mono<CategoryDto> categoryDtoMono ){


        return categoryDtoMono
                .map(EntityDtoUtil::toEntityCategory)
                .publishOn(Schedulers.boundedElastic())
//                .doOnNext(product -> {
//                    if (product.getImage() == null) {
//                        product.setImage("");
//                    }
//
//                    product.setImage(Objects.requireNonNull(images.block()).filename());
//                })
                .flatMap(this.categoryRepository::insert)
                .map(EntityDtoUtil::toDtoCategory);
    }

    public Mono<CategoryDto> readCategory(String categoryName) {
        return this.categoryRepository
                .findByCategoryName(categoryName)
                .map(EntityDtoUtil::toDtoCategory);
    }

    public Mono<CategoryDto> readCategoryId(Long categoryId) {
        return this.categoryRepository
                .findById(categoryId)
                .map(EntityDtoUtil::toDtoCategory);
    }

    public Mono<CategoryDto> updateProduct(Long id, Mono<CategoryDto> categoryDtoMono){
        return this.categoryRepository.findById(id)
                .flatMap(p -> categoryDtoMono.map(EntityDtoUtil::toEntityCategory)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(this.categoryRepository::insert)
                .map(EntityDtoUtil::toDtoCategory);
    }


    public Mono<Void> deleteCategory(Long id){
        return this.categoryRepository.deleteById(id);
    }
}
