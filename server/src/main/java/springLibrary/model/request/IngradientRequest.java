package springLibrary.model.request;

import lombok.Data;
import springLibrary.entities.Ingradient;

@Data
public class IngradientRequest {

    private Long id;
    private String name;
    private float numberOnStorage;


    public Ingradient toIngradient() {
        Ingradient ingradient = new Ingradient();
        if (id != null)
            ingradient.setId(id);
        ingradient.setName(name);
        ingradient.setNumerosity(numberOnStorage);

        return ingradient;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "IngradientRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOnStorage=" + numberOnStorage +
                '}';
    }
}
