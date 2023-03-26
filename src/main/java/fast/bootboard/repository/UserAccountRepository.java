package fast.bootboard.repository;

import fast.bootboard.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}