package database;
import java.util.*;

public interface IDatabaseOperations<T> //<T> means any type so T can be String,Integer or User based on implementation at class which uses it.
{
    void register(T user);
    T login (String email, String password );
    
    //for test db connection
    List<T> getAllUsers();
}
