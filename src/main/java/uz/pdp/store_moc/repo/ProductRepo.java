package uz.pdp.store_moc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store_moc.entity.Product;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
    Product findByName(String name);

    List<Product> findAllByCategoryId(UUID categoryId);


    List<Product> findByNameContainingIgnoreCaseAndCategoryId(String name, UUID categoryId);
}
