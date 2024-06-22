package uz.pdp.store_moc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Roles;
import uz.pdp.store_moc.interfaces.RoleService;
import uz.pdp.store_moc.repo.RoleRepo;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;


    @Override
    public void save(Roles role) {
        roleRepo.save(role);
    }

    @Override
    public Roles findByName(String name) {
        return roleRepo.findByName(name);
    }
}
