package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private final Connection connection;
    public Patient(Connection connection){
        this.connection = connection;
    }

    public void addPatient(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter Patient Name: ");
        String name = scanner1.nextLine();
        System.out.print("Enter Patient Gender: ");
        String gender = scanner1.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = scanner1.nextInt();

        try{
            String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient Added Successfully!!");
            }else{
                System.out.println("Failed to add Patient!!");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void viewPatients(){
        String query = "select * from patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+--------------------+----------+------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     |");
            System.out.println("+------------+--------------------+----------+------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, name, age, gender);
                System.out.println("+------------+--------------------+----------+------------+");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void deletePatient(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id for Delete Patient: ");
        int id = scanner.nextInt();
        try{
            String query = "DELETE FROM patients WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Patient Deleted Succesfully!");
            } else {
                System.out.println("Patient not Deleted");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            } else{
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

}