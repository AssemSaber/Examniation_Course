/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.io.*;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Moham
 */
public class SystemCalcGrade {
    public static ArrayList<String> Check_Split_grade(String line,String ExamName,String CourseName){
        String data[]=line.split(",");
        ArrayList<String>corrected=new ArrayList();
        if(ExamName.equals(data[0])&& CourseName.equals(data[1]))
        { for(int x=2;x<data.length;x++)
            corrected.add(data[x]);
               
        }
         return corrected;
    }
    public static ArrayList<String> ReadCorrectedAnswer(String ExamName,String CourseName){
              ArrayList<String>corrected=new ArrayList();
              ArrayList<String>temp=new ArrayList();
         try (FileReader f=new FileReader("E:\\Database\\AnswersExams.txt");){       
            Scanner scan=new Scanner(f);      
            String s=" ";
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                temp=Check_Split_grade(s, ExamName, CourseName);
                if(temp.size()>0){
                    for(int x=0;x<temp.size();x++)
                        corrected.add(temp.get(x)) ;
                }
                       
            }
        }catch (Exception ex) {
          System.out.println(ex);
        }
         
           return corrected ;
    }
    public static void StoringGrade(String Id,String ExamName,String CourseName,String grade_std,String grade_exam){
        try{
         Connect c=new Connect(); 
         c.connect();
         Statement stmt=c.connect().createStatement();
         String query="insert into grades (Id_Std,ExamName,CourseName,grade_std,grade_exam) values( '"+Id+"','"+ExamName+"','"+CourseName+"','"+grade_std+"','"+grade_exam+"') ";
         int row=stmt.executeUpdate(query);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    public static void CalcGrade(ArrayList<String> AnswerStudent,String Id,String ExamName,String CourseName){
        ArrayList<String>Corrected=new ArrayList();
        int grade_std=0;
        Corrected=ReadCorrectedAnswer(ExamName,CourseName);
        if(Corrected.size()>0){
            for(int x=0;x<Corrected.size();x++){
                if(Corrected.get(x).equals(AnswerStudent.get(x)))
                {grade_std++;}
            }
        String grade_S=String.valueOf(grade_std);
        String grade_exam=String.valueOf(Corrected.size());
        StoringGrade(Id, ExamName, CourseName,grade_S,grade_exam);
        }
    }
}
