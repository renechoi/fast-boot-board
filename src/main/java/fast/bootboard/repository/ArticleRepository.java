package fast.bootboard.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import fast.bootboard.domain.Article;
import fast.bootboard.domain.QArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // 이 기능으로 검색기능 자동으로
        QuerydslBinderCustomizer<QArticle> {

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);  // listing 하지 않을 거는 제외 시킴
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
//        bindings.bind(root.title).first((StringExpression::likeIgnoreCase); // like ' '
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like %s{v}%
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
