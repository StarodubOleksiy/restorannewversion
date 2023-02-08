package springLibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.entities.Menu;
import springLibrary.model.request.IngradientRequest;
import springLibrary.model.request.MenuRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.MenuResponse;
import springLibrary.service.MenuService;
import springLibrary.service.OrderService;

import java.util.Collections;
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

    @GetMapping("/menu")
    public ResponseEntity<List<MenuResponse>> menu() {
        List<MenuResponse> menuList = menuService.findAllResponse();
        Collections.sort(menuList);
        return new ResponseEntity<>(menuList, HttpStatus.OK);
    }


    @GetMapping("menu/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
        return menuService.findByIdResponse(id)
                .map(menu -> new ResponseEntity<Object>(menu, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect book id", HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/addmenu/save")
    ResponseEntity<?> save(@RequestBody MenuRequest menuRequest) {
        Menu menu = menuRequest.toMenu();
        LOGGER.info("menu = "+menu);
        menuService.save(menu);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("deletemenu/{id}")
    ResponseEntity<?> deleteMenu(@PathVariable Long id) {
        LOGGER.info(" delete menuid = "+id);
        menuService.deleteMenu(id);
        //menuService.deleteMenu(id);
        return new  ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/menu/update/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable Long id,@RequestBody MenuRequest menuRequest) {
        LOGGER.info("public ResponseEntity<?> updateMenu(@PathVariable Long id,@RequestBody MenuRequest menuRequest)");
        LOGGER.info("menuRequest = " + menuRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
