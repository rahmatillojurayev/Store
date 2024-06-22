package uz.pdp.store_moc.interfaces;

import org.springframework.stereotype.Service;
import uz.pdp.store_moc.entity.User;

import java.util.List;

@Service
public interface UserService {

    void save(User user);


    User findByUsername(String username);

    List<User> findAll();
}
