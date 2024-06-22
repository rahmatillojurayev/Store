package uz.pdp.store_moc.interfaces;

import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.Roles;

@Service
public interface RoleService {

    void save(Roles role);

    Roles findByName(String name);

}
