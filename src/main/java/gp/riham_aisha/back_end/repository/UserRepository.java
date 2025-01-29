package gp.riham_aisha.back_end.repository;

import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findByRoles(Role role);
}