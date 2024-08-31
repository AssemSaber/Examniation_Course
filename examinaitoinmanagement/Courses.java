/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.sql.ResultSet;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Moham
 */
public class Courses {
     public ArrayList<String> getAllCoursesForStd(String Id){
       ArrayList<String>AllCourses=new ArrayList();
       try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select CourseName from Students_Courses where Id_Std='"+Id+"' ";
         ResultSet rs=stmt.executeQuery(query);
         while(rs.next()){
             String course=rs.getString("CourseName");
             AllCourses.add(rs.getString("CourseName"));
           }
       
       }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return AllCourses;
   }
    public ArrayList<String> Courses_Doctor(String Ssn){
            ArrayList<String>CoursesDoctor=new ArrayList();
            try{
            Connect c=new Connect(); 
            c.connect();
            Statement stmt=c.connect().createStatement();
            String query="select CourseName from Doctors_Courses  where Ssn='"+Ssn+"'";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            CoursesDoctor.add(rs.getString("CourseName"));    
            }
           catch(SQLException e){
               System.out.println(e.getMessage());
           }
            return CoursesDoctor;
        }
     public ArrayList<String> getAllGeneralCourses(){
       ArrayList<String>AllCourses=new ArrayList();
       try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select Course_Name from Courses  ";
         ResultSet rs=stmt.executeQuery(query);
         while(rs.next()){
             String course=rs.getString("Course_Name");
             AllCourses.add(rs.getString("Course_Name"));
           }
       
       }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return AllCourses;
   }
}
