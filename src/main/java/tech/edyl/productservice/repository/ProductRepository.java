package tech.edyl.productservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import tech.edyl.productservice.entity.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
