package tech.edyl.productservice.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import tech.edyl.productservice.entity.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Long> {

    //> min & < max
    //  Flux<Product> findByPriceBetween(int min, int max);
    Flux<Product> findByPriceBetween(Range<Integer> range);
}
