package springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import springLibrary.entities.Cooked_Dish;
import springLibrary.entities.Dish;

@CrossOrigin(origins = "http://localhost:4200") //extends JpaRepository<Dish, Long>
public interface CookedDishRepository extends JpaRepository<Cooked_Dish, Long> {


}
