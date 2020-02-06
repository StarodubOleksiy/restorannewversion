package springLibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springLibrary.service.IngradientService;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class IngradientController {

   private static final Logger LOGGER = LoggerFactory.getLogger(IngradientController.class);

    @Autowired
    private IngradientService ingradientService;

    public void printIngradients()
    {
        ingradientService.findAllResponse();
    }


       /* @GetMapping("/booksbypublisher")
    public ResponseEntity<List<PublisherResponse>> publishers() {
        return new ResponseEntity<>(publisherService.findAllResponse(), HttpStatus.OK);

    }


    @GetMapping("publisher/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
          return publisherService.findByIdResponse(id)
                .map(publisher -> new ResponseEntity<Object>(publisher, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect publisher id", HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/addpublisher/save")
    ResponseEntity<?> save(@RequestBody PublisherRequest publisherRequest) {
        Publisher publisher = publisherRequest.toPublisher();
        publisherService.save(publisher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/publisher/delete")
    public ResponseEntity<?> delete(@RequestBody PublisherRequest publisherRequest) {
        Publisher publisher = publisherRequest.toPublisher();
        publisherService.delete(publisher);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @PostMapping("/publisher/findbycriteria")
    public ResponseEntity<List<PublisherResponse>> findPublishers(@RequestBody PublisherSearchCreateria publisherSearchCreateria) {
       LOGGER.info("publisherSearchCreateria.toString() = "+publisherSearchCreateria.toString());
        if (publisherSearchCreateria.isFindByCity() == false)
            return new ResponseEntity<>(publisherService.findByNameResponse(publisherSearchCreateria.getSearchWord()), HttpStatus.OK);
        else
            return new ResponseEntity<>(publisherService.findByCityResponse(publisherSearchCreateria.getSearchWord()), HttpStatus.OK);
    }


    @GetMapping("publisher/findbycharacter")
    public List<PublisherResponse> getPublisherByCharacter(@RequestParam("character") String character) {
        return publisherService.findByCharacterResponse(character);
    }
*/


}
