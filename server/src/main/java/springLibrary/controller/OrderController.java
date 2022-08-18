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
import springLibrary.service.CookedDishService;
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

    @Autowired
    private CookedDishService cookedDishService;

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
        LOGGER.info("=====================in function ingradientsById(@PathVariable Long id================)============");
        return orderService.findByIdResponse(id)
                .map(order -> new ResponseEntity<Object>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect order id", HttpStatus.BAD_REQUEST));
    }

    // ResponseEntity<List<OrderResponse>> ingradients()
    @GetMapping("/date")
    public List<OrderResponse> getOrdersByDate(@RequestParam("date") String date) {
        LOGGER.info("date = "+date);// 26.03.2020
        String replaceDate = date.replace(".","/");//Because this method does not split string with replacement .
        LOGGER.info("replaceDate = "+replaceDate);
        String[] dateArray = replaceDate.split("/");
        LOGGER.info("dateArray.length = "+dateArray.length);
        for(int i = 0; i < dateArray.length; ++i )
            LOGGER.info("dateArray["+i+"]"+dateArray[i]);
        StringBuilder finalDate = new StringBuilder(dateArray[2]+"-"+dateArray[1]+"-"+dateArray[0]);
        //new ResponseEntity<>(orderService.findAllResponse(), HttpStatus.OK);
        return orderService.findOrdersByDate(finalDate.toString());
    }


    @PutMapping("/setclose")
    public ResponseEntity<?> setOrderClose(@RequestBody OrderRequest orderRequest) {
        Orders order = orderService.getOne(orderRequest.getId());
        LOGGER.info("order = " + order);
        order.setStateClose();
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping("/addorder/save")
    public ResponseEntity<?> save(@RequestBody OrderRequest orderRequest) {
        LocalDate date = LocalDate.now();
        Orders order = orderRequest.toOrder();
        order.setWaiter(new Waiter(employeeService.getOne(orderRequest.getWaiterId())));
        order.setOrderDate(date.toString());
        order.setState(OrderStatus.open);
        LOGGER.info("order = " + order);
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/deleteorder/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        LOGGER.info(" delete order by it's id ========***///---+++++= " + id);
        orderService.findById(id).get().getDishes().forEach(
                dish->cookedDishService.deleteCookedDish(dish.getId())
        );
      //  orderService.delete(orderService.findById(id).get());
     //  orderService.deleteById(id);
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
