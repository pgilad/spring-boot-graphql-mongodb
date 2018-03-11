package pac.services.implementation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pac.models.User;
import pac.repositories.UserRepository;
import pac.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findAllUsers(Integer first) {
        Integer count = Optional.ofNullable(first).orElse(5);
        final PageRequest pageable = new PageRequest(0, count);
        return userRepository.findAll(pageable);
    }

    @Override
    public User findOneById(ObjectId id) {
        return userRepository.findOne(id);
    }

    @Override
    public Page<User> findByIdIn(List<String> ids) {
        final PageRequest pageRequest = new PageRequest(0, 5);
        return userRepository.findByIdIn(ids, pageRequest);
    }
}
