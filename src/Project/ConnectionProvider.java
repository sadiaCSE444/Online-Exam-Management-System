/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammad Ali
 */
public class ConnectionProvider {
    public static Connection getCon()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/oems","root","");
            System.out.println("db connected");
            return con;
        }
        catch(Exception e)
        {
            System.out.println("error: "+" "+e);
            return null;
        }
    }
    
    
    
   
}
