package springLibrary.model.response;

import springLibrary.entities.Employee;

import java.util.Base64;


public class EmployeeResponse {

    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String position;
    private Float salary;
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

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getPhotography() {
        return photography;
    }

    public void setPhotography(byte[] photography) {

        this.photography = Base64.getEncoder().encodeToString(photography);
    }

    public static EmployeeResponse of(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        if (employee.getPhotography() != null) {
            response.setPhotography(employee.getPhotography());
        }
        response.setSurname(employee.getSurname());
        response.setPhoneNumber(employee.getPhoneNumber());
        response.setSalary(employee.getSalary());
        return response;
    }


}
