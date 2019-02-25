/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pc
 */
public class MyConnection {
    
    String url="jdbc:mysql://localhost:3306/pepiniere";
    String login="root";
    String pwd="";
    Connection cnx;
    static MyConnection  instance;

    public MyConnection() {
    
     try{
         cnx=  DriverManager.getConnection(url,login,pwd);
            System.out.println("Connection Etablie");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
            
        }
    }
    public static MyConnection getMyConnection(){
        
        if(instance ==null){
            instance = new MyConnection();
        }
        return instance ;
    }
    
    
    
    
    public void connectToDB(){
       
        
        
    }
    public Connection getCnx(){
       return cnx;
    }
    
    
   
}
