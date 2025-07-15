package mvc.models;

import java.util.ArrayList;
import java.util.List;
import mvc.database.*;
import java.sql.*;
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
    public void register(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User login(String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); 
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

