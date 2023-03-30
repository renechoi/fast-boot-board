package fast.bootboard.repository;

import fast.bootboard.domain.UserAccount;
import fast.bootboard.domain.projection.ArticleProjection;
import fast.bootboard.domain.projection.UserAccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(excerptProjection = UserAccountProjection.class)
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}