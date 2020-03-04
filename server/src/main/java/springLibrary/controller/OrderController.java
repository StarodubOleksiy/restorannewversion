package springLibrary.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.model.response.IngradientResponse;
import springLibrary.model.response.OrderResponse;
import springLibrary.service.OrderService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class OrderController {

  @Autowired
    private OrderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    public void printOrders()
    {
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





}
