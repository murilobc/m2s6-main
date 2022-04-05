package dive.tech.projeto.exercicios.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @Size(min = 3, max = 20, message = "O nome precisa ter entre 3 e 20 caracteres")
    private String nome;

    @Size(min = 5, message = "A especie precisa ter no mínimo 5 caracteres")
    private String especie;

    public Animal(Long id, String nome, String especie) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
    }

    public Animal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return getId().equals(animal.getId()) && getNome().equals(animal.getNome()) && getEspecie().equals(animal.getEspecie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getEspecie());
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                '}';
    }
}
