package br.eti.vagnergross.todo.api.rest;

import br.eti.vagnergross.todo.api.rest.request.UsuarioRequest;
import br.eti.vagnergross.todo.api.rest.response.DefaultResponse;
import br.eti.vagnergross.todo.services.UsuarioService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by vagner on 17/05/16.
 */
@Path("/usuario")
public class ToDoRestUsuarioAPI {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(UsuarioRequest request){

        UsuarioService service = new UsuarioService();
        service.salvarUsuario(service.usuarioResquestToUsuario(request));

        DefaultResponse response = new DefaultResponse();
        response.setCodigo(1);
        response.setMensagem("Usuário cadastrado com sucesso");

        return Response.status(200).entity(response).build();
    }

}
