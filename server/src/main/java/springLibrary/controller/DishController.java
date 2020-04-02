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


    @GetMapping("dishes")
    public ResponseEntity<List<DishResponse>> dishes() {
        return new ResponseEntity<>(dishService.findAllResponse(), HttpStatus.OK);
    }


    @GetMapping("/dishes/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
        return dishService.findByIdResponse(id)
                .map(dish -> new ResponseEntity<Object>(dish, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect book id", HttpStatus.BAD_REQUEST));
    }


    @PostMapping("dish/save")
   public ResponseEntity<?> save(@RequestBody DishRequest dishRequest) {
           Dish dish = dishRequest.toDish();
           dish.setMenu(menuService.findById(Long.valueOf(dishRequest.getMenuId())).orElse(null));
           LOGGER.info("dish = "+dish);
        dishService.saveFromRequest(dish, dishRequest);
       return new ResponseEntity<>(HttpStatus.OK);
   }


    @GetMapping("/dishname")
    public List<DishResponse> getDishessByName(@RequestParam("name") String name) {
        LOGGER.info("dishName = "+name);
        return dishService.findDishesByName(name);
    }


    @GetMapping("/dishesbymenu/{id}")
    public ResponseEntity<List<DishResponse>> dishesByMenu(@PathVariable Long id)  {
        return new ResponseEntity<>(dishService.findDishesByMenu(id), HttpStatus.OK);
    }


    @DeleteMapping("deletedish/{id}")
    ResponseEntity<?> deleteDish(@PathVariable Long id) {
        LOGGER.info(" delete dishid = "+id);
        dishService.deleteDish(id);
        return new  ResponseEntity<>(HttpStatus.OK);
    }




}
