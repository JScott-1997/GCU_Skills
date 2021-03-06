/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author jackb
 */
public class UserManager {

    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private final String connectionString = "jdbc:ucanaccess://C:\\Users\\jackb\\IdeaProjects\\GCU_Skills\\data\\GCU_SkillsDB.accdb";

    PassHash passHash = new PassHash();

    public int registerStudent(Student student) {

        int studentId = 0;

        try {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO Students(EmailAddress, Password, FirstName, LastName, DateOfBirth, PhoneNumber) " + "VALUES('" + student.getEmail() + "', '" + student.getPassword() + "', '" + student.getFirstName() + "', '" + student.getLastName() + "', '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(student.getDob()) + "', '" + student.getPhoneNumber() + "')");

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                studentId = rs.getInt(1);

            }

            conn.close();
            return studentId;

        } catch (ClassNotFoundException | SQLException ex) {

            String message = ex.getMessage();
            return 0;

        }

    }

    public Student logInStudent(String emailAddress, String password) throws SQLException, ClassNotFoundException {

        int studentId = 0;
        String realPassword = "";
        String firstName = "";
        String lastName = "";
        Date dob = new Date();
        String phoneNumber = "";

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();

        //Selects every matching entry in the customers table in the database
        ResultSet rs = stmt.executeQuery("SELECT * FROM Students WHERE EmailAddress= '" + emailAddress + "'");

        //Executes for every entry ing the results set
        while (rs.next())
        {

            //Gets the values from the result set entry 
            studentId = rs.getInt("studentId");
            realPassword = rs.getString("Password");
            firstName = rs.getString("FirstName");
            lastName = rs.getString("LastName");
            dob = rs.getDate("DateOfBirth");
            phoneNumber = rs.getString("PhoneNumber");


        }

        conn.close();

        //Compares encrypted password in database to plaintext password for equality using bCrypt and returns student if valid
        boolean isPassValid = passHash.checkPassword(emailAddress, password);

        if (isPassValid)
        {

            Student student = new Student(emailAddress, password, firstName, lastName, dob, phoneNumber);
            student.setStudentId(studentId);
            return student;

        }

        else
        {
            return null;
        }

    }

    public Student updateAttribute(String parameterName, String parameter, Student student) throws SQLException, ClassNotFoundException {

        switch (parameterName)
        {

            case "emailaddress":

                student.setEmail(parameter);
                break;

            case "phonenumber":

                student.setPhoneNumber(parameter);
                break;

            case "password":

                student.setPassword(parameter);
                parameter = passHash.hashPassword(parameter);
                break;

        }

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE Students SET " + parameterName + " = " + "\"" + parameter + "\" " + "WHERE StudentId= " + student.getStudentId());
        conn.close();

        return student;

    }

    public HashMap<Integer, Student> loadAllStudents(){

        //Integer as key is the students id
        HashMap<Integer, Student> studentsHashMap = new HashMap();

        try
        {

           Class.forName(driver);
           Connection conn = DriverManager.getConnection(connectionString);
           Statement stmt = conn.createStatement();

           //Selects every entry in the students table in the database
           ResultSet rs= stmt.executeQuery("SELECT * FROM Students;");

           //Executes for every entry in the results set
           while(rs.next())
           {

               //Gets the values from the result set entry and creates a student object using them
               int studentId = rs.getInt("id");
               String email = rs.getString("EmailAddress");
               String password = rs.getString("Password");
               String firstName = rs.getString("FirstName");
               String lastName = rs.getString("LastName");
               Date dob = rs.getDate("DateOfBirth");
               String phoneNumber = rs.getString("PhoneNumber");
               int courseId = rs.getInt(1);

               Course studentCourse = new Course(courseId);

               Student students = new Student(email, password, firstName, lastName, dob, phoneNumber);

               //Puts the student object into the hashmap
               studentsHashMap.put(studentId, students);

           }

           conn.close();

        }

        catch(ClassNotFoundException | SQLException ex)
        {

            String message = ex.getMessage();

        }

        finally
        {

            //Loads the students course and that courses lessons into the studentsHashMap and returns it
            CourseManager cm = new CourseManager();
            studentsHashMap = cm.loadStudentCourse(studentsHashMap);
            studentsHashMap = cm.loadCourseLessons(studentsHashMap);
            return studentsHashMap;

        }
}

}

