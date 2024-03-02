package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Doctor {
    private final Connection connection;
    public Doctor(Connection connection){
        this.connection = connection;
    }

    public void addDoctor(){
        Scanner scanner1 = new Scanner(System.in);
        try{
            System.out.print("Enter Doctor Name: ");
            String name = scanner1.nextLine();
            System.out.print("Enter Doctor Specialization: ");
            String Specialization = scanner1.nextLine();
            String query = "INSERT INTO doctors(name, specialization) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, Specialization);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Doctor Added Successfully!!");
            }else{
                System.out.println("Failed to add Doctor!!");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void viewDoctors(){
        String query = "select * from doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+--------------------+------------------+");
            System.out.println("| Doctor Id  | Name               | Specialization   |");
            System.out.println("+------------+--------------------+------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-18s | %-16s |\n", id, name, specialization);
                System.out.println("+------------+--------------------+------------------+");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteDoctor(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print("Enter id for Delete Doctor: ");
            int id = scanner.nextInt();
            String query = "DELETE FROM doctors WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Doctor Deleted Succesfully!");
            } else {
                System.out.println("Doctor not Deleted");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } catch(InputMismatchException e){
            System.out.println("Input Mismatch Exception: For Input Int!");
        }
    }
    public boolean getDoctorById(int id){
        String query = "SELECT * FROM doctors WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
