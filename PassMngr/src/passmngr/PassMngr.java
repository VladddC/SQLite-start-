/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passmngr;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.SQLiteTest;

/**
 *
 * @author Freeware Sys
 */
public class PassMngr {

    
    public static void main(String[] args) {
        
        SQLiteTest test = new SQLiteTest();
        ResultSet rs;
        
       
        
        try {
            rs = test.displayUsers();
            while(rs.next()){
                System.out.println(rs.getString("fname")+ " " + rs.getString("lname"));
            }
        } catch (Exception ex) {
            Logger.getLogger(PassMngr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
