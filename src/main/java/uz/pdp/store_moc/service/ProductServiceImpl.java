package uz.pdp.store_moc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Product;
import uz.pdp.store_moc.interfaces.ProductService;
import uz.pdp.store_moc.repo.ProductRepo;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(UUID id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(UUID id) {
       productRepo.deleteById(id);
    }

    @Override
    public Product findByName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public List<Product> findAllByCategoryId(UUID categoryId) {
        return productRepo.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Product> searchAllByName(String name, UUID categoryId) {
        return productRepo.findByNameContainingIgnoreCaseAndCategoryId(name, categoryId);
    }

    @Override
    public List<Product> searchProducts(String searchProduct, UUID categoryId) {
        return productRepo.findByNameContainingIgnoreCaseAndCategoryId(searchProduct, categoryId);
    }
}
