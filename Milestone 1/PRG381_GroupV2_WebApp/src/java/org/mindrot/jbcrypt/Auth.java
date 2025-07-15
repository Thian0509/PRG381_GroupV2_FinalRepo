package org.mindrot.jbcrypt;
import org.mindrot.jbcrypt.BCrypt;

public class Auth 
{
    public Auth () {}
    public static String hashPassword(String plainPassword) 
    {

        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    public static boolean verifyPassword(String plainPassword, String hashed) 
    {
        return BCrypt.checkpw(plainPassword, hashed);
    }
}