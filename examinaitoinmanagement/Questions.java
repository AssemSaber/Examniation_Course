/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Questions {
     public void addQuestion(String nameExam,String CourseId,String duratation,String Ques,String a,String b,String c,String d){
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
    }
     public static void DeleteNQuestions(int [] NQuestions,String ExamName,String CourseName){
        int positionExamN=0;
        int positinoCourseN=1;
        String tempfile="E:\\Database\\Temp.txt";
        File oldFile=new File("E:\\Database\\Exams.txt");
        File newFile=new File(tempfile);
        String currentLine;
        String data[];
        int counter=1,next=0;
        try{
            FileWriter fw=new FileWriter(tempfile,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);
            FileReader fr=new FileReader("E:\\Database\\Exams.txt");
            BufferedReader br=new BufferedReader(fr);
            
            while((currentLine=br.readLine())!=null){
                data=currentLine.split(",");
                if(!(data[positionExamN].equalsIgnoreCase(ExamName)&& data[positinoCourseN].equalsIgnoreCase(CourseName)))
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
    /* public static void spilt(int [] NQuestions,String ExamName,String CourseName){
         try (FileReader f=new FileReader("E:\\Database\\Exams.txt");){       
            Scanner scan=new Scanner(f);
            String s=" ",data[];
            int counter=1,next=0;
             Arrays.sort(NQuestions);
            while(scan.hasNextLine()){ 
                s=scan.nextLine();
                data=s.split(",");
                if(data[0].equals(ExamName)&&data[1].equals(CourseName)){
                    
                    if(counter==NQuestions[next]){
                        counter++;
                        System.out.println("found "+s);
                       // DeleteNQuestions(true,s);
                        if(next<NQuestions.length-1)
                            next++;
                    }
                      else{
                        DeleteNQuestions(true,s);
                      }
                      
                }
                else{
                    DeleteNQuestions(true,s);
                }
                    
            }
            
        }catch (Exception ex) {
          System.out.println(ex);
        }
       
     
     }*/
}
