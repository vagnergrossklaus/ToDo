package br.eti.vagnergross.todo.services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vagner on 25/04/16.
 */
public class ConnectionSQLite {

    private static ConnectionSQLite instance = null;
    private static Connection conn;

    protected ConnectionSQLite(){

    }

    public static ConnectionSQLite getInstance() {
        if (instance == null){
            instance = new ConnectionSQLite();
        }
        return instance;
    }

    public Connection getConnection(){
        if (conn == null) try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:tarefa.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
