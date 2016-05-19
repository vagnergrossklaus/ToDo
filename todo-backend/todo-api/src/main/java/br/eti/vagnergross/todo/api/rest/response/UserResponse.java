package br.eti.vagnergross.todo.api.rest.response;

import lombok.Data;

/**
 * Created by vagner on 05/05/16.
 */
@Data
public class UserResponse {
    private int id;
    private String user;
    private String role;
    private String name;
}
