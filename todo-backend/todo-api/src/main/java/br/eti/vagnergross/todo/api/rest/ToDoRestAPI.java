package br.eti.vagnergross.todo.api.rest;

import br.eti.vagnergross.todo.api.rest.request.TarefaRequest;
import br.eti.vagnergross.todo.services.TarefaService;
import br.eti.vagnergross.todo.services.domain.Tarefa;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by vagner on 20/04/16.
 */
@Path("/tarefa")
public class ToDoRestAPI {

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public Response post(TarefaRequest tarefa){

        TarefaService service = new TarefaService();
        service.adicionarTarefa(tarefa.getIdUsuario(), tarefa.getDescricao());

        String result = "Sucesso";

        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/{id}/{status}")
    @Produces("application/json")
    public Response obterTarefas(@PathParam("id") int id, @PathParam("status") int status){
        TarefaService service = new TarefaService();
        Collection<Tarefa> tarefas = service.obterTarefas(id, status);
        return Response.status(200).entity(tarefas).build();
    }

    @PUT
    @Path("/{param}")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response put(@PathParam("param") int id, TarefaRequest tarefa){

        TarefaService service = new TarefaService();
        Tarefa t = new Tarefa();
        t.setId(tarefa.getId());
        t.setDescricao(tarefa.getDescricao());
        t.setConcluido(tarefa.getConcluido());
        service.editarTarefa(tarefa.getId(), t);

        String result = "Sucesso ";

        return Response.status(200).entity(result).build();
    }

    @DELETE
    @Path("/{param}")
    @Produces("text/plain")
    public Response delete(@PathParam("param") int id){
        TarefaService service = new TarefaService();
        service.deletarTarefa(id);
        String result = "REstful example: ";
        return Response.status(200).entity(result).build();
    }

}
