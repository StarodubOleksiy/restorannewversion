package springLibrary.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.entities.*;
import springLibrary.model.request.DishRequest;
import springLibrary.model.request.OrderRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.IngradientResponse;
import springLibrary.model.response.OrderResponse;
import springLibrary.service.EmployeeService;
import springLibrary.service.OrderService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class OrderController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OrderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    public void printOrders() {
        orderService.findAllResponse();
    }


    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> ingradients() {
        return new ResponseEntity<>(orderService.findAllResponse(), HttpStatus.OK);
    }


    @GetMapping("/orders/{id}")
    public ResponseEntity<?> ingradientsById(@PathVariable Long id) {
        return orderService.findByIdResponse(id)
                .map(order -> new ResponseEntity<Object>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect order id", HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/date")
    public List<OrderResponse> getDishessByName(@RequestParam("date") String date) {
        LOGGER.info("date = "+date);// 26.03.2020
       // LocalDate localDate = LocalDate.parse(date);
       // LOGGER.info("localDate.toString() = " +localDate.toString());
        return orderService.findAllResponse();
    }



    @PostMapping("/addorder/save")
    public ResponseEntity<?> save(@RequestBody OrderRequest orderRequest) {
        LocalDate date = LocalDate.now();
        Orders order = orderRequest.toOrder();
        //order.setId(Long.valueOf("47856"));
        order.setWaiter(new Waiter(employeeService.getOne(orderRequest.getWaiterId())));
        order.setOrderDate(date.toString());
        order.setState(OrderStatus.open);
        //LOGGER.info("employeeService.getOne(orderRequest.getWaiterId()).toString() = "+employeeService.getOne(orderRequest.getWaiterId()).toString());
        // LOGGER.info("orderRequest = " + orderRequest);
        LOGGER.info("order = " + order);
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
