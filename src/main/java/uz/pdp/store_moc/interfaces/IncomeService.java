package uz.pdp.store_moc.interfaces;

import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Income;
import uz.pdp.store_moc.entity.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface IncomeService {

    void save(Income income);

    List<Income> findAll();

    Income findById(UUID id);

    void deleteById(UUID id);

    List<Income> findAllByProductId(UUID id);


    List<Income> findAllByProductIdAndDateBetween(UUID id, LocalDateTime startDate, LocalDateTime endDate);

    Income findByProductId(UUID productId);
}
