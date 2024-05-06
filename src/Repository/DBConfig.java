package Repository;
import java.sql.*;
public class DBConfig {
	protected Connection conn=null;
	protected PreparedStatement pmt=null;
	protected ResultSet rs=null;
	public DBConfig()
	{
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeproject","root","root");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeproject","root","root");
		}
		catch(Exception ex)
		{
		System.out.println("Errot is-->"+ex);	
		}
	}
}


//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class DBConfig
//{
//	public static void main(String x[])
//	{
//		try
//		{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306:/shubham","root","root");
//			if(conn!=null)
//			{
//			System.out.println("Databse Connected");	
//			}
//			else
//			{
//				System.out.println("not");
//			}
//		}
//		catch(Exception ex)
//		{
//			System.out.println("Error is:"+ex);
//		}
//	}
//}