package uz.pdp.store_moc.interfaces;

import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Sale;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface SaleService {

    List<Sale> findAll();

    List<Sale> findAllByProductId(UUID id);

    List<Sale> findAllByProductIdAndDateBetween(UUID id, LocalDateTime startDate, LocalDateTime endDate);

    void save(Sale sale);

    void deleteById(UUID id);
}
