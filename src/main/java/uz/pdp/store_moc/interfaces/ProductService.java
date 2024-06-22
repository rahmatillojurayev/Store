package uz.pdp.store_moc.interfaces;

import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Product;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {

    void save(Product product);

    List<Product> findAll();

    Product findById(UUID id);

    void deleteById(UUID id);

    Product findByName(String name);

    List<Product> findAllByCategoryId(UUID categoryId);

    List<Product> searchAllByName(String search, UUID categoryId);

    List<Product> searchProducts(String searchProduct, UUID categoryId);
}
