package pl.bartoszmatras.projektto2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartoszmatras.projektto2.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
