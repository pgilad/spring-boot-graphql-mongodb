package pac.services;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import pac.models.User;

import java.util.List;

public interface UserService {

    Page<User> findAllUsers(Integer first);

    User findOneById(ObjectId id);

    Page<User> findByIdIn(List<String> ids);
}
