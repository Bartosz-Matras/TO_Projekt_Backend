package pl.bartoszmatras.projektto2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartoszmatras.projektto2.entity.Operator;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    public Optional<Operator> findByLogin(String login);
}
