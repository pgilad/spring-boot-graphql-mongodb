package pac.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pac.models.Article;
import pac.repositories.ArticleRepository;
import pac.services.ArticleService;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Integer DEFAULT_FIRST = 5;
    private static final Integer DEFAULT_PAGE = 0;
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public List<Article> findAllUserArticles(List<String> ids, Integer first, Integer offset) {
        final Integer size = Optional.ofNullable(first).orElse(DEFAULT_FIRST);
        final Integer page = Optional.ofNullable(offset).orElse(DEFAULT_PAGE);

        final PageRequest pageRequest = new PageRequest(page, size);
        return articleRepository.findByIdIn(ids, pageRequest);
    }
}
