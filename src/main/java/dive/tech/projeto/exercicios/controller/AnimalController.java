package dive.tech.projeto.exercicios.controller;

import dive.tech.projeto.exercicios.entity.Animal;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/animal")
public class AnimalController {

    @GET
    @Produces("text/plain")
    public String listarAnimais() {
        StringBuilder retorno = new StringBuilder();
        retorno.append("Cavalo").append(", ")
                .append("Cachorro").append(", ")
                .append("Gato").append(".");

        return retorno.toString();
    }

    @GET
    @Path("{id}")
    @Produces("text/plain")
    public Response buscarAnimalPorId(@PathParam("id") Long id) {
        String retorno = "";
        if (id == 1) {
            retorno = "Macaco";
        } else if (id == 2) {
            retorno = "Cachorro";
        }

        if (retorno.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(retorno).build();
    }

    @GET
    @Path("/filtro")
    @Produces("text/plain")
    public Response filtrarAnimais(@QueryParam("nome") String nome, @QueryParam("especie") String especie) {
        List<Animal> animais = criarListaDeAnimais();

        if (nome == null && especie == null) {
            return Response.ok(animais).build();
        }

        List<Animal> retorno = new ArrayList<Animal>();
        for (Animal animal : animais) {
            if (nome != null && especie != null) {
                if (nome.equalsIgnoreCase(animal.getNome()) && especie.equalsIgnoreCase(animal.getEspecie())) {
                    retorno.add(animal);
                }
            }
            if (nome != null && especie == null) {
                if (nome.equalsIgnoreCase(animal.getNome())) {
                    retorno.add(animal);
                }
            }
            if (especie != null && nome == null) {
                if (especie.equalsIgnoreCase(animal.getEspecie())) {
                    retorno.add(animal);
                }
            }
        }

        if (retorno.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(retorno).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public Response criarUsuario(@HeaderParam("Authorization") String authorization, Animal animal) {
        if (!"codigo123".equalsIgnoreCase(authorization)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        animal.setId(5L);
        return Response.ok(animal).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("text/plain")
    public Response validarAnimal(@Valid Animal animal) {
        List<Animal> animais = criarListaDeAnimais();
        if (animais.contains(animal)) {
            return Response.ok(animais.get(animais.indexOf(animal))).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private List<Animal> criarListaDeAnimais() {
        List<Animal> animais = new ArrayList<Animal>();
        Animal a1 = new Animal(1L, "Abu", "Macaco");
        Animal a2 = new Animal(2L, "Marcel", "Macaco");
        Animal a3 = new Animal(3L, "Bob", "Cachorro");
        Animal a4 = new Animal(4L, "Sagua", "Gato");
        animais.add(a1);
        animais.add(a2);
        animais.add(a3);
        animais.add(a4);
        return animais;
    }


}
