package springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springLibrary.entities.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {


}
