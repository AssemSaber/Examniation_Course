/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.util.ArrayList;
import java.util.Scanner;
public class MenusAdmin {
    public static void Take_Information(String who){
         Scanner in=new Scanner(System.in);
         Admin admin=new Admin();boolean check=false;
         String Id_Course="",CourseName="",phone="",Address="",FName="",LName="",Ssn="";
         if(who=="l"){
            do{
                System.out.println("Enter his Ssn and at least 10");
                Ssn=in.nextLine(); 
            }while(Ssn.length()<10||Ssn.charAt(Ssn.length()-1)>'9');
            if(admin.CheckDoctor(Ssn))
            System.out.println("He /She is Exist");
            else 
                check=true;
         }
         else{
            
                check=true;
         }
         if(check){
             System.out.println("Enter his first name");
            FName=in.nextLine();
            System.out.println("Enter his last name");
            LName=in.nextLine();
            do{
                System.out.println("Enter his phone");
                phone=in.nextLine();
            }while(phone.length()<11||phone.charAt(phone.length()-1)>'9');
            System.out.println("Enter his Address");
            Address=in.nextLine();
             if(who=="l"){
                if(admin.AddLecturer(phone, Address, FName, LName, Ssn))
                    System.out.println("Added Successfully");
                else 
                    System.out.println("He/is is exist");
             }
             else{
             LastId object=new LastId();
             String Id=String.valueOf(object.getId());
             if(admin.AddStudent(phone, Address, FName, LName,Id))
                 System.out.println("his/her Id is "+Id);
             }
         }
         
    }
    public static String List_Courses(){
         ArrayList<String>AllCourses=new ArrayList();int Choose_Course=-1;
          Courses Course=new Courses();
         Scanner in=new Scanner(System.in);
         AllCourses= Course.getAllGeneralCourses();
         if(AllCourses.size()>0)
         {
             System.out.println("These are all courses in the system ");
             for(int x=0;x<AllCourses.size();x++){
             System.out.println((x+1)+") "+AllCourses.get(x));
             }
             do{
             System.out.println("Enter the number the course what you want");
             Choose_Course=in.nextInt();
              }while(Choose_Course>AllCourses.size() &&Choose_Course<=0);
         return AllCourses.get(Choose_Course-1);
         }
         else
             System.out.println("There are not courses");
     return "";
    }
    public static void printInfo(ArrayList<String> DataPerson,char c){
        if(DataPerson.size()>0){
             System.out.println("First Name: "+DataPerson.get(0));
             System.out.println("Last Name: "+DataPerson.get(1));
             System.out.println("Phone: "+DataPerson.get(2));
             System.out.println("Address: "+DataPerson.get(3));
             if(DataPerson.size()>4){
                if(c=='l'){
                System.out.print("He teaches these courses : ");
               }
                else
                  System.out.print("His/her Courses : ");
                for(int x=4;x<DataPerson.size();x++){
                     System.out.print(DataPerson.get(x)+" ,");
                }
             }
             else
             System.out.println("He/she doesn't have any course");
             }
             else
             System.out.println("He/She is not found");
    }
    public static void Menus_admin(){
        Scanner in=new Scanner(System.in);
        String answer2="",answer3="",answer4="",answer5="",answer6="",Choose_Course="";
        String Id_Course="",CourseName="",Id_std="", Ssn="",answer1="";
        ArrayList<String>AllCourses=new ArrayList();
        ArrayList<String>All_Courses_Unfound_In_him=new ArrayList();
        Admin admin=new Admin();
        Courses Course=new Courses();
        String User="",Pass="";
        boolean There_Courses=false;
        do{
            System.out.println("Enter your UserName or -1 to get out");
            User=in.nextLine();
            if(User.equals("-1"))
                break;
            System.out.println("Enter your password");
            Pass=in.nextLine();
        if(admin.login_Admin(User,Pass)){
            do{
                do{
                    System.out.println("1)Add\n2)Delete\n3)Update\n4)Search\n5)Settings\n6)List\n7)Exit");
                    answer1=in.nextLine();
                }while(answer1.length()<=0||answer1.length()>7||answer1.charAt(answer1.length()-1)>'9');
            switch(answer1){
                case "1":
                    do{
                        do{
                             System.out.println("1)Add Course\n2)Add lecturer\n3)Add Student\n4)Assign student to course\n5)Assign lecturer to course\n6)Back");
                             answer2=in.nextLine();
                        }while(answer2.length()>6||answer2.length()<=0||answer2.charAt(answer2.length()-1)>'9');
                        switch(answer2){
                        case "1":
                            in.nextLine();
                            do{
                                System.out.println("Etner a vaild Id Course at least 6 letters");
                                Id_Course=in.nextLine();
                                }while(Id_Course.length()<6);
                            System.out.println("Enter Course Name");
                            CourseName=in.nextLine();
                            if(admin.AddCourse(Id_Course, CourseName))
                                System.out.println("Added successfully");
                            else
                                System.out.println("It is Exist");
                            break;
                        case "2":
                            Take_Information("l");
                            break;
                        case "3":
                            Take_Information("s");
                            break;
                        case  "4":
                             int counter=1;
                             ArrayList<String>All_Courses_Std=new ArrayList();
                             
                            do{
                               System.out.println("Etner the Id student");
                               Id_std=in.nextLine();
                             }while(Id_std.length()<=6);
                            if(admin.CheckStudent(Id_std)){
                                All_Courses_Std=Course.getAllCoursesForStd(Id_std);
                                 AllCourses= Course.getAllGeneralCourses();boolean foundcourse=false;
                                 if(AllCourses.size()>0){
                                       // System.out.println("There are  all unregistered courses");
                                    for(int x=0;x<AllCourses.size();x++){
                                        boolean check=true;
                                        for(int j=0;j<All_Courses_Std.size();j++){
                                            if(AllCourses.get(x).equals(All_Courses_Std.get(j))){
                                                {check=false;break;}
                                            }

                                        }
                                         if(check){
                                                foundcourse=true;
                                                System.out.println(counter+")"+AllCourses.get(x));
                                                counter++;
                                                 All_Courses_Unfound_In_him.add(AllCourses.get(x));
                                            }
                                    }
                                    
                                    if(foundcourse){
                                        do{
                                            System.out.println("Choose the number of course for adding");
                                            Choose_Course=in.nextLine();
                                        }while(Integer.parseInt(Choose_Course)>=counter ||Integer.parseInt(Choose_Course)<=0);
                                        if(admin.AssignStudentToCourse(Id_std,All_Courses_Unfound_In_him.get(Integer.parseInt(Choose_Course)-1)))
                                            System.out.println("Added successfully");
                                    }
                                    else
                                        System.out.println("He/She registered in all courses");
                                 }
                                 else {
                                     System.out.println("There are not courses in the system");
                                 }

                            }
                            else{
                                System.out.println("This is unfound");
                            }
                            break;
                        case "5":
                            int counter2=1;boolean foundcourse=false;
                             ArrayList<String>All_Courses_Lec=new ArrayList();
                             
                             in.nextLine();
                            do{
                               System.out.println("Enter the Ssn,remember at least 10 numbers");
                               Ssn=in.nextLine();
                             }while(Ssn.length()<10);
                            if(admin.CheckDoctor(Ssn)){
                                       All_Courses_Lec=Course.Courses_Doctor(Ssn);
                                 AllCourses= Course.getAllGeneralCourses();
                                 if(AllCourses.size()>0){
                                        System.out.println("Other courses");
                                    for(int x=0;x<AllCourses.size();x++){
                                        boolean check=true;
                                        for(int j=0;j<All_Courses_Lec.size();j++){
                                            if(AllCourses.get(x).equals(All_Courses_Lec.get(j))){
                                                {check=false;break;}
                                            }

                                        }
                                         if(check){
                                                System.out.println(counter2+")"+AllCourses.get(x));
                                                counter2++;foundcourse=true;
                                                 All_Courses_Unfound_In_him.add(AllCourses.get(x));

                                            }
                                    }
                                    if(foundcourse){
                                        do{
                                            System.out.println("Choose the number of course for adding");
                                            Choose_Course=in.nextLine();
                                        }while(Integer.parseInt(Choose_Course)>=counter2 || Integer.parseInt(Choose_Course)<=0);
                                        if(admin.AssignLecturerToCourse(Ssn, All_Courses_Unfound_In_him.get(Integer.parseInt(Choose_Course)-1)))
                                            System.out.println("operation successfully");
                                        
                                    }
                                    else
                                        System.out.println("The doctor teaches in all courses");
                                 }
                                 else {
                                     System.out.println("There are not courses in the system");
                                 }
                            }
                            else{
                                System.out.println("This is not found");
                            }
                             
                            break;
                        case "6":
                            break;
                        default :
                            System.out.println("Enter a vaild input");
                        }
                      }while(Integer.parseInt(answer2)!=6);
                    break;
                case "2":
                    do{
                        do{
                            System.out.println("1)Delete a Course\n2)Delete a lecturer\n3)Delete a student\n4)Back");
                        answer3=in.nextLine();
                        }while(Integer.parseInt(answer3)>4 || Integer.parseInt(answer3)<0);
                        switch(answer3){
                            case "1":
                                CourseName=List_Courses();
                                if(CourseName.length()>0){
                                    if(admin.DeleteCourse(CourseName))//اكيد موجوده عشان لسه مختار هو 
                                     System.out.println("Deleted successfully");
                                }
                                break;
                            case "2":
                                in.nextLine();
                                do{
                                    System.out.println("Enter his/her Ssn ,remember at least 10 numbers");
                                    Ssn=in.nextLine();
                                }while(Ssn.length()<10);
                                if(admin.DeleteLecturer(Ssn))
                                    System.out.println("Deleted Succefully");
                                else 
                                    System.out.println("He is not found");
                                break;
                            case "3":
                                in.nextLine();
                                do{
                                    System.out.println("Etner the Id student");
                                    Id_std=in.nextLine();
                                }while(Id_std.length()<=6);
                                if(admin.DeleteStudent(Id_std))
                                     System.out.println("Deleted successfully");
                                else
                                    System.out.println("He is Not found");
                                break;
                            case "4":
                                break;
                            default :
                                System.out.println("Enter a vaild input");
                        }
                    }while(Integer.parseInt(answer3)!=4);
                    
                    break;
                case "3":
                    int oldCourse=-1,newCourse=-1, counter=0;
                    ArrayList<String>Courses_Doctor=new ArrayList();
                    //in.nextLine();
                    do{
                        
                         System.out.println("Enter the Ssn Doctor,remember at least 10 numbers");
                         Ssn=in.nextLine();
                       }while(Ssn.length()<10);
                     if(admin.CheckDoctor(Ssn)){
                            Courses_Doctor=Course.Courses_Doctor(Ssn);
                     AllCourses= Course.getAllGeneralCourses();
                     ArrayList<Integer>FoundForDoctor_indeces=new ArrayList();
                     ArrayList<Integer>anotherCourses_indeces=new ArrayList();
                     boolean findAtleastCourse=false;
                     if(Courses_Doctor.size()>0){
                         if(AllCourses.size()>0){  
                               int count=1;
                                System.out.println("List other courses");
                               for(int x=0;x<AllCourses.size();x++){
                                boolean found=true;
                                for(int j=0;j<Courses_Doctor.size();j++){
                                     if(AllCourses.get(x).equals(Courses_Doctor.get(j)))
                                     {found=false;break;}
                                }
                                if(found)
                                {  findAtleastCourse=true;
                                    System.out.println((count)+") "+AllCourses.get(x));counter++;
                                    anotherCourses_indeces.add(x);
                                    count++;
                                }
                             }
                              if(findAtleastCourse){
                               
                              
                             System.out.println("================================");
                             System.out.println("these all Courses for the doctor");
                             for(int x=0;x<Courses_Doctor.size();x++){
                                 System.out.println((x+1)+") "+Courses_Doctor.get(x));
                                 FoundForDoctor_indeces.add(x);
                             }
                             do{
                                 System.out.println("Enter the number of old course");
                                 oldCourse=in.nextInt();
                             }while(oldCourse>Courses_Doctor.size());
                             do{
                                 System.out.println("Enter the number of new course");
                                 newCourse=in.nextInt();
                             }while(newCourse>counter);
                             System.out.println(anotherCourses_indeces.size());
                             String New=(AllCourses.get(anotherCourses_indeces.get(newCourse-1)));
                             System.out.println(Courses_Doctor.get(FoundForDoctor_indeces.get(oldCourse-1))+"  "+ New+"  "+Ssn);
                           if(admin.UpdateCourseForLecturer(Ssn, Courses_Doctor.get(FoundForDoctor_indeces.get(oldCourse-1)),New))
                               System.out.println("updated successfully");
                            else
                                System.out.println("Not found the new course or the old course Or Ssn the doctor");

                              }
                              else
                                  System.out.println("the doctor registered in all courses");
                         }
                        else
                           System.out.println("There are not courses");
                     }
                        else 
                            System.out.println("The doctor has not any course");
                     //above
                     }
                     else{
                         System.out.println("He/she is not found");
                     }
                    break;
                case "4":
                    do{
                         do{
                              System.out.println("1)Search for Student\n2)Search for lecturer\n3)Back");
                              answer4=in.nextLine();
                         }while(answer4.charAt(answer4.length()-1)>'9'||Integer.parseInt(answer4)>3||Integer.parseInt(answer4)<=0);
                        
                         switch(answer4){
                             case "1":
                                 ArrayList<String>DataStudent=new ArrayList();
                                 //in.nextLine();
                                 do{
                                     System.out.println("Enter Id of student,remembre at least 8 numbers");
                                     Id_std=in.nextLine();
                                 }while(Id_std.length()<=6);
                                 if(admin.CheckStudent(Id_std)){
                                     DataStudent=admin.SearchStudent(Id_std);
                                     printInfo(DataStudent,'s');
                                     System.out.println();
                                 }
                                 else{
                                     System.out.println("He/She is not found");
                                 }
                                 break;
                             case "2":
                                 ArrayList<String>DataLecturer=new ArrayList();
                                 //in.nextLine();
                                  do{
                                     System.out.println("Enter Ssn of Lecturer");
                                     Ssn=in.nextLine();
                                 }while(Ssn.length()<8);
                                 if(admin.CheckDoctor(Ssn)){
                                     DataLecturer=admin.Searchlecturer(Ssn);
                                    printInfo(DataLecturer,'l');
                                 }
                                 else{
                                     System.out.println("He/She is not found");
                                 }
                                 break;
                             case "3":
                                 break;
                             default:
                                 System.out.println("Enter a vaild input");
                         }
                    }while(Integer.parseInt(answer4)!=3);
                    
                    break;
                case "5":
                    do{
                        do{
                            System.out.println("1)Edit username\n2)Edit password\n3)Exit");
                           answer5=in.nextLine();
                        }while(answer5.charAt(answer5.length()-1)>'9'||Integer.parseInt(answer5)>3);
                        
                        switch(answer5){
                            case "1":
                                System.out.println("before any thing user is "+User);
                                String User_again="";
                                in.nextLine();
                                do{    
                                    System.out.println("Enter a new username at least 7 letters");
                                    User=in.nextLine();
                                    System.out.println("Enter an username again");
                                    User_again=in.nextLine();
                                }while(!User.equals(User_again)&&User.length()<7);
                                System.out.println("after any thing user is "+User_again);
                                if(admin.EditUserName(User_again, Pass))
                                    System.out.println("Updated successfully");
                                break;
                            case "2":
                                String Pass_again="";
                                do{
                                    in.nextLine();
                                    System.out.println("Enter a new password at least 7 letters");
                                    Pass=in.nextLine();
                                    System.out.println("Enter a password again");
                                    Pass_again=in.nextLine();
                                }while(!Pass.equals(Pass_again)&&User.length()<7);
                                if(admin.EditPassWord(User, Pass_again))
                                    System.out.println("Updated successfully");
                                break;
                            case "3":
                                break;
                            default:
                                System.out.println("Enter a vaild input");
                        }
                    }while(Integer.parseInt(answer5)!=3);                    
                    break;
                case "6":
                    do{
                        int cont=0;
                        ArrayList<String>Lecturers_Ides=new ArrayList();
                        ArrayList<String>info=new ArrayList();
                        ArrayList<String>Students_Ides=new ArrayList();
                        do{
                            System.out.println("1)For Lecturers\n2)For Students\n3)Back");
                            answer6=in.nextLine();
                        }while(answer6.charAt(answer6.length()-1)>'9'||Integer.parseInt(answer6)>3 || Integer.parseInt(answer6)<=0);
                        switch(answer6){
                            case "1":
                                Lecturers_Ides=admin.List_Lecturers();
                                for(int x=0;x<Lecturers_Ides.size();x++){
                                  info=admin.Searchlecturer(Lecturers_Ides.get(x));
                                  printInfo(info,'l');
                                  System.out.println();
                                  System.out.println("============");
                                }
                                break;
                            case "2":
                                Students_Ides=admin.List_Students();
                                for(int x=0;x<Students_Ides.size();x++){
                                  info=admin.SearchStudent(Students_Ides.get(x));
                                  printInfo(info,'s');
                                  System.out.println();
                                  System.out.println("============");
                                }
                                break;
                            case "3":
                                break;
                            default:
                                System.out.println("Enter a vaild input");
                        }
                    }while(Integer.parseInt(answer6)!=3);
                    break;
                case "7":
                    //in.nextLine();
                    break;
                default :
                    System.out.println("Enter a vaild input");
            }
           }while(Integer.parseInt(answer1)!=7);
        }
        else{
            System.out.println("Username or Password is invaild");
           }
        }while(true);
        
    
    }
    
}
