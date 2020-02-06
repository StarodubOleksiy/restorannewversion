package springLibrary.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springLibrary.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class EmployeeController {

  @Autowired
    private EmployeeService employeeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    public void printEmployees()
    {
        employeeService.findAllResponse();
    }


/*
    @GetMapping("/authors")
    public ResponseEntity<List<AuthorResponse>> authors() {
        return new ResponseEntity<>(authorService.findAllResponse(), HttpStatus.OK);

    }


    @PostMapping("/addauthor/save")
    ResponseEntity<?> save(@RequestBody AuthorRequest authorRequest) {
        authorService.saveFromRequest(authorRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("author/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        return authorService.findByIdResponse(id)
                .map(author -> new ResponseEntity<Object>(author, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect author id", HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/author/delete")
    public ResponseEntity<?> delete(@RequestBody AuthorRequest authorRequest) {
        Author author = authorRequest.toAuthor();
        authorService.delete(author);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/author/findbyname")
    public List<AuthorResponse> getAuthorsByName(@RequestParam("name") String name) {
        return authorService.findByAuthorsByName(name);
    }

    @GetMapping("author/findbycharacter")
    public List<AuthorResponse> getAuthorByCharacter(@RequestParam("character") String character) {
        return authorService.findByCharacterResponse(character);
    }

    @GetMapping("getbybook/{id}")
    public List<AuthorResponse> getAuthorsByBook(@PathVariable Long id) {
        return authorService.findByBookResponse(id);
    }

*/

}
