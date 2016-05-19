package br.eti.vagnergross.todo.services.db.tabela;

import br.eti.vagnergross.todo.services.db.ConnectionSQLite;

import java.sql.SQLException;

/**
 * Created by vagner on 26/04/16.
 */
public class TarefaTabela {

    public void criar(){
        String sql = "CREATE TABLE IF NOT EXISTS tarefa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idusuario INTEGER NOT NULL," +
                "descricao TEXT NOT NULL," +
                "concluido INTEGER DEFAULT 0)";
        try {
            ConnectionSQLite.getInstance().getConnection().prepareStatement(sql).execute();
            ConnectionSQLite.getInstance().getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
