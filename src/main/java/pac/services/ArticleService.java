package pac.services;

import pac.models.Article;

import java.util.List;

public interface ArticleService {

    List<Article> findAllUserArticles(List<String> userId, Integer first, Integer offset);
}
