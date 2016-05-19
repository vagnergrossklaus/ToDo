package br.eti.vagnergross.todo.api.rest;

import br.eti.vagnergross.todo.api.rest.request.CredentialsRequest;
import br.eti.vagnergross.todo.api.rest.response.UserResponse;
import br.eti.vagnergross.todo.services.UsuarioService;
import br.eti.vagnergross.todo.services.domain.Usuario;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by vagner on 07/05/16.
 */
@Path("/access")
public class ToDoRestAPILogin {

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(CredentialsRequest request){

        UsuarioService service = new UsuarioService();

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getUsername());
        usuario.setSenha(request.getPassword());

        usuario = service.obterUsuario(usuario);

        UserResponse response = new UserResponse();
        response.setId(usuario.getId());
        response.setName(usuario.getNome());
        response.setUser(usuario.getEmail());
        response.setRole(usuario.getRole());

        return Response.status(200).entity(response).build();
    }
}
