package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "Faizan@2006";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url, username, password);

            while(true){
                System.out.println("+------------+--------------------+------------------+");
                System.out.println("|            HOSPITAL MANAGEMENT SYSTEM              |");
                System.out.println("+------------+--------------------+------------------+");
                System.out.println("| 1. Admin                                           |");
                System.out.println("| 2. Receptionist                                    |");
                System.out.println("| 3. Exit                                            |");
                System.out.println("+------------+--------------------+------------------+");
                  System.out.print("  Enter your choice: ");
                int choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        admin();
                        break;
                    case 2:
                        receptionist();
                        break;
                    case 3:
                        System.out.println("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
                        return;
                    default:
                        System.out.println("Enter valid choice!!!");
                        break;
                }
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void admin(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String aUsername = scanner.next();
        System.out.print("Enter Password: ");
        String aPassword = scanner.next();
        if("Admin".equals(aUsername) && "1234".equals(aPassword)) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Patient patient = new Patient(connection);
                Doctor doctor = new Doctor(connection);
                Appointment appointment = new Appointment();

                while (true) {
                    System.out.println("+------------+--------------------+------------------+");
                    System.out.println("|                      ADMIN                         |");
                    System.out.println("+------------+--------------------+------------------+");
                    System.out.println("| 1. Add Patient                                     |");
                    System.out.println("| 2. View Patients                                   |");
                    System.out.println("| 3. Delete Patient                                  |");
                    System.out.println("| 4. Add Doctor                                      |");
                    System.out.println("| 5. View Doctors                                    |");
                    System.out.println("| 6. Delete Doctor                                   |");
                    System.out.println("| 7. Book Appointment                                |");
                    System.out.println("| 8. Return to login menu                            |");
                    System.out.println("+------------+--------------------+------------------+");
                    System.out.print("  Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            // Add Patient
                            patient.addPatient();
                            System.out.println();
                            break;
                        case 2:
                            // View Patient
                            patient.viewPatients();
                            System.out.println();
                            break;
                        case 3:
                            // Delete Patient
                            patient.deletePatient();
                            System.out.println();
                            break;
                        case 4:
                            // Add Doctor
                            doctor.addDoctor();
                            System.out.println();
                            break;
                        case 5:
                            // View Doctors
                            doctor.viewDoctors();
                            System.out.println();
                            break;
                        case 6:
                            // Delete Doctors
                            doctor.deleteDoctor();
                            System.out.println();
                            break;
                        case 7:
                            // Book Appointment
                            appointment.bookAppointment(patient, doctor, connection, scanner);
                            System.out.println();
                            break;
                        case 8:
                            System.out.println();
                            return;
                        default:
                            System.out.println("Enter valid choice!!!");
                            break;
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void receptionist(){
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection);
            Doctor doctor = new Doctor(connection);
            Appointment appointment = new Appointment();
            while(true){
                System.out.println("+------------+--------------------+------------------+");
                System.out.println("|                    RECEPTIONIST                    |");
                System.out.println("+------------+--------------------+------------------+");
                System.out.println("| 1. Add Patient                                     |");
                System.out.println("| 2. View Patients                                   |");
                System.out.println("| 3. Delete Patients                                 |");
                System.out.println("| 4. View Doctors                                    |");
                System.out.println("| 5. Book Appointment                                |");
                System.out.println("| 6. Return to login menu                            |");
                System.out.println("+------------+--------------------+------------------+");
                  System.out.print("  Enter your choice: ");
                int choice = scanner.nextInt();

                switch(choice){
                    case 1:
                        // Add Patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        // View Patient
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        // Delete Patient
                        patient.deletePatient();
                        System.out.println();
                        break;
                    case 4:
                        // View Doctors
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 5:
                        // Book Appointment
                        appointment.bookAppointment(patient, doctor, connection, scanner);
                        System.out.println();
                        break;
                    case 6:
                        System.out.println();
                        return;
                    default:
                        System.out.println("Enter valid choice!!!");
                        break;
                }
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}