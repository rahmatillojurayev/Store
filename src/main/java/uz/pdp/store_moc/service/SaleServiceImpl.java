package uz.pdp.store_moc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Sale;
import uz.pdp.store_moc.interfaces.SaleService;
import uz.pdp.store_moc.repo.SaleRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepo saleRepo;
    @Override
    public List<Sale> findAll() {
        return saleRepo.findAll();
    }

    @Override
    public List<Sale> findAllByProductId(UUID id) {
        return saleRepo.findAllByProductId(id);
    }

    @Override
    public List<Sale> findAllByProductIdAndDateBetween(UUID id, LocalDateTime startDate, LocalDateTime endDate) {
        return saleRepo.findAllByProductIdAndDateBetween(id, startDate, endDate);
    }

    @Override
    public void save(Sale sale) {
       saleRepo.save(sale);
    }

    @Override
    public void deleteById(UUID id) {
        saleRepo.deleteById(id);
    }

}
