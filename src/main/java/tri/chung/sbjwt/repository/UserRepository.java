package tri.chung.sbjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tri.chung.sbjwt.entity.User;
;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
