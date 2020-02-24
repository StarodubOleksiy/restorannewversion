package springLibrary.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import springLibrary.service.CookedDishService;
import springLibrary.service.DishService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CookedDishController {

    @Autowired
    private CookedDishService cookedDishService;

}
