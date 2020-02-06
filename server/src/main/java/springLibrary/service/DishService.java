package springLibrary.service;

import springLibrary.entities.Dish;
import springLibrary.model.request.BookRequest;
import springLibrary.model.response.BookResponse;
import springLibrary.repository.DishRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Администратор on 23.02.18.
 */

public interface DishService extends Service<Dish, Long, DishRepository> {


   public List<BookResponse> findAllResponse();

   public Optional<BookResponse> findByIdResponse(Long id);


}
