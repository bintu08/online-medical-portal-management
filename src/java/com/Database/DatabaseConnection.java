/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ramakrishna
 */
public class DatabaseConnection {
   public Connection getConnection(){
    Connection con=null;
    
    try{
    
      Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical", "root", "root");
            System.out.println("connected.....");
        
    }catch(Exception e){
    e.printStackTrace();
    }
    
    
    return con;
    
    }
 
}
