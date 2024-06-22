package uz.pdp.store_moc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store_moc.entity.Income;
import uz.pdp.store_moc.entity.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface IncomeRepo extends JpaRepository<Income, UUID> {
    List<Income> findAllByProductId(UUID id);


    List<Income> findByProductIdAndDateBetween(UUID productId, LocalDateTime startDate, LocalDateTime endDate);


    Income findByProductId(UUID productId);
}
