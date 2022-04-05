package dive.tech.projeto.exercicios.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Path("/sistema")
public class SistemaController {

    @GET
    @Path("/cabecalho")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cabecalhos(@Context HttpHeaders headers) {
        return Response.ok(headers.getRequestHeaders()).build();
    }

    @GET
    @Path("/cookie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCookies(@Context HttpHeaders headers) {
        return Response.ok(headers.getCookies()).build();
    }


    @POST
    @Path("/cookie")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response criarCookie(String nome) {
        NewCookie cookie = new NewCookie(nome, nome);
        return Response.ok().cookie(cookie).build();
    }
}
