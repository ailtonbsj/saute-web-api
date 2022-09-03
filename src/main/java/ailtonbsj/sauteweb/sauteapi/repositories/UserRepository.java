package ailtonbsj.sauteweb.sauteapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ailtonbsj.sauteweb.sauteapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    // SELECT u from User u JOIN FETCH u.roles where u.username = :username
    @Query("SELECT u from User u JOIN FETCH u.roles where u.username = :username")
    User findByUsernameFetchRoles(@Param("username") String username);
}
