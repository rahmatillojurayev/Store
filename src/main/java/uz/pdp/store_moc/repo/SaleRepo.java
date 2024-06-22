package uz.pdp.store_moc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store_moc.entity.Sale;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface SaleRepo extends JpaRepository<Sale, UUID> {
    List<Sale> findAllByProductIdAndDateBetween(UUID id, LocalDateTime startDate, LocalDateTime endDate);

    List<Sale> findAllByProductId(UUID id);
}
