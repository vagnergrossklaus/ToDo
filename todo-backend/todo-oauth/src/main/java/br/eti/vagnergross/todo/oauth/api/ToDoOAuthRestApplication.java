package br.eti.vagnergross.todo.oauth.api;

import br.eti.vagnergross.todo.oauth.api.rest.ToDoOAuthRestAPI;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vagner on 05/05/16.
 */
@ApplicationPath("/rest")
public class ToDoOAuthRestApplication extends Application {
    private Set<Object> singletons = new HashSet();

    public ToDoOAuthRestApplication(){
        singletons.add(new ToDoOAuthRestAPI());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
