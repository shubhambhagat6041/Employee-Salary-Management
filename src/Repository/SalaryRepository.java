package Repository;

public class SalaryRepository extends DBConfig{
	
	int eid=0;
	int salary=0;
	int perdaysal=0;
	private int presentcount=0;
	int presentdaysal=0;
	int da=0;
	int totalsal=0;
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
	public int getsal(String user)
	{		
		try {			
			pmt=conn.prepareStatement("select salary from employee where username=?");
			pmt.setString(1, user);
			rs=pmt.executeQuery();			
			if(rs.next())
			{
				salary=rs.getInt(1);						
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return 0;
		}
		return salary;
	}
	
	public int getMonthAttendense(int eid,int month)
	{
		
		try {
				pmt=conn.prepareStatement("SELECT COUNT(d.pdate) FROM employee e INNER JOIN empattendatejoin empj ON empj.emp_id = e.emp_id INNER JOIN date d ON d.did = empj.did WHERE e.emp_id = ? AND MONTH(d.pdate) = ? GROUP BY e.emp_fname");
				pmt.setInt(1, eid);
				pmt.setInt(2, month);
				rs=pmt.executeQuery();
				if(rs.next())
				{
					 presentcount=rs.getInt(1);		
					
				}
			else
			{
				return 0;
			}
		}
			
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return 0;
		}
		return presentcount;
	}
//-----------------------------------------------------------------------------------------------
	public boolean SalaryService(String user,int month)
	{
		try {
			int eid=this.getempid(user);
			int salary=this.getsal(user);
			int presentcount=getMonthAttendense(eid,month);
			perdaysal=salary/30;
			presentdaysal=perdaysal*presentcount;
			da=presentdaysal*10/100;
			totalsal=presentdaysal+da;
			System.out.println("==================================================================\n");
			System.out.println("Total Salary of Employee Including Da is:"+totalsal);
			System.out.println("\n====================================================================");
			return true;
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return false;
		}
		
	}
	
//-----------------------------------------------------------------------------------------------------
	public int PerDaysal(String user)
	{
		try
		{
			salary=this.getsal(user);
			perdaysal=salary/30;
			return perdaysal;
		}
		catch(Exception ex)
		{
			System.out.println("Error is--->"+ex);
			return 0;
		}
		
	}

}
