package springLibrary.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Администратор on 20.06.16.
 */
@Entity
@Table(name = "storage")
@Getter
@Setter
public class Ingradient extends AbstractIdentifiableEntity {


    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "numerosity", nullable = false)
    private int numerosity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumerosity() {
        return numerosity;
    }

    public void setNumerosity(int numerosity) {
        this.numerosity = numerosity;
    }

    @Override
    public String toString() {
        return "Ingradient{" +
                ", name='" + name + '\'' +
                ", numerosity=" + numerosity +
                '}';
    }

}
