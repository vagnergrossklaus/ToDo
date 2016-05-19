package br.eti.vagnergross.todo.services.dao;

import br.eti.vagnergross.todo.services.db.ConnectionSQLite;
import br.eti.vagnergross.todo.services.db.tabela.UsuarioTabela;
import br.eti.vagnergross.todo.services.domain.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vagner on 07/05/16.
 */
public class UsuarioDAO {

    public UsuarioDAO(){
        try {
            contarUsuarios();
        } catch (SQLException e) {
            if (e.getMessage().equals("[SQLITE_ERROR] SQL error or missing database (no such table: usuario)")){
                new UsuarioTabela().criar();
            }
        }
    }

    public int contarUsuarios() throws SQLException {

        int cont = 0;
        String sql = "SELECT COUNT(0) AS cont FROM usuario";

        PreparedStatement statement = ConnectionSQLite.getInstance().getConnection().prepareStatement(sql);
        if (statement.execute()) {
            cont = statement.getResultSet().getInt("cont");
        }

        statement.close();

        return cont;
    }

    public Usuario obterUsuario(String email, String senha) {

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        PreparedStatement statement = null;
        Usuario u = new Usuario();
        try {
            statement = ConnectionSQLite.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);

            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setRole(rs.getString("role"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    public int salvarUsuario(Usuario usuario){
        String sql;
        PreparedStatement statement;

        if (usuario.getId() != null){
            sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, role = ? WHERE id = ?;";
        }else{
            sql = "INSERT INTO usuario (nome, email, senha, role) VALUES (?, ?, ?, ?);";
        }

        try {
            statement = ConnectionSQLite.getInstance().getConnection().prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getRole());

            if (usuario.getId() != null){
                statement.setInt(5, usuario.getId());
            }

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
