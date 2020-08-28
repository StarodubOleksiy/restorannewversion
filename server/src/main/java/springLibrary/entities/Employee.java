package springLibrary.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by Администратор on 04.05.16.
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee extends AbstractIdentifiableEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "salary", nullable = false)
    private Float salary;

    String myGoalIs = "to obtain the perspective position Java QA Automation Engineer, gain work experience, new knowledge and skills about QA automation technologies, participate in the automation testing and improve software projects, make it better, more user-friendly, more reliable and become professional QA Automation Engineer";

    @Column(name = "photography")
    private byte[] photography ;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhotography() {
        return photography;
    }

    public void setPhotography(byte[] photography) {
        this.photography = photography;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(employee.phoneNumber) : employee.phoneNumber != null)
            return false;
        if (salary != null ? !salary.equals(employee.salary) : employee.salary != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone_number=" + phoneNumber +
                ", salary=" + salary +
        //        ", position='" + position + '\'' +
                '}';
    }


}
