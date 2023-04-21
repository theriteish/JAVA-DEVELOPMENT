package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //we want to run our queries from java thats why we are establishing connection:
    //we are making a singleton class:

    static Connection connection = null;
    //this method is going to setup the connection
    public static Connection getConnection(){
        if(connection != null){
            //this means we already have a connection instance we will simply return conection
            return connection;
        }
        //we need establish connection if there is not one
        //for establishing connection we need username , pwd & name of database

        String user = "root";
        String pwd = "2010";
        String db = "searchengineapp";
        return getConnection(user,pwd,db);
    }
    private static Connection getConnection(String user , String pwd , String db) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + user + "&password=" + pwd);

        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

}
