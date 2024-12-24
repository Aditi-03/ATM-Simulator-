package bank.management.system;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    public Conn()
    {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver"); // register the driver
            //create Connection
            c= DriverManager.getConnection("jdbc:mysql:///bankmangementsystem","root","milind135@");
            
            s= c.createStatement(); //create statement
            

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
