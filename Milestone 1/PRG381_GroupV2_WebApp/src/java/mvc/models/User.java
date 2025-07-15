package mvc.models;

import database.IDatabaseOperations;
import database.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.apache.tomcat.jakartaee.commons.lang3.function.FailableBiPredicate;
import org.mindrot.jbcrypt.Auth;
public class User implements IDatabaseOperations<User>
{
    public String studentNumber;
    public String name;
    public String surname;
    public String email;
    public String phone;
    public String password;
    
    private Database database = new Database(); // need this to create new db object in this class

    // Constructor
    
    public User() {} // method overload to be able to use some methods without creating a default temp user.
    
    public User(String studentNumber, String name, String surname, String email, String phone, String password) 
    {
        this.studentNumber = studentNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    
    public User(String studentNumber, String name, String surname, String email, String phone) 
    {
        this.studentNumber = studentNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    // Validation method User user = new User(....), String erros = user.Validate() if null process to db, else there is an error;
    public String validate() 
    {
        if (studentNumber == null || !studentNumber.matches("^[A-Za-z0-9]+$")) {
            return "Invalid student number: must be alphanumeric.";
        }
        if (name == null || !name.matches("^[A-Za-z ]+$")) {
            return "Invalid name: only letters and spaces allowed.";
        }
        if (surname == null || !surname.matches("^[A-Za-z ]+$")) {
            return "Invalid surname: only letters and spaces allowed.";
        }
        if (email == null || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Invalid email format.";
        }
        if (phone != null && !phone.matches("^[0-9\\-\\+\\(\\) ]{7,20}$")) {
            return "Invalid phone number.";
        }
        if (password == null || password.length() < 8) {
            return "Password must be at least 8 characters long.";
        }
        return null; // no errors
    }

    
    
    
    /*Database related methods*/

    @Override
    public boolean register(User user) 
    {
        String sql = "INSERT INTO users (student_number, name, surname, email, phone, password) VALUES(?,?,?,?,?,?)";
        try(Connection connection = database.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, user.studentNumber);
            stmt.setString(2, user.name);
            stmt.setString(3, user.surname);
            stmt.setString(4, user.email);
            stmt.setString(5, user.phone);
            
            //Bcrpyt password
            stmt.setString(6, user.password);
            
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0)
            {
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User login(String email, String password) {
        
        String sql = "SELECT * FROM users WHERE email = ?";
        try(Connection connection = database.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, email);            
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                String studentNumber = rs.getString("student_number");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String Email = rs.getString("email");
                String phone = rs.getString("phone");
                
                String hashedPassword = rs.getString("password");
                
                if(Auth.verifyPassword(password, hashedPassword))
                {
                    User user = new User(studentNumber,name,surname,email,phone);
                    return user;
                }
                else{
                    return null;
                }

            }
            else{
                return null;
            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    
    //if method runs successfully then, we have connected the db and api
    @Override
    public List<User> getAllUsers() 
    {
        List<User> users = new ArrayList<>();
        
        String sql = "SELECT * FROM users";
        try(Connection connection = database.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                    String studentNumber = rs.getString("student_number");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    
                    User user = new User(studentNumber,name,surname,email,phone);
                    users.add(user);
            }
            
            return users;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

