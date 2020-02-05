package springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
public interface BookRepository extends JpaRepository<Book, Long> {



}
