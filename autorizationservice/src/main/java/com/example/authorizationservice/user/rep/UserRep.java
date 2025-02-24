package com.example.authorizationservice.user.rep;
import com.example.authorizationservice.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRep extends JpaRepository<User, Long> {

    User findByUsername(String username);

    //User findByLogin(String login);

    boolean existsByUsername(String username);

    //boolean existsByEmail(String email);
    //Optional<User> findByUsername(String username);


}
