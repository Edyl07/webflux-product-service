package tech.edyl.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.edyl.productservice.dto.CategoryDto;
import tech.edyl.productservice.dto.ProductDto;
import tech.edyl.productservice.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("all")
    public Flux<CategoryDto> all(){
        return this.categoryService.listCategories();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<CategoryDto>> getProductById(@PathVariable Long id){
        return this.categoryService.readCategoryId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
//            (consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CategoryDto> insertProduct(@Valid @RequestBody Mono<CategoryDto> categoryDtoMono){

        return this.categoryService.insertCategory(categoryDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<CategoryDto>> updateProduct(@PathVariable Long id, @RequestBody Mono<CategoryDto> categoryDtoMono){
        return this.categoryService.updateProduct(id, categoryDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable Long id){
        return this.categoryService.deleteCategory(id);
    }
}
