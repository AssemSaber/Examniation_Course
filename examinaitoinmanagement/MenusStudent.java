/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

/**
 *
 * @author Moham
 */
import java.util.ArrayList;
import java.util.Scanner;
public class MenusStudent {
    public static void Menus_Std(){
        Scanner in=new Scanner(System.in);
        String Id,Pass,CourseName="",Exam;
        Courses course=new Courses();
        Information info=new Students();
        String answer="";
        String answer5="";
        while(true){
            System.out.println("Etner your Id to login or -1 to Log out ");
            Id=in.nextLine();
            if(Id.equals("-1"))
                break;
             System.out.println("Etner your password");
            Pass=in.nextLine();
            
            Students Std=new Students();
            if(Std.Login(Id, Pass))
            {
              while(true){
                    do{
                     System.out.println("1)Entering on your registered courses\n2)Exit");
                     answer=in.nextLine();
                    }while(answer.charAt(answer.length()-1)>'9'||Integer.parseInt(answer)>2 ||Integer.parseInt(answer)<=0);
                    if(answer.equals("1"))
                     {   
                        outer:while(true){
                    //show all Courses >>change information
                    ArrayList<String>AllCourses=new ArrayList();String index="";
                        Files f=new Files();int iterator=1;
                        AllCourses=course.getAllCoursesForStd(Id);
                        if(AllCourses.size()>0){
                            for(int x=0;x<AllCourses.size();x++){
                            System.out.println(iterator+") "+AllCourses.get(x));
                            iterator++;
                        }
                        do{
                            System.out.println("Choose the number of the course");
                            index=in.nextLine();
                        }while(index.charAt(index.length()-1)>'9'||Integer.parseInt(index)>AllCourses.size()||Integer.parseInt(index)<=0);
                       CourseName=AllCourses.get(Integer.parseInt(index)-1);
                    //show options >>getGrade ,entering exam or settings>>show all exams تكون متخرنه ف array 
                       while(true){
                         System.out.println("1)Settings\n2)Entering Exam\n3)Show grade and correctecd Exam\n4)Back");
                         answer=in.nextLine();
                         switch(answer){
                             case "1":
                                 String Fname="",Lname="",Phone="",Address="",Password="",passwordAgain="";
                                  in.nextLine();
                                  do{
                                  System.out.println("1)Edit first name\n2)Edit last name\n3)Edit Phone\n4)Edit Address\n5)Edit Password\n6)Back");
                                  answer5=in.nextLine();
                                   switch(answer5){
                                   case"1":
                                         do{
                                         System.out.println("Enter a new First name at least 4 letters");
                                         Fname=in.nextLine();
                                        }while(Fname.length()<4);
                                          Information.function(info,"FName",Id, Fname);//info points lecturer [polymorphism]
                                         System.out.println("Edited successfully");
                                         break;
                                case "2":
                                     do{
                                      System.out.println("Enter a new last name at least 4 letters");
                                        Lname=in.nextLine();
                                      }while(Lname.length()<4);
                                       Information.function(info,"LName",Id, Lname);//info points lecturer [polymorphism]
                                         System.out.println("Edited successfully");
                                         break;
                                case "3":
                                         do{
                                         System.out.println("Enter a new phone");
                                        Phone=in.nextLine();
                                        }while(Phone.length()!=11);
                                        Information.function(info,"Phone",Id, Phone);//info points lecturer [polymorphism]
                                         System.out.println("Edited successfully");
                                         break;
                                case "4":
                                     do{
                                      System.out.println("What is a new your Address");
                                      Address=in.nextLine();
                                      }while(Address.length()<4);
                                      Information.function(info,"Address",Id, Address);//info points lecturer [polymorphism]
                                     System.out.println("Edited successfully");
                                     break;
                                 case "5":
                                     do{
                                     System.out.println("Enter a new password at least 6 letters");
                                     Password=in.nextLine();
                                     System.out.println("Enter  password again");
                                      passwordAgain=in.nextLine();
                                     }while(Password.length()<6 || !Password.equals(passwordAgain) );
                                     Information.function(info,"Pass",Id, Password);//info points lecturer [polymorphism]
                                      System.out.println("Edited successfully");
                                         break;
                                  case "6":
                                       break;
                                  default:
                                     System.out.println("Enter a vaild number");
                                      }
                                  }while(Integer.parseInt(answer5)!=6 ||Integer.parseInt(answer5)<=0 );

                                 break;
                             case "2":
                                 //first exam for him

                                 ArrayList<String>Exams=new ArrayList();int ans=-1;
                                 int counter=2;
                                 Exams=f.getExams(Id,CourseName);
                                 ArrayList<Integer>indeces=new ArrayList();
                                 if(Exams.size()>0){
                                      String last=Exams.get(0);
                                      indeces.add(0);
                                     System.out.println("1)"+Exams.get(0));
                                     for(int x=1;x<Exams.size();x++)//display all his exams
                                     {  if(!last.equals(Exams.get(x))){
                                                 System.out.println(counter+")"+Exams.get(x));
                                                 last=Exams.get(x);
                                                 counter++;
                                                 indeces.add(x);
                                             }
                                     }
                                     do{
                                         System.out.println("Enter your choose");
                                         ans=in.nextInt();
                                     }while(ans>=counter||ans<=0);

                                         if(ans<=Exams.size()){
                                             if(Std.EnterFOrExam( Id,Exams.get(ans-1), CourseName)){
                                                     f.DeletingFromEnteringOnce(Id,Exams.get(indeces.get(ans-1)),CourseName,",");
                                                     ArrayList<String> ShowEx=new ArrayList();// it starts with duration then all questions
                                                     ArrayList<String> AnswersStudent=new ArrayList();
                                                     int temp=6,Iterator_question_answer=1;String input="";char an=' ';
                                                     ShowEx=f.ShowExam2(Exams.get(ans-1), CourseName);
                                                     System.out.println("The duration for the exam is "+ShowEx.get(0));
                                                     for(int x=1;x<=ShowEx.size()/6;x++){
                                                             if(Iterator_question_answer==temp){
                                                                 temp*=2;
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
                                                             in.nextLine();
                                                             while(true){
                                                                 System.out.print("Enter  a or b or c or d : ");    
                                                                  an=in.next().charAt(0);
                                                                  if(an=='a'||an=='b'||an=='c'||an=='d')
                                                                     break;
                                                             } 
                                                             String ans_std=String.valueOf(an);
                                                             AnswersStudent.add(ans_std);

                                                     }
                                                          SystemCalcGrade.CalcGrade(AnswersStudent, Id, Exams.get(ans-1), CourseName);
                                                 }
                                               break;
                                             }

                                          }
                                          else
                                              System.out.println("There is not an exam");
                                 break;
                             case "3":
                                 ArrayList<String> ExamsHaveGarades=new ArrayList();
                                 Files f2=new Files();int count=1,Choose_Exam=-1;
                                 ExamsHaveGarades=f2.ShowExamsHavaGrades(Id, CourseName);
                                 if(ExamsHaveGarades.size()>0){
                                     System.out.println("Choose Exam to display its grade");
                                     for(int x=0;x<ExamsHaveGarades.size();x+=3)
                                     {System.out.println(count+") "+ExamsHaveGarades.get(x));count++;}
                                     while(true){
                                         Choose_Exam=in.nextInt();
                                         if(Choose_Exam<=ExamsHaveGarades.size())
                                             break;
                                         System.out.println("Enter vaild input");
                                     }

                                           ArrayList<String> ShowEx=new ArrayList();// it starts with duration then all questions
                                                     ArrayList<String> AnswersStudent=new ArrayList();
                                                     ArrayList<String> CorrectedExam=new ArrayList();
                                                     int temp=6,Iterator_question_answer=1;String input="";char an=' ';
                                                     int indexOfExam=(Choose_Exam*2)+(Choose_Exam-3);
                                                     System.out.println("============================");
                                                     ShowEx=f.ShowExam2(ExamsHaveGarades.get(indexOfExam), CourseName);
                                                     Menuslecturer.ShowModel(ShowEx);
                                                     CorrectedExam=SystemCalcGrade.ReadCorrectedAnswer(ExamsHaveGarades.get(indexOfExam), CourseName);
                                                     for(int x=0;x<CorrectedExam.size();x++){
                                                         System.out.println((x+1)+") "+CorrectedExam.get(x));
                                                     }
                                                     System.out.println("============================");
                                                     //**
                                     int indexOfGrade=(Choose_Exam*2)+(Choose_Exam-2);
                                     System.out.println("You had got "+ExamsHaveGarades.get(indexOfGrade)+" from "+ExamsHaveGarades.get(indexOfGrade+1));
                                 in.nextLine();
                                 }
                                 else
                                     System.out.println("There are not grades for any exam");
                                 break;
                             case "4":
                             {break outer;}
                             default :
                                 System.out.println("\n\tHome Page\nEnter a vaild input");
                              }
                           }   
                        }
                        else{
                            System.out.println("You don't have any course ");
                            in.nextLine();break;
                         }
                        //above
                      }
                    }
                    
               else if(answer.equals("2"))
               {break;}
               else 
                   System.out.println("Enter a vaild input");
              }  
              
               //above it
            } 
            else{
                System.out.println("error! invaild your id or password");
            }  
        }
        
            
                
    }
}
