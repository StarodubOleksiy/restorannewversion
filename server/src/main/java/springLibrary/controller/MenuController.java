package springLibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.MenuResponse;
import springLibrary.service.MenuService;
import springLibrary.service.OrderService;

import java.util.List;



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

    @GetMapping("menu")
    public ResponseEntity<List<MenuResponse>> menu() {
        return new ResponseEntity<>(menuService.findAllResponse(), HttpStatus.OK);
    }


    @GetMapping("menu/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
        return menuService.findByIdResponse(id)
                .map(menu -> new ResponseEntity<Object>(menu, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect book id", HttpStatus.BAD_REQUEST));
    }


}
