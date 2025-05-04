package com.Comercio.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBaseDatos {
    private static final String PROPERTIES_FILE = "config.properties";

    //instancia unica (Singleton) de Conexion
    private static Connection connection;

    public static Connection getConnection() throws SQLException, IOException{
        //si no existe o esta cerrada, crea una nueva conexion
        if(connection == null || connection.isClosed()){
            Properties props = new Properties();
            props.load(ConexionBaseDatos.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
            //datos de conexion desde archivo properties
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            //Establece conexion a bd usando DriverManager
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException{
        if(connection != null && !connection.isClosed()){
            connection.close();
        }
    }
}
