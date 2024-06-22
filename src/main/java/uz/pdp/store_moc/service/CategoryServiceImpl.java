package uz.pdp.store_moc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Category;
import uz.pdp.store_moc.interfaces.CategoryService;
import uz.pdp.store_moc.repo.CategoryRepo;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(UUID id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepo.findByName(name);
    }

    @Override
    public List<Category> searchCategoriesByName(String search) {
        return categoryRepo.findByNameContainingIgnoreCase(search);
    }

}
