package dive.tech.projeto.exercicios.controller;

import dive.tech.projeto.exercicios.dao.CursoDao;
import dive.tech.projeto.exercicios.entity.Curso;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

@Path("/curso")
public class CursoController {

    CursoDao dao = new CursoDao();

    @GET
    @Produces("application/json")
    public Response listarCursos(@QueryParam("nome") String nome) {
        List<Curso> cursos = dao.obterCursos();

        if (nome == null) {
            return Response.ok(cursos).build();
        }

        List<Curso> retorno = new ArrayList<Curso>();
        for (Curso curso : cursos) {
            if (curso.getNome().equals(nome)) {
                retorno.add(curso);
            }
        }

        return Response.ok(retorno).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response criarCurso(Curso curso, @HeaderParam("Authorization") String authorization) {

        if (!"senha123".equals(authorization)) {
            return Response.status(403).build();
        }

        if (curso.getId() == null) {
            curso = dao.criarCurso(curso);
        }
        return Response.ok(curso).status(201).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response buscarCurso(@PathParam("id") Long id) {
        List<Curso> cursos = dao.obterCursos();

        for (Curso curso : cursos) {
            if (curso.getId().equals(id)) {
                return Response.ok(curso).build();
            }
        }

        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarCurso(@FormParam("id") Long id, @FormParam("nome") String nome) {
        List<Curso> cursos = dao.obterCursos();
        Curso curso = null;
        for (Curso c : cursos) {
            if (c.getId().equals(id)) {
                curso = c;
                break;
            }
        }
        if (curso != null) {
            curso.setNome(nome);
            return Response.ok(curso).build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerCurso(@PathParam("id") Long id) {
        List<Curso> cursos = dao.obterCursos();
        for (Curso c : cursos) {
            if (c.getId().equals(id)) {
                cursos.remove(c);
                break;
            }
        }

        return Response.ok(cursos).build();
    }

}
