package springLibrary.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springLibrary.model.response.CookedDishResponse;
import springLibrary.model.response.DishResponse;
import springLibrary.service.CookedDishService;
import springLibrary.service.DishService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CookedDishController {

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

}
