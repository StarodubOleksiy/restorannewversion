package springLibrary.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springLibrary.service.OrderService;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class OrderController {

  @Autowired
    private OrderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    public void printOrders()
    {
        orderService.findAllResponse();
    }


    /*@GetMapping("/booksbygenres")
    public ResponseEntity<List<GenreResponse>> genres() {
        return new ResponseEntity<>(genreService.findAllResponse(), HttpStatus.OK);

    }


    @PostMapping("/addgenre/save")
    ResponseEntity<?> save(@RequestBody GenreRequest genreRequest) {
        Genre genre = genreRequest.toGenre();
        genreService.save(genre);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("genre/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
               return genreService.findByIdResponse(id)
                .map(genre -> new ResponseEntity<Object>(genre, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect genre id", HttpStatus.BAD_REQUEST));
    }


    @PostMapping("/genre/delete")
    public ResponseEntity<?> delete(@RequestBody GenreRequest genreRequest) {
        Genre genre = genreRequest.toGenre();
            genreService.delete(genre);
             return new ResponseEntity<>(HttpStatus.OK);

    }*/




}
