package springLibrary.entities;


import javax.persistence.*;
import java.util.Set;

/**
 * Created by Администратор on 26.06.16.
 */
@Entity
public class Waiter extends Employee {

    public Waiter()
    {

    }

    public Waiter(Employee employee)
    {
       // if(em)
        this.setId(employee.getId());
        this.setName(employee.getName());
        this.setPhoneNumber(employee.getPhoneNumber());
        this.setPhotography(employee.getPhotography());
        this.setSalary(employee.getSalary());
    }

    @OneToMany(mappedBy = "waiter", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Orders> orders;

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Waiter (\n");
        sb.append("id = (\n").append(getId()).append("\n");
        sb.append("name = (\n").append(getName()).append("\n");
        sb.append("surname = (\n").append(getSurname()).append("\n");
        sb.append("telephone = (\n").append(getPhoneNumber()).append("\n");
        sb.append("salary = (\n").append(getSalary()).append("\n");
        sb.append("orders = (\n").append(orders).append("\n");
        sb.append(" )\n");
        sb.append(")\n");
        return sb.toString();
    }
}
