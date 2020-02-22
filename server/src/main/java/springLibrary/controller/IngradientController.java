package springLibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.entities.Ingradient;
import springLibrary.entities.Menu;
import springLibrary.model.request.IngradientRequest;
import springLibrary.model.request.MenuRequest;
import springLibrary.model.response.EmployeeResponse;
import springLibrary.model.response.IngradientResponse;
import springLibrary.service.IngradientService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class IngradientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngradientController.class);

    @Autowired
    private IngradientService ingradientService;

    public void printIngradients() {
        ingradientService.findAllResponse();
    }

    @GetMapping("ingradients")
    public ResponseEntity<List<IngradientResponse>> ingradients() {
        return new ResponseEntity<>(ingradientService.findAllResponse(), HttpStatus.OK);
    }


    @GetMapping("ingradients/{id}")
    public ResponseEntity<?> ingradientsById(@PathVariable Long id) {
        return ingradientService.findByIdResponse(id)
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


}
