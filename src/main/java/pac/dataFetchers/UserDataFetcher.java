package pac.dataFetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pac.models.User;
import pac.services.UserService;

import java.util.Map;

@Component
public class UserDataFetcher implements DataFetcher<User> {

    private final UserService userService;

    @Autowired
    UserDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        final ObjectId id = new ObjectId(String.valueOf(args.get("id")));
        return userService.findOneById(id);
    }
}
