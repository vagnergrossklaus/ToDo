package br.eti.vagnergross.todo.api;

import br.eti.vagnergross.todo.api.rest.ToDoRestAPI;
import br.eti.vagnergross.todo.api.rest.ToDoRestAPILogin;
import br.eti.vagnergross.todo.api.rest.ToDoRestUsuarioAPI;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

/**
 * Created by vagner on 20/04/16.
 */
@ApplicationPath("/rest")
public class ToDoApplication extends Application{

    private Set<Object> singletons = new HashSet<>();

    public ToDoApplication(){

        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedMethods("GET, POST, PUT, DELETE, OPTIONS, HEAD");

        singletons.add(corsFilter);
        singletons.add(new ToDoRestAPI());
        singletons.add(new ToDoRestAPILogin());
        singletons.add(new ToDoRestUsuarioAPI());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}
