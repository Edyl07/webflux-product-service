package tech.edyl.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import tech.edyl.productservice.dto.ProductDto;
import tech.edyl.productservice.repository.ProductRepository;
import tech.edyl.productservice.util.EntityDtoUtil;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Flux<ProductDto> getAll(){
       return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Flux<ProductDto> getProductByPriceRange(int min, int max){
        return this.repository.findByPriceBetween(Range.closed(min, max))
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> getProductById(Long id){
        return this.repository.findById(id)
                .map(EntityDtoUtil::toDto);
    }


    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono ){


        return productDtoMono
                .map(EntityDtoUtil::toEntity)
                .publishOn(Schedulers.boundedElastic())
//                .doOnNext(product -> {
//                    if (product.getImage() == null) {
//                        product.setImage("");
//                    }
//
//                    product.setImage(Objects.requireNonNull(images.block()).filename());
//                })
                .flatMap(this.repository::insert)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> updateProduct(Long id, Mono<ProductDto> productDtoMono){
        return this.repository.findById(id)
                .flatMap(p -> productDtoMono.map(EntityDtoUtil::toEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteProduct(Long id){
        return this.repository.deleteById(id);
    }

}
