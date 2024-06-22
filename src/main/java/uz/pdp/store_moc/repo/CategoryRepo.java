package uz.pdp.store_moc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.store_moc.entity.Category;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepo extends JpaRepository<Category, UUID> {

    Category findByName(String name);

    List<Category> findByNameContainingIgnoreCase(String name);

}
