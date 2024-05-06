package Repository;

import java.util.ArrayList;
import java.util.List;

import Model.DepartmentModel;

public class DepartmentRepository extends DBConfig{
	List <String> list=new ArrayList <String>();
	//----------------------get max depart id for insert new department on next id-----------------------------------------------------
	private int id;
	public int getId()
	{
		try {
			pmt=conn.prepareStatement("select max(dept_id) from department");
			rs=pmt.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			return ++id;
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return 0;
		}
	}
// ---------------------here check alredy department present or not-----------------	
	 public boolean isDepartmentPresent(String name)
	 {
		 try {
			 pmt=conn.prepareStatement("select *from department where dept_name=?");
			pmt.setString(1, name);
			rs=pmt.executeQuery();
			return rs.next();
		 }
		 catch(Exception ex)
		 {
			 System.out.println("Error is-->"+ex);
			 return false;
		 }
	 }
//	-----------add new department-------------------------------------------------
	public boolean addDepartment(DepartmentModel dm)
	{
		int did=this.getId();
		if(did!=0)
		{
		try {
			pmt=conn.prepareStatement("insert into department values(?,?)");
			pmt.setInt(1, did);
			pmt.setString(2, dm.getName());
			int value=pmt.executeUpdate();
			if(value>0)
			{
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is -->"+ex);
			return false;
		}		
	}
		return false;
	}

//---------------------------------------------------------------------------------------


public List <String> getAllDepartment()
{
	try {
		pmt=conn.prepareStatement("select dept_name from department");
		rs=pmt.executeQuery();
		while(rs.next())
		{
			list.add(rs.getString(1));
		}
		return list.size()>0?list:null;
	}
	catch(Exception ex)
	{
		System.out.println("Error is--->"+ex);
		return null;
	}
}

}








