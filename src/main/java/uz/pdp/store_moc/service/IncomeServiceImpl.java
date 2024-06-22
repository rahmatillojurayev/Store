package uz.pdp.store_moc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Income;
import uz.pdp.store_moc.entity.Product;
import uz.pdp.store_moc.interfaces.IncomeService;
import uz.pdp.store_moc.repo.IncomeRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepo incomeRepo;

    @Override
    public void save(Income income) {
        incomeRepo.save(income);
    }

    @Override
    public List<Income> findAll() {
        return incomeRepo.findAll();
    }

    @Override
    public Income findById(UUID id) {
        return incomeRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(UUID id) {
        incomeRepo.deleteById(id);
    }

    @Override
    public List<Income> findAllByProductId(UUID id) {
        return incomeRepo.findAllByProductId(id);
    }

    @Override
    public List<Income> findAllByProductIdAndDateBetween(UUID id, LocalDateTime startDate, LocalDateTime endDate) {
        return incomeRepo.findByProductIdAndDateBetween(id, startDate, endDate);
    }

    @Override
    public Income findByProductId(UUID productId) {
        return incomeRepo.findByProductId(productId);
    }

}
