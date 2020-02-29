package springLibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.entities.Ingradient;
import springLibrary.entities.Menu;
import springLibrary.model.request.DishIngradientRequest;
import springLibrary.model.request.IngradientRequest;
import springLibrary.model.request.MenuRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.EmployeeResponse;
import springLibrary.model.response.InfoResponse;
import springLibrary.model.response.IngradientResponse;
import springLibrary.service.DishService;
import springLibrary.service.IngradientService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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
        return new ResponseEntity<>(ingradientService.findAllResponse(), HttpStatus.OK);
    }

    @GetMapping("newingradients/{id}")
    public ResponseEntity<List<IngradientResponse>> newIngradients(@PathVariable Long id) {
        return new ResponseEntity<>(ingradientService.findNewIngradientsResponse(id), HttpStatus.OK);
    }


    @GetMapping("ingradients/{id}")
    public ResponseEntity<?> ingradientsById(@PathVariable Long id) {
        return ingradientService.findByIdResponse(id)
                .map(ingradient -> new ResponseEntity<Object>(ingradient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect ingradient id", HttpStatus.BAD_REQUEST));
    }

    @GetMapping("dishingradientresponse")
    public ResponseEntity<?> dishIngradientResponse(@RequestParam("dish_id") String dishId,@RequestParam("ingradient_id") String ingradientId) {
        LOGGER.info(" dish_id = "+dishId+ " ingradientId = "+ingradientId);
        return ingradientService.getCurrentIngradientInDish(Long.valueOf(dishId),Long.valueOf(ingradientId))
                .map(ingradient -> new ResponseEntity<Object>(ingradient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect ingradient id", HttpStatus.BAD_REQUEST));

    }



    @GetMapping("dishingradients/{id}")
    public ResponseEntity<?> ingradientsByDishId(@PathVariable Long id) {
        return new ResponseEntity<>(ingradientService.findIngradientsByDishIdResponse(id), HttpStatus.OK);
    }


    @PostMapping("addingradient/save")
    ResponseEntity<?> save(@RequestBody IngradientRequest ingradientRequest) {
        Ingradient ingradient = ingradientRequest.toIngradient();
        LOGGER.info("ingradient = " + ingradient);
        ingradientService.save(ingradient);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("adddishingradient")
    ResponseEntity<?> addIngradientToDish(@RequestBody DishIngradientRequest dishIngradientRequest) {
        LOGGER.info("dishIngradientRequest = " + dishIngradientRequest.toString());
        ingradientService.addIngradientToDish(dishIngradientRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("changenumerosity")
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
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
