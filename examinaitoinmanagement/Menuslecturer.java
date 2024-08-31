/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.util.ArrayList;
import java.util.Scanner;
public class Menuslecturer {
     public static void ShowModel(ArrayList<String> ShowEx){
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
   }
    public static void take_N_Questions(String nameExam,String CourseName,String OldOrNew){
      int No_Question=-1;String Question="",duratation="";
      Questions ques=new Questions();
      Lecturers lect=new Lecturers();
      Scanner in=new Scanner(System.in);
      do{
       System.out.println("Enter number of questions");
       No_Question=in.nextInt();
       }while(No_Question<=0);
      String actualAnswer[]=new String [No_Question];
      in.nextLine();
      if(OldOrNew=="New")
        do{
               System.out.println("Enter duratation");
               duratation=in.nextLine();
         }while(Integer.parseInt(duratation)<=0);  
      else
          do{
               System.out.println("Enter a duratation");
               duratation=in.nextLine();
         }while(Integer.parseInt(duratation)<=0);  
      for(int x=0;x<No_Question;x++)
      {  String a="",b="",c="",d="";
                                       
      System.out.println("Enter question "+(x+1)+" :");            
      Question=in.nextLine();
       System.out.println("Enter answer this question ");
      do{
           System.out.println("a)");
            a=in.nextLine();
            }while(a.equals(" ")|| a.equals("\n"));
      do{
            System.out.println("b)");
             b=in.nextLine();
              }while(b.equals(" ")|| b.equals("\n"));
        do{
             System.out.println("c)");
             c=in.nextLine();
             }while(c.equals(" ")|| c.equals("\n"));
              do{
             System.out.println("d)");
             d=in.nextLine();
             }while(d.equals(" ")|| d.equals("\n"));
             do{
             System.out.print("What is the actual answer a or b or c or d : ");
             actualAnswer[x]=in.nextLine();
             actualAnswer[x]=actualAnswer[x].toLowerCase();
             }while(!actualAnswer[x].equals("a")&&!actualAnswer[x].equals("b")&&!actualAnswer[x].equals("c")&&!actualAnswer[x].equals("d"));
             ques.addQuestion(nameExam, CourseName, duratation, Question,a,b,c,d);   
             
    }
      lect.addExam(nameExam, CourseName,actualAnswer);
             System.out.println("Added successfully");
  }
    public static void MenusLect(){
        Scanner in=new Scanner(System.in);
        Files f=new Files();
        Lecturers lect=new Lecturers();
        ArrayList<String>DoctorCourses=new ArrayList();
        ArrayList<String>DoctorExams=new ArrayList();
        Information info=new Lecturers();
        Courses course=new Courses();
        String answer0="",answer1="",answer5="",Ssn="",Pass="",CourseName="";String Choose_Exam="";
         String nameExam="",duratation=" ",Question=" ";int No_Question=-1;
         Questions ques=new Questions();
        String  Choose_Course="";
        outer:do{
                do{
                     System.out.println("Enter your Ssn ,remember at least 10 numbers or -1 to Exit");
                     Ssn=in.nextLine();
                     if(Ssn.equals("-1"))
                        break outer;
                }while(Ssn.charAt(Ssn.length()-1)>'9'||Ssn.length()<10||Ssn.length()<0);
                System.out.println("Enter your Password");
                Pass=in.nextLine();
                 if(info.Login(Ssn, Pass)){
                     DoctorCourses=course.Courses_Doctor(Ssn);
                     for(int x=0;x<DoctorCourses.size();x++){
                         System.out.println((x+1)+") "+DoctorCourses.get(x));
                     }
                        if(DoctorCourses.size()>0){
                            do{
                                System.out.println("Enter the number of the course what you want");
                                Choose_Course=in.nextLine();
                            }while(Choose_Course.charAt(Choose_Course.length()-1)>'9'||Integer.parseInt(Choose_Course)>DoctorCourses.size()||Integer.parseInt(Choose_Course)<=0);
                            CourseName=DoctorCourses.get(Integer.parseInt(Choose_Course)-1);
                            //in.nextLine();
                            inside:do{
                            System.out.println("1)Add an Exam\n2)Delete an Exam\n3)Edit Exam\n4)List of Exam\n5)Report For Students for a specific exam\n6)Settings\n7)Exit");
                            answer1=in.nextLine();
                            switch(answer1){
                                case "1":
                                    boolean check=true;
                                   

                                    System.out.println("Enter  name of exam");
                                     while(check){  
                                     nameExam=in.nextLine(); 
                                     if(f.CheckExam(nameExam,CourseName))
                                         System.out.println("Enter anther name");
                                     else 
                                         break;
                                     }
                                     take_N_Questions(nameExam,CourseName,"New");
                                    break;
                                case "2":
                                    ArrayList<Integer>indeces1=new ArrayList();
                                    DoctorExams=lect.listExams(CourseName);
                                    int counter=1;
                                    if(DoctorExams.size()>0){
                                         String last3=DoctorExams.get(0);
                                         System.out.println("1)"+last3);
                                         indeces1.add(0);
                                        for(int x=0;x<DoctorExams.size();x++){
                                            if(!last3.equals(DoctorExams.get(x))){
                                                 counter++;
                                                System.out.println(counter+") "+DoctorExams.get(x));
                                                last3=DoctorExams.get(x);
                                                indeces1.add(x);
                                            }
                                        }
                                        //
                                        do{
                                            System.out.println("Enter the number of Exam");
                                            Choose_Exam=in.nextLine();
                                        }while(Integer.parseInt(Choose_Exam)>counter||Integer.parseInt(Choose_Exam)<=0);
                                        lect.deleteExam(DoctorExams.get(indeces1.get(Integer.parseInt(Choose_Exam)-1)), CourseName);
                                        System.out.println("Deleted successfully");
                                        in.nextLine();
                                    }
                                        else
                                        System.out.println("Doctor! you don't make any exam");
                                    //in.nextLine();
                                    break;
                                case "3":
                                    ArrayList<Integer>indeces2=new ArrayList();
                                    DoctorExams=lect.listExams(CourseName);
                                    String action="";
                                    int counter4=1;
                                    if(DoctorExams.size()>0){
                                         String last3=DoctorExams.get(0);
                                         System.out.println("1)"+last3);
                                         indeces2.add(0);
                                        for(int x=0;x<DoctorExams.size();x++){
                                            if(!last3.equals(DoctorExams.get(x))){
                                                 counter4++;
                                                System.out.println(counter4+") "+DoctorExams.get(x));
                                                last3=DoctorExams.get(x);
                                                indeces2.add(x);
                                            }
                                        }
                                        
                                        //
                                        do{
                                            System.out.println("Enter the number of Exam");
                                            Choose_Exam=in.nextLine();
                                        }while(Choose_Exam.charAt(Choose_Exam.length()-1)>'9'||Integer.parseInt(Choose_Exam)>counter4||Integer.parseInt(Choose_Exam)<=0);
                                    //
                                    //  in.nextLine();
                                       do{
                                        System.out.println("1)Add number of qustion\n2)Delete a Question\n3)Back");
                                        action=in.nextLine();
                                         switch(action){
                                            case "1":
                                               take_N_Questions(DoctorExams.get(indeces2.get(Integer.parseInt(Choose_Exam)-1)),CourseName,"old");
                                               break;
                                            case "2":
                                                int []N_Question_For_Deleting;
                                                int number=1;
                                                ///*******
                                                         ArrayList<String> ShowEx=new ArrayList();// it starts with duration then all questions
                                                     ArrayList<String> AnswersStudent=new ArrayList();
                                                     ArrayList<String> CorrectedExam=new ArrayList();
                                                     int temp=6,Iterator_question_answer=1;String input="";char an=' ';
                                                     System.out.println("============================");
                                                     ShowEx=f.ShowExam2(DoctorExams.get(indeces2.get(Integer.parseInt(Choose_Exam)-1)), CourseName);
                                                     ShowModel(ShowEx);
                                                      System.out.println("============================");
                                                     
                                                      do{
                                                       System.out.println("How many Questions  will be deleted?"+"at most "+ShowEx.size()/6);
                                                      number=in.nextInt();    
                                                      }while(number<=0 ||number>ShowEx.size()/6);
                                                      N_Question_For_Deleting=new int[number];
                                                      for(int x=0;x<number;){
                                                          int num=-1;
                                                          System.out.print("Number: ");
                                                          num=in.nextInt();
                                                          if(num<=ShowEx.size()){
                                                             N_Question_For_Deleting[x]=num; 
                                                             x++;  
                                                          }
                                                          else
                                                              System.out.println("Enter a vaild number");
                                                      }
                                                      Questions.DeleteNQuestions(N_Question_For_Deleting, DoctorExams.get(indeces2.get(Integer.parseInt(Choose_Exam)-1)), CourseName);
                                                      //*********   
                                                     in.nextLine(); 
                                            break;
                                            case "3":
                                              break;
                                            default:
                                                System.out.println("Enter a vaild input");
                                            
                                          }
                                       }while(Integer.parseInt(action)!=3||Integer.parseInt(action)<=0);
                                     
                                  } 
                                  else
                                      System.out.println("Doctor!you don't have exams");
                                    break;
                                case "4":
                                    int counter2=2;
                                     DoctorExams=lect.listExams(CourseName);
                                    if(DoctorExams.size()>0){
                                        System.out.println("These are all exams ");
                                        String last=DoctorExams.get(0);
                                        System.out.println("1)"+last);
                                        for(int x=0;x<DoctorExams.size();x++){
                                            if(!last.equals(DoctorExams.get(x))){
                                                System.out.println(counter2+")"+DoctorExams.get(x));
                                                last=DoctorExams.get(x);
                                                counter2++;
                                            }
                                            
                                        }
                                    }
                                        else
                                        System.out.println("Doctor! you don't make any exam");
                                    break;
                                case "5":
                                    
                                    DoctorExams=lect.listExams(CourseName);
                                    ArrayList<String>students=new ArrayList();
                                    ArrayList<Integer>indeces=new ArrayList();
                                    int counter3=1;
                                    if(DoctorExams.size()>0){
                                        String last1=DoctorExams.get(0);
                                        System.out.println("1)"+last1);
                                        indeces.add(0);
                                        for(int x=1;x<DoctorExams.size();x++){
                                            if(!last1.equals(DoctorExams.get(x))){
                                                counter3++;
                                                System.out.println(counter3+")"+DoctorExams.get(x));
                                                last1=DoctorExams.get(x);
                                                indeces.add(x);
                                            }
                                        }
                                        do{
                                            System.out.println("Enter the number of Exam");
                                            Choose_Exam=in.nextLine();
                                        }while(Choose_Exam.charAt(Choose_Exam.length()-1)>'9'||Integer.parseInt(Choose_Exam)>counter3||Integer.parseInt(Choose_Exam)<=0);
                                        students=lect.report(DoctorExams.get(indeces.get(Integer.parseInt(Choose_Exam)-1)), CourseName);
                                        System.out.println("============================");
                                        if(students.size()>0){
                                            counter=0;
                                            System.out.println("   Id\t"+"total exam from "+students.get(2));    
                                            for(int x=0;x<students.size()/3;x++){
                                                      System.out.print((x+1)+") "+students.get(counter)+"\t");
                                                      counter++;
                                                      System.out.println(students.get(counter));
                                                      counter+=2;
                                                }
                                            System.out.println("============================");
                                        }
                                        else{
                                            System.out.println("No Student has examed for this exam");
                                        }
                                        
                                    }
                                    else
                                        System.out.println("Doctor!you don't make any Exam");
                                    break;
                                case "6":
                                   
                                   
                                        String Fname="",Lname="",Phone="",Address="",Password="",passwordAgain="";
                                         do{
                                               System.out.println("1)Edit first name\n2)Edit last name\n3)Edit Phone\n4)Edit Address\n5)Edit Password\n6)Back");
                                               answer5=in.nextLine();
                                               switch(answer5){
                                           case"1":
                                                    do{
                                                        System.out.println("Enter a new First name at least 4 letters");
                                                        Fname=in.nextLine();
                                                    }while(Fname.length()<4);
                                                    Information.function(info,"FName",Ssn, Fname);//info points lecturer [polymorphism]
                                                   System.out.println("Edited successfully");
                                               break;
                                            case "2":
                                                    do{
                                                        System.out.println("Enter a new last name at least 4 letters");
                                                        Lname=in.nextLine();
                                                    }while(Lname.length()<4);
                                                    Information.function(info,"LName",Ssn, Lname);//info points lecturer [polymorphism]
                                                    System.out.println("Edited successfully");
                                                break;
                                            case "3":
                                                    do{
                                                        System.out.println("Enter a new phone");
                                                        Phone=in.nextLine();
                                                    }while(Phone.length()!=11);
                                                    Information.function(info,"Phone",Ssn, Phone);//info points lecturer [polymorphism]
                                                   System.out.println("Edited successfully");
                                                 break;
                                            case "4":
                                                    do{
                                                        System.out.println("What is a new your Address");
                                                        Address=in.nextLine();
                                                    }while(Address.length()<4);
                                                   Information.function(info,"Address",Ssn, Address);//info points lecturer [polymorphism]
                                                   System.out.println("Edited successfully");
                                                    break;
                                               case "5":
                                                    do{
                                                        System.out.println("Enter a new password at least 6 letters");
                                                        Password=in.nextLine();
                                                         System.out.println("Enter  password again");
                                                        passwordAgain=in.nextLine();
                                                      }while(Password.length()<6 || !Password.equals(passwordAgain) );
                                                      Information.function(info,"Pass",Ssn, Password);//info points lecturer [polymorphism]
                                                      System.out.println("Edited successfully");
                                                    break;
                                                case "6":
                                                    break;
                                                default:
                                                    System.out.println("Enter a vaild number");
                                            }
                                         }while(Integer.parseInt(answer5)!=6 ||Integer.parseInt(answer5)<=0 );
                                    break;
                                case "7":
                                    break inside;
                                default:
                                    System.out.println("Enter a vaild input");
                              }
                            //  in.nextLine(); 
                             }while(!answer1.equals("7"));
                      }
                        else
                            System.out.println("Doctor!you don't have any course ");
                
               }
                 else 
                     System.out.println("your ssn or password is invaild");

            }while(true);


    }
}
