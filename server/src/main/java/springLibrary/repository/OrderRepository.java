package springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springLibrary.entities.Dish;
import springLibrary.entities.Orders;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("select d from Orders d where d.orderDate like %?1%")
    public List<Orders> findByDate(String name);

}
