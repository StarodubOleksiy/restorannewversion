package springLibrary.controller;


import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.entities.Dish;
import springLibrary.model.request.DishRequest;
import springLibrary.model.request.EmployeeRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.service.DishService;
import springLibrary.service.MenuService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class DishController {

    @Autowired
    private DishService dishService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DishController.class);


    @GetMapping("dishes")
    public ResponseEntity<List<DishResponse>> dishes() {
        return new ResponseEntity<>(dishService.findAllResponse().stream()
                .sorted().collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
        return dishService.findByIdResponse(id)
                .map(dish -> new ResponseEntity<Object>(dish, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect book id", HttpStatus.BAD_REQUEST));
    }


    @PostMapping("dish/save")
    public ResponseEntity<?> save(@RequestBody DishRequest dishRequest) {
        try
        {
        dishService.saveFromRequest(dishRequest);
        } catch (Exception exception) {
            System.out.println(exception instanceof PSQLException);
            LOGGER.info("=================Exception happened!!!!!!!!!!!!!!!!!!!!!++++++++++++++++++++");
            LOGGER.info("=================exception.getMessage!!!!!!!!!!++++++++++++;"+exception.getMessage());
            LOGGER.info("=================exception.toString()!!!!!!!!!++++++++++;"+exception.toString());
            LOGGER.info("=================exception..getLocalizedMessage()***---;"+exception.getLocalizedMessage());
            LOGGER.info("=================exception.toString()!!!!!!!!!++++++++++;"+exception.toString());
            LOGGER.info("=====exception.getClass().getCanonicalName();***---;"+exception.getClass().getCanonicalName());
            LOGGER.info("=====exception.getStackTrace()///,.,.,.;"+ Arrays.asList(exception.getStackTrace()));
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        LOGGER.info("()())()()()(Exception does not happened**************///////////,,,,,,,..........");
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/dishname")
    public List<DishResponse> getDishessByName(@RequestParam("name") String name) {
        LOGGER.info("dishName = " + name);
        return dishService.findDishesByName(name);
    }


    @GetMapping("/dishesbymenu/{id}")
    public ResponseEntity<List<DishResponse>> dishesByMenu(@PathVariable Long id) {
        return new ResponseEntity<>(dishService.findDishesByMenu(id).stream()
                .sorted().collect(Collectors.toList()), HttpStatus.OK);
    }


    @DeleteMapping("deletedish/{id}")
    ResponseEntity<?> deleteDish(@PathVariable Long id) {
        LOGGER.info(" delete dishid = " + id);
        dishService.deleteDish(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/dish/update/")
    public ResponseEntity<?> updateDish(@RequestBody DishRequest dishRequest) {
        LOGGER.info("public ResponseEntity<?> updateDish(@PathVariable Long id,@RequestBody DishRequest dishRequest)");
        LOGGER.info("dishRequest = " + dishRequest);
        dishService.updateFromRequest(dishRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
