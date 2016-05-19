package br.eti.vagnergross.todo.api.rest.request;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vagner on 07/05/16.
 */
@Data
@XmlRootElement
public class UsuarioRequest {
    private String nome;
    private String email;
    private String senha;
}
