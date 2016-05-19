package br.eti.vagnergross.todo.services.domain;

import lombok.Data;

/**
 * Created by vagner on 07/05/16.
 */
@Data
public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String role;

}
