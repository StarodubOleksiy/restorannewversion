package springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springLibrary.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   /* @Query("select d from Author d where d.fio like %?1%")
    public List<Author> findByFio(String fio);

    @Query("select d from Author d where d.fio like ?1%")
    List<Author> findByCharacter(Character letter);*/

}
