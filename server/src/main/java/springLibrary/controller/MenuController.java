package springLibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import springLibrary.service.MenuService;
import springLibrary.service.OrderService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    public void printOrders()
    {
        menuService.findAllResponse();
    }

}
