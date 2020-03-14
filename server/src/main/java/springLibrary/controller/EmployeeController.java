package springLibrary.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springLibrary.entities.Employee;
import springLibrary.model.request.EmployeeRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.EmployeeResponse;
import springLibrary.service.EmployeeService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    public void printEmployees() {
        employeeService.findAllResponse();
    }

    @GetMapping("employees")
    public ResponseEntity<List<EmployeeResponse>> employees() {
        return new ResponseEntity<>(employeeService.findAllResponse(), HttpStatus.OK);
    }

    @GetMapping("waiters")
    public ResponseEntity<List<EmployeeResponse>> waiters() {
        return new ResponseEntity<>(employeeService.getAllWaiters(), HttpStatus.OK);
    }

    @GetMapping("cookers")
    public ResponseEntity<List<EmployeeResponse>> cookers() {
        return new ResponseEntity<>(employeeService.getAllCookers(), HttpStatus.OK);
    }


    @GetMapping("employees/{id}")
    public ResponseEntity<?> configure(@PathVariable Long id) {
        return employeeService.findByIdResponse(id)
                .map(employee -> new ResponseEntity<Object>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Object>("Incorrect book id", HttpStatus.BAD_REQUEST));
    }

    @PostMapping("employees/save")
    public ResponseEntity<?> save(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeRequest.toEmployee();
        LOGGER.info("employeeRequest = " + employeeRequest);
        //   LOGGER.info("employee = "+employee);
        employeeService.saveFromRequest(employee, employeeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
