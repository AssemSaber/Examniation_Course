/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;
// لازم
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Admin {
    
    public static void SetAccount(String UserName,String Pass){
      try(FileWriter f=new FileWriter("E:\\Database\\UserPass.txt");) 
        {
            System.out.println("I have received User : "+UserName);
            System.out.println("I have received Pass : "+Pass);
             try(PrintWriter f1=new PrintWriter(f);){
                 f1.println();
                 f1.print(UserName+",");
                 f1.print(Pass);
             }
         catch(Exception e){
           System.out.print(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

}
public static boolean Check_Split(String LineDB,String User,String pass){
	   
           String data[]=LineDB.split(",");
           if(data[0].equals(User)&&data[1].equals(pass))
         { return true;}
         else 
            return false;
            
}

public static boolean CheckAccount(String User,String Pass){
    try (FileReader f=new FileReader("E:\\Database\\UserPass.txt");){       
            Scanner scan=new Scanner(f);
            String s="";
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                if(Check_Split(s,User,Pass))
                {return true;}
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
       
      return false;
}
    
     public boolean login_Admin(String User,String pass){
         if(CheckAccount(User,pass))
             return true;
         else 
             return false;
     }
     public boolean EditUserName(String User,String pass){//  data is coming true
         Scanner in=new Scanner(System.in);
         SetAccount(User,pass);  
         return true;
     }
     public boolean EditPassWord(String User,String pass){//  data is coming true
          Scanner in=new Scanner(System.in);
         SetAccount(User,pass);             
         return true;
     }
     public boolean AddCourse(String Id_Course,String CourseName){
         try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select * from Courses where Id_Course='"+Id_Course+"' or Course_Name='"+CourseName+"'";
         ResultSet rs=stmt.executeQuery(query);
         if(rs.next()){
             return false;
         }
         else 
         {
            CourseName=CourseName.toLowerCase();
            String query2="insert into Courses (Id_Course,Course_Name) values('"+Id_Course+"' , '"+CourseName+"') ";
            int EffectecdRows =stmt.executeUpdate(query2);
            if(EffectecdRows>0)
                return true;
            else
               return false;
         }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
       return false;
     }
      public boolean DeleteCourse(String CourseName){
             try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         //String query="select * from Courses where  NameCourse='"+CourseName+"'";
         //ResultSet rs=stmt.executeQuery(query);
        // if(rs.next()){
          CourseName=CourseName.toLowerCase();
          String query2="delete from Courses where  Course_Name='"+CourseName+"'";
          int rows=stmt.executeUpdate(query2);
          if(rows>0){
             
              return true;
          } 
         //}
         else{
            
             return false;
         }
         
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
       return false;
      }
      public boolean CheckCourse(String CourseName){
         try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select * from Courses where Course_Name ='"+CourseName+"' ";
         ResultSet rs=stmt.executeQuery(query);
         if(rs.next()){
             return true;
         }
         else 
             return false;
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
      }
      public boolean CheckDoctor(String Ssn){
         try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select * from Doctors where Id_Doc ='"+Ssn+"' ";
         ResultSet rs=stmt.executeQuery(query);
         if(rs.next()){
             return true;
         }
         else 
             return false;
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
      }
      public boolean CheckStudent(String Id_Std){
         try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select * from Students where Id_Std ='"+Id_Std+"' ";
         ResultSet rs=stmt.executeQuery(query);
         if(rs.next()){
             return true;
         }
         else 
             return false;
                    
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return false;
      }
      public boolean AddLecturer(String phone,String Address,String FName,String LName,String ssn){
              try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select * from Doctors where Id_Doc='"+ssn+"'";
         ResultSet rs=stmt.executeQuery(query);
         if(rs.next()){
             return false;
         }
         else 
         {
            String CourseName=" ";boolean check=true;
            Scanner in=new Scanner(System.in);            
            FName=FName.toLowerCase();
            LName=LName.toLowerCase();
            Address=Address.toLowerCase();
            String fullName="",pass="";
            fullName=fullName.concat(FName);
            fullName=fullName.concat(LName);
            pass=pass.concat(fullName);
            pass=pass.concat("_");
            pass=pass.concat(ssn);
            phone=phone.toLowerCase();
            ssn=ssn.trim();
            String query2="insert into Doctors (FName,LName,Address,Phone,Id_Doc,UserName,Pass) values('"+FName+"' ,'"+LName+"','"+Address+"','"+phone+"','"+ssn+"', '"+fullName+"','"+pass+"' ) ";
            int EffectecdRows =stmt.executeUpdate(query2);
            if(EffectecdRows>0)
                return true;
            else
                return false;
           
         }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
       return false;
      }
      public boolean DeleteLecturer(String Ssn){
            try{
            Connect c=new Connect(); 
            c.connect();
            Statement stmt=c.connect().createStatement();
            String query="delete from Doctors  where Id_Doc='"+Ssn+"' ";
            int NumberRows=stmt.executeUpdate(query);
            if(NumberRows>0)
                return true;
            else 
               return false;
            }
            catch(SQLException e){
              System.out.println(e.getMessage());
            }
          return false;
      }
      public boolean DeleteStudent(String Id_Std){
            try{
            Connect c=new Connect(); 
            c.connect();
            Statement stmt=c.connect().createStatement();
            String query="delete from Students  where Id_Std='"+Id_Std+"' ";
            int NumberRows=stmt.executeUpdate(query);
            if(NumberRows>0)
               return true;
            else 
               return false;
            }
            catch(SQLException e){
              System.out.println(e.getMessage());
            }
          return false;
      }
    
      public ArrayList<String> Searchlecturer(String Ssn){
          ArrayList<String>ResultSearchLecturer=new ArrayList();
          try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select * from Doctors  where Id_Doc='"+Ssn+"'";
         ResultSet rs=stmt.executeQuery(query);
         if(rs.next()){ 
              ResultSearchLecturer.add(rs.getString("FName"));
              ResultSearchLecturer.add(rs.getString("LName"));
              ResultSearchLecturer.add(rs.getString("phone"));
              ResultSearchLecturer.add(rs.getString("Address"));
            
         }            
         
         String query2="select CourseName from Doctors d,Doctors_Courses dc  where d.Id_Doc=dc.Ssn and dc.Ssn='"+Ssn+"'";
         ResultSet rs2=stmt.executeQuery(query2);
         while(rs2.next()){
              ResultSearchLecturer.add(rs2.getString("CourseName"));
            }
          }
        catch(SQLException e){
            System.out.println(e.getMessage());
        } 
          return ResultSearchLecturer;
      }
      public ArrayList<String> SearchStudent(String Id_Std){
         ArrayList<String>ResultSearchStudent=new ArrayList();
          try{
         Connect c=new Connect(); int check=0;
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select * from Students S where S.Id_Std='"+Id_Std+"'";
         ResultSet rs=stmt.executeQuery(query);
         if(rs.next()){ 
              ResultSearchStudent.add(rs.getString("FName"));
              ResultSearchStudent.add(rs.getString("LName"));
              ResultSearchStudent.add(rs.getString("phone"));
              ResultSearchStudent.add(rs.getString("Address"));
            
         }            
         
         String query2="select * from Students S,Students_Courses Sc  where Sc.Id_Std=s.Id_Std and S.Id_Std='"+Id_Std+"'";
         ResultSet rs2=stmt.executeQuery(query2);
         while(rs2.next()){
              ResultSearchStudent.add(rs2.getString("CourseName"));
            }
          }
        catch(SQLException e){
            System.out.println(e.getMessage());
        } 
          return ResultSearchStudent;
   }
      public boolean UpdateCourseForLecturer(String Ssn,String oldCourse,String UpdateCourse){
        try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update  Doctors_Courses set CourseName='"+UpdateCourse+"' where Ssn='"+Ssn+"' and CourseName='"+oldCourse+"' ";
         int NumberRows=stmt.executeUpdate(query);
         if(NumberRows>0)
             return true;
         else 
            return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
          }
        return false;
        }
        public boolean AssignStudentToCourse(String Id_Std,String CourseName){
           try{
          Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
        String query2="insert Students_Courses (Id_Std,CourseName) values( '"+Id_Std+"','"+CourseName+"') ";
         int NumberRows2=stmt.executeUpdate(query2);
         if(NumberRows2>0)
            return true;
         else 
             return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
          }   
           return false;
        }
         public boolean AssignLecturerToCourse(String Ssn,String CourseName){
           try{
          Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
        String query2="insert Doctors_Courses (Ssn,CourseName) values( '"+Ssn+"','"+CourseName+"') ";
         int NumberRows2=stmt.executeUpdate(query2);
         if(NumberRows2>0)
            return true;
         else 
             return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
          }   
           return false;
        }
        public boolean AddStudent(String phone,String Address,String FName,String LName,String Id_Std){
          try{
          Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String Pass=FName;
         Pass=Pass.concat("_");
         Pass=Pass.concat(Id_Std);
         String query="insert Students (Id_Std,FName,LName,Phone,Address,Pass) values( '"+Id_Std+"','"+FName+"','"+LName+"','"+phone+"','"+Address+"' ,'"+Pass+"') ";
         int NumberRows=stmt.executeUpdate(query);
         if(NumberRows>0){
             LastId incre=new LastId();
             incre.increment();
             return true;
         }
            
         else 
             return false;
         }
        catch(SQLException e){
            System.out.println(e.getMessage());
          } 
          return false;
        }
        public ArrayList<String> List_Lecturers(){
            ArrayList<String>lecturers=new ArrayList();
            try{
                Connect c=new Connect(); 
                c.connect();
                Statement stmt=c.connect().createStatement();
                String query="select Id_Doc from Doctors ";
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next()){
                    lecturers.add(rs.getString("Id_Doc"));
                }
                    
               }
               catch(SQLException e){
                   System.out.println(e.getMessage());
               }
            return lecturers;
        }
          public ArrayList<String> List_Students(){
            ArrayList<String>Students=new ArrayList();
            try{
                Connect c=new Connect(); 
                c.connect();
                Statement stmt=c.connect().createStatement();
                String query="select Id_Std from Students ";
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next()){
                    Students.add(rs.getString("Id_Std"));
                   
                }
                    
               }
               catch(SQLException e){
                   System.out.println(e.getMessage());
               }
            return Students;
        }
        /*public void login(String User,String pass){
        try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="update Students set phone='01019051187' where UserName='"+User+"' and Pass='"+pass+"'";
         int NumberRows=stmt.executeUpdate(query);
         if(NumberRows>0)
             System.out.println("updated successfully");
         else 
             System.out.println("Not found");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    } */     
}
     
