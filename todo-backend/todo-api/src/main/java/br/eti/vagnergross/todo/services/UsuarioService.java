package br.eti.vagnergross.todo.services;

import br.eti.vagnergross.todo.api.rest.request.UsuarioRequest;
import br.eti.vagnergross.todo.services.dao.UsuarioDAO;
import br.eti.vagnergross.todo.services.domain.Usuario;

/**
 * Created by vagner on 07/05/16.
 */
public class UsuarioService {

    public int salvarUsuario(Usuario usuario){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.salvarUsuario(usuario);
    }

    public Usuario obterUsuario(Usuario usuario){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.obterUsuario(usuario.getEmail(), usuario.getSenha());
    }

    public Usuario usuarioResquestToUsuario(UsuarioRequest usuarioRequest){

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setRole("editor");

        return usuario;
    }

}
