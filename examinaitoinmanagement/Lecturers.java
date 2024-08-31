/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.io.FileReader;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
public class Lecturers extends Information{
   @Override
      public boolean Login(String Ssn,String pass){
         try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select Id_Doc,Pass from Doctors  ";
         ResultSet rs=stmt.executeQuery(query);
         while(rs.next()){
             String ssn=rs.getString("Id_Doc");
             String passWord=rs.getString("Pass");
             if(Ssn.equals(ssn) && passWord.equals(pass))
             {return true;}
         }
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
    }
      @Override
      public  boolean setFname(String Ssn,String Fname){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Doctors set FName='"+Fname+"' where Id_Doc='"+Ssn+"' ";
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
      public  boolean setLname(String Ssn,String Lname){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Doctors set LName='"+Lname+"' where Id_Doc='"+Ssn+"' ";
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
      public  boolean setPhone(String Ssn,String phone){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Doctors set Phone='"+phone+"' where Id_Doc='"+Ssn+"' ";
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
      public  boolean setAddress(String Ssn,String Address){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Doctors set Address='"+Address+"' where Id_Doc='"+Ssn+"' ";
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
      public  boolean setPass(String Ssn,String Pass){
           try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Doctors set Pass='"+Pass+"' where Id_Doc='"+Ssn+"' ";
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
      public ArrayList<String> getAllIdes(){
          ArrayList<String>Ides=new ArrayList();
          try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select Id_Std from  Students  ";
         ResultSet rs=stmt.executeQuery(query);
         while(rs.next())
             Ides.add(rs.getString("Id_Std"));
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return Ides;
      }
    public  void addExam(String nameExam,String CourseName,String []actualAnswer){
         Files f=new Files();  
         f.CorrectedAnswer(nameExam, CourseName,actualAnswer);
         f.EnteringOnce(nameExam,CourseName );
    }
    public void deleteExam(String ExamName,String CourseName){
        ArrayList<String>Ides=new ArrayList();
        Files f=new Files();
        f.DeletingExam( ExamName, CourseName,",");
        Ides=getAllIdes();
        for(int x=0;x<Ides.size();x++){
            f.DeletingFromEnteringOnce(Ides.get(x), ExamName, CourseName, ",");
        }
        try{
            Connect c=new Connect(); 
            c.connect();
            Statement stmt=c.connect().createStatement();
            System.out.println("lld");
            String query="delete from  grades  where ExamName='"+ExamName+"' and CourseName='"+CourseName+"'";
            int rows=stmt.executeUpdate(query);
         }
        catch(SQLException e){
            System.out.println(e.getMessage());
       }

    }
    public ArrayList<String> listExams(String CourseName){
        ArrayList<String>Exams=new ArrayList();
        Files f=new Files();
        Exams=f.getAllExamsForLecturer(CourseName);
        return Exams;
    }
    public ArrayList<String> report(String ExamName,String CourseName){
          ArrayList<String>StudentsReport=new ArrayList();
          try{
            Connect c=new Connect(); 
            c.connect();
            Statement stmt=c.connect().createStatement();
            String query="select * from  grades  where ExamName='"+ExamName+"' and CourseName='"+CourseName+"'";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                StudentsReport.add(rs.getString("Id_Std"));
                StudentsReport.add(rs.getString("grade_Std"));
                StudentsReport.add(rs.getString("grade_exam"));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
    }
          return StudentsReport;
  }











//    public void DeleteExam(String ExamName){
//        Files f=new Files();
//        f.DeleteExam(ExamName);
//    }
    /*public static boolean Check_Split(String LineDB,String ExamName){
	   
           String data[]=LineDB.split(",");
           if(data[0].equals(ExamName))
         { return true;}
         else 
            return false;
            
}

public  boolean CheckExam(String ExamName){
    try (FileReader f=new FileReader("E:\\Database\\Exams.txt");){       
            Scanner scan=new Scanner(f);
            String s="";
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                if(Check_Split(s,ExamName))
                {return true;}
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
       
      return false;
    }
   

    public void MoveToTemp(String line){
        try(FileWriter f=new FileWriter("E:\\Database\\temp.txt");) 
        {
             try(PrintWriter f1=new PrintWriter(f);){
              f1.print(line);
              f1.println();
             }
         catch(Exception e){
           System.out.print(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public  void CheckForDeleting(String ExamName){
    try (FileReader f=new FileReader("E:\\Database\\Exams.txt");){       
            Scanner scan=new Scanner(f);
            String s="";boolean check=false;
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                if(!Check_Split(s,ExamName))
                { 
                    MoveToTemp(s);
                     check=true;
                }
            }
            if(!check){
                System.out.println("It is not found");
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
       
    }
    public void WriteInOriginal(String line){
        try(FileWriter f=new FileWriter("E:\\Database\\Exams.txt");) 
        {
             try(PrintWriter f1=new PrintWriter(f);){
              f1.print(line);
              f1.println();
             }
         catch(Exception e){
           System.out.print(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void DeleteExam(String ExamName){
        CheckForDeleting(ExamName);
        try (FileReader f=new FileReader("E:\\Database\\Temp.txt");){       
            Scanner scan=new Scanner(f);
            String s="";
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
               
            }
                    
        }catch (Exception ex) {
          System.out.println(ex);
        }
    }
*/
}
