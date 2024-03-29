package springLibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.entities.Ingradient;
import springLibrary.model.request.DishIngradientRequest;
import springLibrary.model.request.DishRequest;
import springLibrary.model.request.IngradientRequest;
import springLibrary.model.response.IngradientResponse;
import springLibrary.service.DishService;
import springLibrary.service.IngradientService;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class IngradientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngradientController.class);

    @Autowired
    private DishService dishService;

    @Autowired
    private IngradientService ingradientService;

    public void printIngradients() {
        ingradientService.findAllResponse();
    }

    @GetMapping("ingradients")
    public ResponseEntity<List<IngradientResponse>> ingradients() {
        return new ResponseEntity<>(ingradientService.findAllResponse()
                .stream()
                .sorted().collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("newingradients/{id}")
    public ResponseEntity<List<IngradientResponse>> newIngradients(@PathVariable Long id) {
        return new ResponseEntity<>(ingradientService.findNewIngradientsResponse(id)
                .stream().sorted().collect(Collectors.toList()), HttpStatus.OK);
    }


    @GetMapping("ingradients/{id}")
    public ResponseEntity<?> ingradientsById(@PathVariable Long id) {
        return ingradientService.findByIdResponse(id)
                .map(ingradient -> new ResponseEntity<Object>(ingradient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect ingradient id", HttpStatus.BAD_REQUEST));
    }

    @GetMapping("dishingradientresponse")
    public ResponseEntity<?> dishIngradientResponse(@RequestParam("dish_id") String dishId, @RequestParam("ingradient_id") String ingradientId) {
        LOGGER.info(" dish_id = " + dishId + " ingradientId = " + ingradientId);
        return ingradientService.getCurrentIngradientInDish(Long.valueOf(dishId), Long.valueOf(ingradientId))
                .map(ingradient -> new ResponseEntity<Object>(ingradient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect ingradient id", HttpStatus.BAD_REQUEST));

    }


    @GetMapping("dishingradients/{id}")
    public ResponseEntity<?> ingradientsByDishId(@PathVariable Long id) {
        return new ResponseEntity<>(ingradientService.findIngradientsByDishIdResponse(id)
                .stream().sorted().collect(Collectors.toList())
                , HttpStatus.OK);
    }


    @PostMapping("addingradient/save")
    ResponseEntity<?> save(@RequestBody IngradientRequest ingradientRequest) {
        LOGGER.info("ingradientRequest = " + ingradientRequest);
        ingradientService.saveFromRequest(ingradientRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("adddishingradient")
    ResponseEntity<?> addIngradientToDish(@RequestBody DishIngradientRequest dishIngradientRequest) {
        LOGGER.info("dishIngradientRequest = " + dishIngradientRequest.toString());
        ingradientService.addIngradientToDish(dishIngradientRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("changenumerosity")
    ResponseEntity<?> changeNumerosityOfIngradientsInDish(@RequestBody DishIngradientRequest dishIngradientRequest) {
        LOGGER.info("changeNumerosityOfIngradientsInDish method");
        LOGGER.info("dishIngradientRequest = " + dishIngradientRequest.toString());
        ingradientService.changeNumerosityOfIngradientsInDish(dishIngradientRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("deleteingradientfromdish")
    ResponseEntity<?> deleteFromIngradientFromDish(@RequestBody DishIngradientRequest dishIngradientRequest) {
        LOGGER.info("deleteFromIngradientFromDish method");
        LOGGER.info("dishIngradientRequest = " + dishIngradientRequest.toString());
        ingradientService.deleteIngradientFromCurrentDish(dishIngradientRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}/deleteallingradientsfromcurrentdish")
    ResponseEntity<?> deleteAllIngradientsFromCurrentDish(@PathVariable Long id) {
        LOGGER.info("dishid = " + id);
        ingradientService.deleteAllIngradientsFromCurrentDish(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ingredientname")
    public List<IngradientResponse> getIngradientsByName(@RequestParam("name") String name) {
        LOGGER.info("ingradientName = " + name);
        return ingradientService.findIngradientsByName(name);
    }


    @DeleteMapping("deleteingradient/{id}")
    ResponseEntity<?> deleteIngradient(@PathVariable Long id) {
        LOGGER.info(" delete ingradientid = " + id);
        ingradientService.delete(ingradientService.getOne(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/ingradient/update/")
    public ResponseEntity<?> updateIngradient(@RequestBody IngradientRequest ingradientRequest) {
        LOGGER.info("public ResponseEntity<?> updateIngradient(@PathVariable Long id,@RequestBody IngradientRequest ingradientRequest)");
        LOGGER.info("ingradientRequest = " + ingradientRequest.toString());
        ingradientService.updateFromRequest(ingradientRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
