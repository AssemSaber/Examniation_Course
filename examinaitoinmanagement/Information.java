/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Information {
     private String Fanem,Lname,Address,Phone;
      public abstract boolean Login(String identify,String pass);
    public abstract boolean setFname(String identify,String Fanem);
    public abstract boolean setLname(String identify,String Lname);
    public abstract boolean setAddress(String identify,String Address); 
    public abstract boolean setPhone(String identify,String Phone) ;
    public abstract boolean setPass(String identify,String Pass) ;
    public static void function(Information obj,String who,String Identify,String value){//either std or lec and this function in the parent only due to static
        switch(who){
            case "Pass":
                obj.setPass(Identify, value);
                break;
            case "Phone":
                obj.setPhone(Identify, value);
                break;
            case "Address":
                obj.setAddress(Identify, value);
                break;
            case "FName":
                obj.setFname(Identify, value);
                break;
            case "LName":
                obj.setLname(Identify, value);
            case "login":
                obj.Login(Identify, value);
                break;
        }
    }
}
