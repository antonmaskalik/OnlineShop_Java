package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private int id;
    private String name;
    private String status;

    @Override
    public String toString() {
        return "Pet{id='" + id + "', name='" + name + "', status=" + status + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pet pet = (Pet) obj;
        return id == pet.getId()
                && Objects.equals(name, pet.name)
                && Objects.equals(status, pet.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }
}