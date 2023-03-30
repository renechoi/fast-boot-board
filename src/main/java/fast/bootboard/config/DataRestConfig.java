package fast.bootboard.config;

import fast.bootboard.domain.Article;
import fast.bootboard.domain.ArticleComment;
import fast.bootboard.domain.Hashtag;
import fast.bootboard.domain.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class DataRestConfig {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig((config, cors) ->
                config
                        .exposeIdsFor(UserAccount.class)
                        .exposeIdsFor(Article.class)
                        .exposeIdsFor(ArticleComment.class)
                        .exposeIdsFor(Hashtag.class)
        );
    }

}