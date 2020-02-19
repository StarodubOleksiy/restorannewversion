package springLibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import springLibrary.controller.*;
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
    IngradientController ingradientController;

    @Autowired
    MenuController menuController;

    @Override
    public void run(String[] args) throws IOException {
        System.out.println("method run=");
        ingradientController.printIngradients();
    }

    public static void main(String[] args) throws IOException {

        SpringApplication.run(App.class, args);
        System.out.println("EXIT AND CLOSE");


    }
}
