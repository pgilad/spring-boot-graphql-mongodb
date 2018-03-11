package pac.dataFetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pac.models.Article;
import pac.models.User;
import pac.services.ArticleService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticlesDataFetcher implements DataFetcher<List<Article>> {

    private final ArticleService articleService;

    @Autowired
    ArticlesDataFetcher(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public List<Article> get(DataFetchingEnvironment env) {
        User user = env.getSource();
        List<String> articleIds = new ArrayList<>();
        if (user != null) {
            articleIds = user.getArticlesIds();
        }
        return articleService.findAllUserArticles(articleIds);
    }
}
