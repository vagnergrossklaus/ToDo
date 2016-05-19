package br.eti.vagnergross.todo.services.domain;

import lombok.Data;

/**
 * Created by vagner on 25/04/16.
 */
@Data
public class Tarefa {
    private Integer id;
    private Integer idUsuario;
    private String descricao;
    private Integer concluido;
}
