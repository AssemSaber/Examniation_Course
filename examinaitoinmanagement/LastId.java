/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LastId {
public static int Id_Student;   
     public void ReadLastId(){
      
      try(FileReader f=new FileReader("E:\\Database\\Last_Id.txt");)
        {
             try(BufferedReader f1=new BufferedReader(f);){
                                     
                  Id_Student=Integer.parseInt(f1.readLine());
        
       }
         catch(Exception e){
           System.out.print(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }   
  }
  public void WriteLastId(int Id){
      try(FileWriter f=new FileWriter("E:\\Database\\Last_Id.txt");)
        {
             try(PrintWriter  f1=new PrintWriter (f);){
              f1.print(Id);
        
       }
         catch(Exception e){
           System.out.print(e);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
  }
  public int getId(){
      ReadLastId();
      return Id_Student;
  }
  public void increment(){
      Id_Student++;
      WriteLastId(Id_Student);
  }
  
}
