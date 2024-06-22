package uz.pdp.store_moc.interfaces;

import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Category;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {

    void save(Category category);

    List<Category> findAll();

    Category findById(UUID id);

    void deleteById(UUID id);

    Category findByName(String name);

    List<Category> searchCategoriesByName(String search);

}

