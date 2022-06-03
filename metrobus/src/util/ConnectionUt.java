package util;
import java.sql.*;
public class ConnectionUt {

	
	Connection conn = null;
	public static Connection conDB() {
    	
    	try {
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/metrobus", "root", "mysql");
    		return con;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
    }
}
