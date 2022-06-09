package pl.bartoszmatras.projektto2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartoszmatras.projektto2.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
