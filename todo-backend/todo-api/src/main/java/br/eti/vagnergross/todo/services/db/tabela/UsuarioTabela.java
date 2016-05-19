package br.eti.vagnergross.todo.services.db.tabela;

import br.eti.vagnergross.todo.services.db.ConnectionSQLite;

import java.sql.SQLException;

/**
 * Created by vagner on 07/05/16.
 */
public class UsuarioTabela {
    public void criar(){
        String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "senha TEXT NOT NULL," +
                "role TEXT NOT NULL)";
        try {
            ConnectionSQLite.getInstance().getConnection().prepareStatement(sql).execute();
            ConnectionSQLite.getInstance().getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
