package pl.bartoszmatras.projektto2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartoszmatras.projektto2.entity.QuantityType;

public interface QuantityTypeRepository extends JpaRepository<QuantityType, Long> {
}
