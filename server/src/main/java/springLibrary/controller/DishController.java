package springLibrary.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springLibrary.service.DishService;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class DishController {

    @Autowired
    private DishService dishService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DishController.class);

    public void printDishes()
    {
        dishService.findAllResponse();
    }

    /*@GetMapping("books")
    public ResponseEntity<List<BookResponse>> books() {
        return new ResponseEntity<>(bookService.findAllResponse(), HttpStatus.OK);
    }


    @GetMapping("books/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
        return bookService.findByIdResponse(id)
                .map(book -> new ResponseEntity<Object>(book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect book id", HttpStatus.BAD_REQUEST));
    }

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


    @PostMapping("books/save")
    public ResponseEntity<?> save(@RequestBody BookRequest bookRequest) {
        Book book = bookRequest.toBook();
        book.setGenre(genreService.findById(bookRequest.getGenreId()).orElse(null));
        book.setPublisher(publisherService.findById(bookRequest.getPublisherId()).orElse(null));
        bookService.saveFromRequest(book, bookRequest);
        if(bookRequest.getId() != null) {
            if (bookRequest.getAuthorsId().length > 0)
                for (int i = 0; i < bookRequest.getAuthorsId().length; ++i)
                    bookService.insertRelationshipBetweenBookAndAuthor((long) bookRequest.getAuthorsId()[i], bookRequest.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/book/delete")
    public ResponseEntity<?> delete(@RequestBody BookRequest bookRequest) {
        bookService.deleteBook(bookRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/


}
