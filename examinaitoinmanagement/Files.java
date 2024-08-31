/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Files {
       Files(){
           System.out.println("");
       }
    public boolean Check_Split(String LineDB,String Id_Std,String ExamName,String CourseName){
	   //check id of std is there in the file to enter once only the exam
         String data[]=LineDB.split(",");
         if(data[0].equals(Id_Std)&&data[1].equals(ExamName)&&data[2].equals(CourseName))
         { return true;}
         else 
            return false;
          
            
}  
    public boolean ReadLines(String Id_Std,String ExamName,String CourseName){
         try (FileReader f=new FileReader("E:\\Database\\EnteringOnce.txt");){       
            Scanner scan=new Scanner(f);
            String s=" ";
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                if(Check_Split(s, Id_Std,ExamName, CourseName))
                      return true;
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
       
      return false;
    }
    public boolean Check_Split(String LineDB,String name,String CourseName){
            //check examname to avoid redundancy for the names in the same course for lecturer
           String data[]=LineDB.split(",");
         if(data[0].equals(name)&&data[1].equals(CourseName))
         { return true;}
         else 
            return false;
          
            
}
public  boolean CheckExam(String nameExam,String CourseName){
    try (FileReader f=new FileReader("E:\\Database\\Exams.txt");){       
            Scanner scan=new Scanner(f);
            String s=" ";
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                if(Check_Split(s,nameExam,CourseName))
                      return true;
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
       
      return false;
  }
  /*public void addQuestion(String nameExam,String CourseId,String duratation,String Ques,String a,String b,String c,String d){
     try(FileWriter f=new FileWriter("E:\\Database\\Exams.txt",true);) 
        {
             try(PrintWriter f1=new PrintWriter(f);){
                 f1.print(nameExam);
                 f1.print(",");
                 f1.print(CourseId);
                 f1.print(",");
                 f1.print(duratation);
                 f1.print(",");
                 f1.print(Ques);
                 f1.print(",");
                 f1.print(a);
                 f1.print(",");
                 f1.print(b);
                 f1.print(",");
                 f1.print(c);
                 f1.print(",");
                 f1.print(d);
                 f1.println();
             }
         catch(Exception e){
           System.out.print(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
  
  }*/
  public void CorrectedAnswer(String nameExam,String CourseId,String answer[]){
      try(FileWriter f=new FileWriter("E:\\Database\\AnswersExams.txt",true);) 
        {
             try(PrintWriter f1=new PrintWriter(f);){
               
               f1.print(nameExam);
               f1.print(",");
               f1.print(CourseId);
               for(int x=0;x<answer.length;x++){
                     f1.print(",");                   
                     f1.print(answer[x]);
                 }
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
   public void EnteringOnce(String NameExam,String NameCourse){
        try(FileWriter f=new FileWriter("E:\\Database\\EnteringOnce.txt",true);) 
        {
             try(PrintWriter f1=new PrintWriter(f);){
                 try{
                    Connect c=new Connect(); 
                    c.connect();
                    Statement stmt=c.connect().createStatement();
                    String query="select Id_Std from Students";
                    ResultSet rs=stmt.executeQuery(query);
                    while(rs.next()){
                    f1.print(rs.getString("Id_Std"));
                    f1.print(",");
                    f1.print(NameExam);
                    f1.print(",");
                    f1.print(NameCourse);                   
                    f1.print(",");
                    f1.print(0);        
                    f1.println();
                    }    
                 }
                    catch(SQLException e){
                        System.out.println(e.getMessage());
                    }               
             }
         catch(Exception e){
           System.out.print(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
   }
   
 /*public boolean Check_Split1(String LineDB,String name){
	   
           String data[]=LineDB.split(",");
         if(data[0].equals(name))
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
                if(Check_Split1(s,ExamName))
                {return true;}
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
       
      return false;
    }
*/    
    
    public void Deleting(String FilePath,String removeTerm,int positionTerm,String delimiter){
        int position=positionTerm-1;
        String tempfile="E:\\Database\\Temp.txt";
           String Filepath;
        File oldFile=new File(FilePath);
        File newFile=new File(tempfile);
        String currentLine;
        String data[];

        try{
            FileWriter fw=new FileWriter(tempfile,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);
            FileReader fr=new FileReader(FilePath);
            BufferedReader br=new BufferedReader(fr);
            
            while((currentLine=br.readLine())!=null){
                data=currentLine.split(",");
                if(!data[position].equalsIgnoreCase(removeTerm))
                    pw.println(currentLine);
            }
            
            pw.flush();
            pw.close();
            br.close();
            bw.close();
            fw.close();
            oldFile.delete();
            File dump=new File(FilePath);
            newFile.renameTo(dump);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void DeletingFromEnteringOnce(String removeTermId,String removeTermExamName,String removeTermCourseName,String delimiter){
        int positionId=0;
        int positionExamN=1;
        int positinoCourseN=2;
        String tempfile="E:\\Database\\Temp.txt";
        File oldFile=new File("E:\\Database\\EnteringOnce.txt");
        File newFile=new File(tempfile);
        String currentLine;
        String data[];

        try{
            FileWriter fw=new FileWriter(tempfile,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);
            FileReader fr=new FileReader("E:\\Database\\EnteringOnce.txt");
            BufferedReader br=new BufferedReader(fr);
            
            while((currentLine=br.readLine())!=null){
                data=currentLine.split(",");
                if(!(data[positionId].equalsIgnoreCase(removeTermId) &&data[positionExamN].equalsIgnoreCase(removeTermExamName)&& data[positinoCourseN].equalsIgnoreCase(removeTermCourseName)))
                    pw.println(currentLine);
            }
            
            pw.flush();
            pw.close();
            br.close();
            bw.close();
            fw.close();
            oldFile.delete();
            File dump=new File("E:\\Database\\EnteringOnce.txt");
            newFile.renameTo(dump);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    public void DeletingExam(String removeTermExamName,String removeTermCourseName,String delimiter){
        int positionExamN=0;
        int positinoCourseN=1;
        String tempfile="E:\\Database\\Temp.txt";
        File oldFile=new File("E:\\Database\\Exams.txt");
        File newFile=new File(tempfile);
        String currentLine;
        String data[];

        try{
            FileWriter fw=new FileWriter(tempfile,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);
            FileReader fr=new FileReader("E:\\Database\\Exams.txt");
            BufferedReader br=new BufferedReader(fr);
            
            while((currentLine=br.readLine())!=null){
                data=currentLine.split(",");
                if(!(data[positionExamN].equalsIgnoreCase(removeTermExamName)&& data[positinoCourseN].equalsIgnoreCase(removeTermCourseName)))
                    pw.println(currentLine);
            }
            
            pw.flush();
            pw.close();
            br.close();
            bw.close();
            fw.close();
            oldFile.delete();
            File dump=new File("E:\\Database\\Exams.txt");
            newFile.renameTo(dump);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       
        
    }
   public boolean EnteringExam(String Id_Std,String ExamName,String CourseName){
       if(ReadLines( Id_Std, ExamName, CourseName))
       {   
           //delete from entering once
           return true;
           
       }
       return false;
   }
   public String ReadExamsFromFile(String line,String Id,String CourseName){
        String data[]=line.split(",");
         
         if(data[0].equals(Id)&&data[2].equals(CourseName))
         { return data[1];}
        return "";  
   }
   public ArrayList<String> ReadAllExams(String Id,String CourseName){
       ArrayList<String>Exams=new ArrayList();
       try (FileReader f=new FileReader("E:\\Database\\EnteringOnce.txt");){       
            Scanner scan=new Scanner(f);            
            String s=" ";
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                String retur=ReadExamsFromFile(s,Id,CourseName);
                if(retur!=""){
                    Exams.add(retur);  
                    
                }
                    
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
       return Exams;
   }
   
   public ArrayList<String> getExams(String Id,String CourseName){
        ArrayList<String> Exams=new ArrayList();
       Exams=ReadAllExams(Id,CourseName);
       return Exams;
   }
   public boolean Check_Split_showExam3(String LineDB,String ExamName,String CourseName){
         String data[]=LineDB.split(",");
         if(data[0].equals(ExamName)&&data[1].equals(CourseName))
         {return true;
         }
         else 
            return false;
          
            
}  
   public  ArrayList<String> ShowExam2(String ExamName,String CourseName){
    String s=""; ArrayList<String>ShowEx=new ArrayList();
       try (FileReader f=new FileReader("E:\\Database\\Exams.txt");){       
            Scanner scan=new Scanner(f);     
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                if(Check_Split_showExam3(s,ExamName,CourseName))
                    {String data[]=s.split(",");
                        for(int x=2;x<data.length;x++){
                            ShowEx.add(data[x]);
                        }
                            
                    }
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
     return ShowEx;
    }
    public boolean Check_Split_ExamsForLecturer(String LineDB,String CourseName){
         String data[]=LineDB.split(",");
         if(data[1].equals(CourseName))
         {  return true;
         }
         else 
            return false;
          
            
}  
   public  ArrayList<String> getAllExamsForLecturer(String CourseName){
    String s=""; ArrayList<String>DoctorExams=new ArrayList();
       try (FileReader f=new FileReader("E:\\Database\\Exams.txt");){       
            Scanner scan=new Scanner(f);     
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                if(Check_Split_ExamsForLecturer(s,CourseName))
                    {String data[]=s.split(",");
                            DoctorExams.add(data[0]);
                    }
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
     return DoctorExams;
    }
   public ArrayList<String> ShowExamsHavaGrades(String Id,String CourseName){
       ArrayList<String>exams=new ArrayList();
       try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="select ExamName,grade_Std,grade_Exam from grades where Id_Std='"+Id+"' and CourseName ='"+CourseName+"' ";
         ResultSet rs=stmt.executeQuery(query);
         while(rs.next()){
             
             exams.add(rs.getString("ExamName"));
             exams.add(rs.getString("grade_Std"));
             exams.add(rs.getString("grade_exam"));
           }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         return exams;
   }
  /* public static void ShowModel(ArrayList<String> ShowEx){
       int temp=6,Iterator_question_answer=1;
       for(int x=1;x<=ShowEx.size()/6;x++){
            if(Iterator_question_answer==temp){
                temp+=6;
                Iterator_question_answer++;
            }
            System.out.println(x+") "+ShowEx.get(Iterator_question_answer));
            Iterator_question_answer++;
            System.out.print("a) "+ShowEx.get(Iterator_question_answer)+"\t");
            Iterator_question_answer++;
            System.out.print("b) "+ShowEx.get(Iterator_question_answer)+"\t");
            Iterator_question_answer++;
            System.out.print("c) "+ShowEx.get(Iterator_question_answer)+"\t");
            Iterator_question_answer++;
            System.out.print("d) "+ShowEx.get(Iterator_question_answer)+"\t");
            Iterator_question_answer++;
            System.out.println();
        }
   }*/
   
   
   /*public ArrayList<String> getAllCourses(String Id){
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
   }*/
}
