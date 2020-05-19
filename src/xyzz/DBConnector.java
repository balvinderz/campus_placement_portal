/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyzz;
import java.sql.*;

/**
 *
 * @author USER
 */
public class DBConnector {
    public static Connection getConnection() throws SQLException {
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/campus","root", "");
       return connection;
    }
}
