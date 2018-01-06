package DatabaseOperations;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {        
    static Connection con=null;
    static String db = "library";
    static String user ="sqluser";
    static String pass = "sqluserpw";
    public static Connection getConnection()
    {
        if (con != null) return con;
        // get db, user, pass from settings file
        return getConnection(db, user, pass);
    }

    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/"+db_name+"?user="+user_name+"&password="+password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("mysql server error");
        }

        return con;        
    }
} 