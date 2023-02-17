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
        this.setIngradientFromRequest(ingradient);
        return ingradient;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getNumberOnStorage() {
        return numberOnStorage;
    }

    public void setIngradientFromRequest(Ingradient ingradient) {
        ingradient.setName(name);
        ingradient.setNumerosity(numberOnStorage);
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
