package pac.dataFetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pac.models.User;
import pac.services.UserService;

@Component
public class AllUsersDataFetcher implements DataFetcher<Page<User>> {

    private final UserService userService;

    @Autowired
    public AllUsersDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Page<User> get(DataFetchingEnvironment environment) {
        User user = environment.getSource();
        final Integer first = environment.getArgument("first");
        final Integer skip = environment.getArgument("skip");

//        System.out.println(first);

        return user == null ?
                userService.findAllUsers(first) :
                userService.findByIdIn(user.getFriendsIds());
    }
}
