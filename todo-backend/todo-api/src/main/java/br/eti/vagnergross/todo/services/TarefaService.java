package br.eti.vagnergross.todo.services;

import br.eti.vagnergross.todo.services.dao.TarefaDAO;
import br.eti.vagnergross.todo.services.domain.Tarefa;

import java.util.Collection;

/**
 * Created by vagner on 20/04/16.
 */
public class TarefaService {

    public Tarefa obterTarefa(int id){
        TarefaDAO dao = new TarefaDAO();
        return dao.obterTarefa(id);
    }

    public Collection<Tarefa> obterTarefas(Integer idUsuario, Integer status){
        TarefaDAO dao = new TarefaDAO();
        return dao.obterTarefas(idUsuario, status);
    }

    public void adicionarTarefa(Integer idUsuario, String descricao){
        TarefaDAO dao = new TarefaDAO();
        Tarefa t = new Tarefa();
        t.setIdUsuario(idUsuario);
        t.setDescricao(descricao);
        dao.salvaTarefa(t);
    }

    public void editarTarefa(int id, Tarefa t){
        TarefaDAO dao = new TarefaDAO();
        dao.salvaTarefa(t);
    }

    public void deletarTarefa(int id){
        TarefaDAO dao = new TarefaDAO();
        dao.deletarTarefa(id);
    }

}
