package mvc.database;
import java.sql.*;

public class Database 
{
    private static final String Postgresql_JDBC_Driver_Name = "org.postgresql.Driver";
    private static final String JDBC_Postgresql_Host = "jdbc:postgresql://localhost:5432/";  //each one must use own network config to pgadmin4
    
    private static final String DB_Name = "BCStudents";
    
    private static final String Username = "postgres"; // each one must use their own username pgadmin4 username
    private static final String Password = "admin"; // each one must use own pgadmin4 password
    
    public Database() {}
    
    
    //This method checks if there is a .jar file in the Libraries folder which is the driver for jdbc, returns true if successful
    private boolean isDriverConfigured() throws SQLException
    {
        try
        {
            Class.forName(Postgresql_JDBC_Driver_Name);
            return true;
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Cant find driver, check if you have added .jar file path in project Libraries: See Output ");
            e.printStackTrace();
            return false;
        }
    }
    
    //This method tests if we can successfully connect with the given credentials, if true returns use a Connection object to connect to the db.
    public Connection getConnection()throws SQLException
    {
        if(!isDriverConfigured()) return null;
        
        try
        {
            Connection connection = DriverManager.getConnection(JDBC_Postgresql_Host + DB_Name, Username, Password);
            if(connection != null)
            {
                return connection;
            }
            else
            {
                System.out.println("Failed to connection.");
                return null;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error: Connection failed! Check output console.");
            e.printStackTrace();
            return null;
            
        }
    }
}
