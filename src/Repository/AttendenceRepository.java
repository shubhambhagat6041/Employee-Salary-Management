package Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendenceRepository extends DBConfig{
//------------------ getting incrementel date id for calculate attendense-------------------------------------------------------------
	private int dateId;
	private int dateId()
	{
		try
		{
			pmt=conn.prepareStatement("select max(did)from date");
			rs=pmt.executeQuery();
			if(rs.next())
			{
				dateId=rs.getInt(1);
			}
			++dateId;
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
			return 0;
		}
		return dateId;
	}
	//==============TAKE ATTENDENSE OF EMPLOYEE==============================================================================================
	private int id=0;
	public boolean isVerifylogin(String user,String pass)
	{
		try {
			pmt=conn.prepareStatement("select emp_id from employee where username=? and password=? ");
			pmt.setString(1, user);
			pmt.setString(2, pass);
			rs=pmt.executeQuery();
			
			if(rs.next())
			{
				int eid=rs.getInt(1);
					id=eid;		
			}
			return true;
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return false;
		}
	}
	private int atid=1;
	int did=0;
	
	public boolean isAttendence()
	{
		try {
			if(id!=0)
			{		did=this.dateId();	
				
				pmt=conn.prepareStatement("insert into date values(?,?)");
				pmt.setInt(1, did);
				Date date=new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String strdate=formatter.format(date);
				pmt.setString(2, strdate);
				int value=pmt.executeUpdate();
				if(value>0)
				{
					pmt=conn.prepareStatement("insert into empattendatejoin values(?,?,?)");
					pmt.setInt(1,id);
					pmt.setInt(2, atid);
					pmt.setInt(3, did);
					value=pmt.executeUpdate();
					if(value>0)
					{
						return true;
					}
					else {
						System.out.println("attendense not join ");
						return false;
					}
				}
				else
				{
					System.out.println("Date not found");
					return false;
				}
			}	
			else {
				System.out.println("Employee not found");
				return false;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
			return false;
		}
	}
	
	//==========================count attendense of all month=====================================================================
	public void calAttMonthwise(String user)
	{
		try {
			int eid=this.getempid(user);
			if(eid!=0)
			{
				pmt=conn.prepareStatement("SELECT e.emp_fname AS 'Employee Name',COUNT(d.pdate) AS 'Count of Present',MONTH(d.pdate) AS 'Month'FROM employee e INNER JOIN empattendatejoin empj ON empj.emp_id = e.emp_id INNER JOIN date d ON d.did = empj.did WHERE e.emp_id = ? GROUP BY e.emp_fname, MONTH(d.pdate)");
				pmt.setInt(1, eid);
				rs=pmt.executeQuery();
				while(rs.next())
				{
					String name=rs.getString(1);
					int presentcount=rs.getInt(2);
					int month=rs.getInt(3);
					System.out.println("Employee Name \t count \t month");
					System.out.println(name+"\t\t"+presentcount+"\t"+month);
				}
				
			}
			else
			{
				System.out.println("Some problem is there");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
		}			
	}
//==============================Count of Attendense for specific month======================================================
	int eid=0;
	public int getempid(String user)
	{		
		try {			
			pmt=conn.prepareStatement("select emp_id from employee where username=?");
			pmt.setString(1, user);
			rs=pmt.executeQuery();			
			if(rs.next())
			{
				eid=rs.getInt(1);						
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return 0;
		}
		return eid;
	}
	
	public int calAttendense(String user,int month)
	{
		int presentcount=0;
		try {
			int eid=this.getempid(user);
			if(eid!=0)
			{
				pmt=conn.prepareStatement("SELECT e.emp_fname,COUNT(d.pdate) FROM employee e INNER JOIN empattendatejoin empj ON empj.emp_id = e.emp_id INNER JOIN date d ON d.did = empj.did WHERE e.emp_id = ? AND MONTH(d.pdate) = ? GROUP BY e.emp_fname");
				pmt.setInt(1, eid);
				pmt.setInt(2, month);
				rs=pmt.executeQuery();
				if(rs.next())
				{
				//String name=rs.getString(1);
					presentcount=rs.getInt(2);
				//	System.out.println(name+"\t"+presentcount);	
					
				}
			//	return 1;
				return presentcount;
			}
			else
			{
				return -1;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return 0;
		}		
	}
}
