/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author Moham
 */
public class Connect {
    private final    static String user ="root";
    private final    static String pass="";
    private final    static String host="jdbc:mysql://localhost/examination";
   public Connection connect() throws SQLException{
       return DriverManager.getConnection(host, user, pass);
   }

}
