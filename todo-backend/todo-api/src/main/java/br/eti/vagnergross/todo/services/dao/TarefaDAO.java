package br.eti.vagnergross.todo.services.dao;

import br.eti.vagnergross.todo.services.db.ConnectionSQLite;
import br.eti.vagnergross.todo.services.db.tabela.TarefaTabela;
import br.eti.vagnergross.todo.services.domain.Tarefa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by vagner on 25/04/16.
 */
public class TarefaDAO {

    public TarefaDAO() {

        try {
            contarTarefas();
        } catch (SQLException e) {
            if (e.getMessage().equals("[SQLITE_ERROR] SQL error or missing database (no such table: tarefa)")){
                new TarefaTabela().criar();
            }
        }
    }

    public int contarTarefas() throws SQLException {

        int cont = 0;
        String sql = "SELECT COUNT(0) AS cont FROM tarefa";

        PreparedStatement statement = ConnectionSQLite.getInstance().getConnection().prepareStatement(sql);
        if (statement.execute()) {
            cont = statement.getResultSet().getInt("cont");
        }

        statement.close();

        return cont;
    }

    public int salvaTarefa(Tarefa t) {

        String sql = "";
        PreparedStatement statement = null;

        if (t.getId() != null){
            sql = "UPDATE tarefa SET descricao = ?, concluido = ? WHERE id = ?;";
        }else {
            sql = "INSERT INTO tarefa (idusuario, descricao) VALUES (?, ?);";
        }

        try {

            statement = ConnectionSQLite.getInstance().getConnection().prepareStatement(sql);
            if (t.getId() != null){
                statement.setString(1, t.getDescricao());
                statement.setInt(2, t.getConcluido());
                statement.setInt(3, t.getId());
            }else{
                statement.setInt(1, t.getIdUsuario());
                statement.setString(2, t.getDescricao());
            }

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }

    public Tarefa obterTarefa(int id){

        String sql = "SELECT * FROM tarefa";
        PreparedStatement statement = null;
        Tarefa t = null;
        try {
            statement = ConnectionSQLite.getInstance().getConnection().prepareStatement(sql);

            statement.execute();
            ResultSet rs = statement.getResultSet();

            while ( rs.next() ) {
                t.setId(rs.getInt("id"));
                t.setIdUsuario(rs.getInt("idusuario"));
                t.setDescricao(rs.getString("descricao"));
                t.setConcluido(rs.getInt("concluido"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return t;
    }

    public Collection<Tarefa> obterTarefas(Integer idUsuario, Integer status) {

        String sql;
        if (status >= 0) {
            sql = "SELECT * FROM tarefa WHERE idUsuario = ? AND concluido = ? ORDER BY id DESC";
        }else{
            sql = "SELECT * FROM tarefa WHERE idUsuario = ? ORDER BY id DESC";
        }
        PreparedStatement statement;
        Collection<Tarefa> tarefas = new ArrayList<>();
        try {
            statement = ConnectionSQLite.getInstance().getConnection().prepareStatement(sql);

            statement.setInt(1, idUsuario);
            if (status >= 0) {
                statement.setInt(2, status);
            }

            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                Tarefa t = new Tarefa();
                t.setId(rs.getInt("id"));
                t.setIdUsuario(rs.getInt("idusuario"));
                t.setDescricao(rs.getString("descricao"));
                t.setConcluido(rs.getInt("concluido"));
                tarefas.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tarefas;

    }

    public int deletarTarefa(int id) {
        String sql = "DELETE FROM tarefa WHERE id = ?";

        try {
            PreparedStatement statement = ConnectionSQLite.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        return 1;

    }
}
