package springLibrary.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.entities.Cooked_Dish;
import springLibrary.entities.Dish;
import springLibrary.model.request.CookedDishRequest;
import springLibrary.model.request.DishRequest;
import springLibrary.model.response.CookedDishResponse;
import springLibrary.model.response.DishResponse;
import springLibrary.service.CookedDishService;
import springLibrary.service.DishService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CookedDishController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookedDishController.class);

    @Autowired
    private CookedDishService cookedDishService;

    @GetMapping("cookeddishes")
    public ResponseEntity<List<CookedDishResponse>> dishes() {
        return new ResponseEntity<>(cookedDishService.findAllResponse(), HttpStatus.OK);
    }


    @GetMapping("/cookeddishes/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
        return cookedDishService.findByIdResponse(id)
                .map(cookedDish -> new ResponseEntity<Object>(cookedDish, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<Object>("Incorrect book id", HttpStatus.BAD_REQUEST));
    }



    @GetMapping("/cookeddishesbyorderid/{id}")
    public ResponseEntity<List<CookedDishResponse>>  cookedDishesByOrderId(@PathVariable Long id) {
        return new ResponseEntity<>(cookedDishService.findByOrderIdResponse(id), HttpStatus.OK);
    }


    @PostMapping("cookeddish/save")
    public ResponseEntity<?> save(@RequestBody CookedDishRequest cookedDishRequest) {
        //Dish dish = dishRequest.toDish();
       // dish.setMenu(menuService.findById(Long.valueOf(dishRequest.getMenuId())).orElse(null));
        LOGGER.info("Method public ResponseEntity<?> save(@RequestBody Cooked_Dish cookedDishRequest) ");
        LOGGER.info("cookedDishRequest = "+cookedDishRequest);
      //  dishService.saveFromRequest(dish, dishRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
