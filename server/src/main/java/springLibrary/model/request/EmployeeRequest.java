package springLibrary.model.request;

import lombok.Data;
import springLibrary.entities.*;

import java.util.Base64;
import java.util.List;

@Data
public class EmployeeRequest {

    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String position;
    private float salary;
    private String photography;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getPhotography() {
        return photography;
    }

    public void setPhotography(String photography) {
        this.photography = photography;
    }

    public Employee toEmployee() {
        Employee employee;
        if (position.equals("WAITER"))
            employee = new Waiter();
        else
            employee = new Cook();
        this.setEmployeeFromRequest(employee);
        return employee;
    }

    public void setEmployeeFromRequest(Employee employee) {
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPhoneNumber(phoneNumber);
        employee.setSalary(salary);
        if (getPhotography() != null)
            employee.setPhotography(Base64.getDecoder().decode(getPhotography()));
    }


    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
