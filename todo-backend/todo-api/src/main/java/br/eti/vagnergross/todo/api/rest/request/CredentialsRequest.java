package br.eti.vagnergross.todo.api.rest.request;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vagner on 05/05/16.
 */
@Data
@XmlRootElement
public class CredentialsRequest {
    private String username;
    private String password;
}
