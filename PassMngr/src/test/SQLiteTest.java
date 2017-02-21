/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteTest {
    private static Connection con;
    private static boolean hasData = false;
    
    public ResultSet displayUsers() throws SQLException, ClassNotFoundException{
        if (con == null){
            getConnection();
        }
        
        try {
            Statement state = con.createStatement();
            ResultSet res;
            res = state.executeQuery("SELECT fname, lname FROM user");
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null; 
        
    };
    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:E:\\Java\\PassManager\\PassMngrSQLite.db");
        initialise();
    }

    private void initialise() throws SQLException {
        if(!hasData == true){
            hasData = true;
            
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery("SELECT name FROM sqlite_master WHERE type ='table' and name = 'user'");
            if (!rs.next()){
                System.out.println("Building the user table with prepopulated values.");
                //build table
                Statement state2 = con.createStatement();
                state2.executeUpdate("CREATE TABLE user (id INT," + 
                                " fname varchar (60)," + " lname varchar (60)," 
                                + " PRIMARY KEY (fid))");
                //insert some data
                PreparedStatement prep = con.prepareStatement("INSERT INTO user VALUES(?,?,?);");
                prep.setString(2, "John");
                prep.setString(3, "MCNeil");
                prep.executeUpdate();
                
                PreparedStatement prep2 = con.prepareStatement("INSERT INTO user VALUES(?,?,?);");
                prep2.setString(2, "Vlad");
                prep2.setString(3, "C.");
                prep2.executeUpdate();

            }
            
        }
    }
    
    public void addUser(String firstName,String lastName) throws ClassNotFoundException, SQLException{
        if(con==null){
            getConnection();
        }
        PreparedStatement prep = con.prepareStatement("INSERT INTO user Values(?,?,?);");
        prep.setString(2, firstName);
        prep.setString(3, lastName);
        prep.execute();
        
        
    }
    
}
