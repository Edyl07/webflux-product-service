package tech.edyl.productservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import tech.edyl.productservice.entity.Category;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, Long> {

    Mono<Category> findByCategoryName(String categoryName);


}
