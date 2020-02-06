package springLibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import springLibrary.controller.DishController;
import springLibrary.controller.EmployeeController;
import springLibrary.controller.IngradientController;
import springLibrary.controller.OrderController;
import springLibrary.service.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class App implements CommandLineRunner {

    @Autowired
    OrderController orderController;

    @Autowired
    DishController dishController;

    @Autowired
    EmployeeController employeeController;

    @Autowired
    IngradientController ingradientController;

    @Override
    public void run(String[] args) throws IOException {
        System.out.println("method run=");
        orderController.printOrders();
        dishController.printDishes();
        employeeController.printEmployees();
        ingradientController.printIngradients();
    }

    public static void main(String[] args) throws IOException {

        SpringApplication.run(App.class, args);
        System.out.println("EXIT AND CLOSE");


    }
}
