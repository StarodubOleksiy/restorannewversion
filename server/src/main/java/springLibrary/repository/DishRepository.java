package springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import springLibrary.entities.Dish;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query("select d from Dish d where d.name like %?1%")
    public List<Dish> findByName(String name);

}
