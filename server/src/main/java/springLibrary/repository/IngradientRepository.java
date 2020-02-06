package springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springLibrary.entities.Ingradient;

import java.util.List;

public interface IngradientRepository extends JpaRepository<Ingradient, Long> {


}
