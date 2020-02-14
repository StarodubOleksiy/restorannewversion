package springLibrary.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.entities.Dish;
import springLibrary.model.request.DishRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.service.DishService;
import springLibrary.service.MenuService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private MenuService menuService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DishController.class);

    /*public void printDishes()
    {
        dishService.findAllResponse();
    }*/

    @GetMapping("dishes")
    public ResponseEntity<List<DishResponse>> dishes() {
        return new ResponseEntity<>(dishService.findAllResponse(), HttpStatus.OK);
    }


    @GetMapping("dishes/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
        return dishService.findByIdResponse(id)
                .map(dish -> new ResponseEntity<Object>(dish, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect book id", HttpStatus.BAD_REQUEST));
    }

    /*
    @GetMapping("book/findbyname")
    public List<Book> getBookByName() {
        return bookService.findByName("Melovoy chelovek");
    }


    @GetMapping("book/findbycharacter")
    public List<BookResponse> getBookByCharacter(@RequestParam("character") String character) {
        return bookService.findByCharacterResponse(character);
    }


    @PostMapping("book/findbycriteria")
    public ResponseEntity<List<BookResponse>> findBooks(@RequestBody CriteriaRequest criteriaRequest) {
        LOGGER.info("criteriaRequest.toString() = "+criteriaRequest.toString());
        if (criteriaRequest.getType() == null)
            return new ResponseEntity<>(bookService.findByTitleResponse(criteriaRequest.getSearchWord()), HttpStatus.OK);
        else
            return new ResponseEntity<>(bookService.findByPlacingResponse(criteriaRequest.getType(),criteriaRequest.getSearchWord()), HttpStatus.OK);
    }


    @GetMapping("getbygenre/{id}")
    public List<BookResponse> findByGenreBooks(@PathVariable Long id) {
        return bookService.findByGenreResponse(id);

    }


    @GetMapping("getbypublisher/{id}")
    public List<BookResponse> getBooksByPublisher(@PathVariable Long id) {
        return bookService.findByPublisherResponse(id);
    }


    @GetMapping("getbyauthor/{id}")
    public List<BookResponse> getBooksByAuthor(@PathVariable Long id) {
        return bookService.findByAuthorResponse(id);
    }

*/
    @PostMapping("dish/save")
   public ResponseEntity<?> save(@RequestBody DishRequest dishRequest) {
           Dish dish = dishRequest.toDish();
           dish.setMenu(menuService.findById(Long.valueOf(dishRequest.getMenuId())).orElse(null));
           LOGGER.info("dish = "+dish);
           //dish.setMenu(menuService.findById(Long.valueOf(1)).orElse(null));
       // book.setGenre(genreService.findById(bookRequest.getGenreId()).orElse(null));
       // book.setPublisher(publisherService.findById(bookRequest.getPublisherId()).orElse(null));
        dishService.saveFromRequest(dish, dishRequest);
       /* if(bookRequest.getId() != null) {
            if (bookRequest.getAuthorsId().length > 0)
                for (int i = 0; i < bookRequest.getAuthorsId().length; ++i)
                    bookService.insertRelationshipBetweenBookAndAuthor((long) bookRequest.getAuthorsId()[i], bookRequest.getId());
        }*/
       return new ResponseEntity<>(HttpStatus.OK);
   }
/*

    @PostMapping("/book/delete")
    public ResponseEntity<?> delete(@RequestBody DishRequest dishRequest) {
        bookService.deleteBook(bookRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/


}
