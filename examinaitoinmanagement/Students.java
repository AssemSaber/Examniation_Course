/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

public class Students extends Information {
    @Override
      public boolean Login(String Id,String pass){
         try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select Id_Std,Pass from Students  ";
         ResultSet rs=stmt.executeQuery(query);
         while(rs.next()){
             String id=rs.getString("Id_Std");
             String passWord=rs.getString("Pass");
             if(id.equals(Id) && passWord.equals(pass))
                 return true;
         }
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
    }
      @Override
      public  boolean setFname(String Id,String Fname){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Students set FName='"+Fname+"' where Id_Std='"+Id+"' ";
         int rows=stmt.executeUpdate(query);
         if(rows>0)
             return true;
         else 
             return false;
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
      }
       @Override
      public  boolean setLname(String Id,String Lname){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Students set LName='"+Lname+"' where Id_Std='"+Id+"' ";
         int rows=stmt.executeUpdate(query);
         if(rows>0)
             return true;
         else 
             return false;
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
      }
      @Override
      public  boolean setPhone(String Id,String phone){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Students set Phone='"+phone+"' where Id_Std='"+Id+"' ";
         int rows=stmt.executeUpdate(query);
         if(rows>0)
             return true;
         else 
             return false;
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
      }
      @Override
      public  boolean setAddress(String Id,String Address){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Students set Address='"+Address+"' where Id_Std='"+Id+"' ";
         int rows=stmt.executeUpdate(query);
         if(rows>0)
             return true;
         else 
             return false;
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
      }
      @Override
      public  boolean setPass(String Id,String Pass){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Students set Pass='"+Pass+"' where Id_Std='"+Id+"' ";
         int rows=stmt.executeUpdate(query);
         if(rows>0)
             return true;
         else 
             return false;
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
      }
      public boolean EnterFOrExam(String Id_Std,String ExamName,String CourseName){
        Files f=new Files();
        return f.EnteringExam(Id_Std, ExamName, CourseName);
    }
      public ArrayList<String> ShowExam(String ExamName,String CourseName){
       ArrayList<String> ShowEx=new ArrayList();
       Files f=new Files();
       ShowEx=f.ShowExam2(ExamName,CourseName);
       return ShowEx;
   }
   public int[] getGrades(String Id,String ExamName,String CourseName){
       int arr[]=new int[2];
        try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select * from grades where Id='"+Id+"'and ExamName='"+ExamName+"'and CourseName'"+CourseName+"' ";
         ResultSet rs=stmt.executeQuery(query);
         if(rs.next()){
             // for student
             arr[0]=rs.getInt("grade_Std");
             arr[1]=rs.getInt("grade_exam");
          }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
     return arr;    
   } 
   
}
