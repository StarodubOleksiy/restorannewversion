package springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springLibrary.entities.Menu;
import springLibrary.entities.Orders;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
