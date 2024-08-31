/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examinaitoinmanagement;

import java.util.Scanner;
import java.io.*;
import java.sql.*;
public class MasterMenus {
    public static void master(){
            Scanner in=new Scanner(System.in);
        String input="";
          do{
              do{
                   System.out.println("1)Admin\n2)Lecturer\n3)Student\n4)Terminate ");
                    input=in.nextLine();
              }while(input.length()>4||input.length()<=0||input.charAt(input.length()-1)>'9');
            switch(input){
                case "1":
                   MenusAdmin.Menus_admin();
                    break;
                case "2":
                    Menuslecturer.MenusLect();
                    break;
                case "3":
                    MenusStudent.Menus_Std();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Enter a vaild input");
            }
        }while(Integer.parseInt(input)!=4 || Integer.parseInt(input)<=0);
    
    }  
}
