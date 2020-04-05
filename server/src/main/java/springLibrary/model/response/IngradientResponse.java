package springLibrary.model.response;

import lombok.Data;
import springLibrary.entities.Ingradient;
import springLibrary.entities.Menu;

@Data
public class IngradientResponse implements Comparable<IngradientResponse>{
    private Long id;
    private String name;
    private float numberOnStorage;

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

    public float getNumberOnStorage() {
        return numberOnStorage;
    }

    public void setNumberOnStorage(float numberOnStorage) {
        this.numberOnStorage = numberOnStorage;
    }

    public static IngradientResponse of(Ingradient ingradient) {
        IngradientResponse response = new IngradientResponse();
        response.setId(ingradient.getId());
        response.setName(ingradient.getName());
        response.setNumberOnStorage(ingradient.getNumerosity());
        return response;
    }


    @Override
    public int compareTo(IngradientResponse o) {
        int result = this.name.compareTo(o.name);
        return result;
    }

}
