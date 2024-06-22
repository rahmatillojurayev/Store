package uz.pdp.store_moc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store_moc.entity.Roles;

import java.util.UUID;
@Repository
public interface RoleRepo extends JpaRepository<Roles, UUID> {
    Roles findByName(String name);
}
