package springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springLibrary.entities.Dish;
import springLibrary.entities.Ingradient;

import java.util.List;

public interface IngradientRepository extends JpaRepository<Ingradient, Long> {

    @Query("select d from Ingradient d where d.name like %?1%")
    public List<Ingradient> findByName(String name);


}
