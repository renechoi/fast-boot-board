package fast.bootboard.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import fast.bootboard.domain.ArticleComment;
import fast.bootboard.domain.QArticle;
import fast.bootboard.domain.QArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root){
        bindings.excludeUnlistedProperties(true);  // listing 하지 않을 거는 제외 시킴
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like %s{v}%
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
