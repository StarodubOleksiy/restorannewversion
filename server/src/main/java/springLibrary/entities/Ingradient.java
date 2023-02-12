package springLibrary.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;
import springLibrary.exceptions.NoIngradientsOnStorageException;

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
    private float numerosity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.capitalize(name.toLowerCase());
    }

    public float getNumerosity() {
        return numerosity;
    }

    public void setNumerosity(float numerosity) {
        if (numerosity < 0)
            throw new NoIngradientsOnStorageException("There are no more ingradients on the storage.");
        else
            this.numerosity = numerosity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingradient)) return false;
        Ingradient that = (Ingradient) o;
        return getName().toLowerCase().equals(that.getName().toLowerCase());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ingradient{" +
                ", name='" + name + '\'' +
                ", numerosity=" + numerosity +
                '}';
    }

}
