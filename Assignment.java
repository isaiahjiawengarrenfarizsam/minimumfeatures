/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment;

import java.util.Scanner;
import java.sql.*;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class Assignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/luckyhoteldatabase", "root", "");
            statement = connect.createStatement();
            Scanner sc=new Scanner(System.in);
        
        String customerID = "";
        String password = "";
        
        //input CustomerID and password first
        System.out.print("Customer ID: ");
            String ID=sc.nextLine();
            
        System.out.print("Password: ");
            String userpassword=sc.nextLine();
            
        //check if password tally with SQL database
        String SQL = "SELECT * FROM customeruser WHERE customerID='" + ID + "' AND password='" + userpassword + "'";
        
        ResultSet rs = statement.executeQuery(SQL);
        
        if(rs.next()){
            System.out.println("Successful Login!\n");
            System.out.println("Please choose your action: ");
            System.out.println("Option 1: Booking ");
            System.out.println("Option 2: Update password");
        
            System.out.println("Option: ");
                int option=sc.nextInt();
                sc.nextLine();
        
            if(option==1){
                System.out.println("Directing to Booking System");
            }else{
                System.out.println("Directing to Update Password");
                
                System.out.print("Please input current password: ");
                    userpassword=sc.nextLine();
                    
                   String SQL2 = "SELECT * FROM customeruser WHERE customerID='" + ID + "' AND password='" + userpassword + "'";
                   rs = statement.executeQuery(SQL2);
                
                    if(rs.next()){
                   
                        System.out.print("Please input new password: ");
                            String newpassword1=sc.nextLine();
                            
                        System.out.print("Please input new password again: ");
                            String newpassword2=sc.nextLine();
                            
                        //UPDATE customeruser SET customeruser_password='newPassword' WHERE customeruser_customerID = ' customerID'
                        if(newpassword1.equals(newpassword2)){
                            String SQL3 = "UPDATE customeruser SET password = ? WHERE customerid = ?";
                       
                            preparedStatement = connect.prepareStatement(SQL3);
                            preparedStatement.setString (1, newpassword1);
                            preparedStatement.setString (2, ID);
                            preparedStatement.executeUpdate();
                            System.out.println("We have successfully reset your password!");
                            preparedStatement.close();
                        }
                        
                    }
                }
           connect.close();
        }
        else {
            System.out.println("Incorrect ID or Password!\n----");
        }
        
        
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                
            }
        }
         try {
             if (preparedStatement != null) {
                 preparedStatement.close();
             }
         } catch (SQLException e) {      
        }
    
} }
