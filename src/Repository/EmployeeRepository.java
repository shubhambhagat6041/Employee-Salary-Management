package Repository;

import java.util.*;
import Model.EmployeeModel;
import Model.Leave_empModel;
public class EmployeeRepository extends DBConfig{
		List <EmployeeModel> al= new ArrayList <EmployeeModel>();
		List <Leave_empModel> al1=new ArrayList <Leave_empModel>();
		
		
		int value=0;
//---------------------------logic for get maximum id of employee-----------------------------
	private int id;
	public int getId()
	{
		try {
			pmt=conn.prepareStatement("select max(emp_id) from employee");
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
//----------------------------------logic for take department id by using dapartment name---------------------------	
	public int getDepartmentid(String name) {
		try {

			pmt = conn.prepareStatement("select dept_id from Department where dept_name=?");
			pmt.setString(1, name);
			rs = pmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}

		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
			return 0;
		}
	}

//--------------logic for check employee already present or not----------------------------------
	 public boolean isEmployeePresent(String username,String password)
	 {
		 try {
			 pmt=conn.prepareStatement("select *from employee where username=? and password=?");
			pmt.setString(1, username);
			pmt.setString(2, password);
			rs=pmt.executeQuery();
			return rs.next();
		 }
		 catch(Exception ex)
		 {
			 System.out.println("Error is-->"+ex);
			 return false;
		 }
	 }
//----------------here insert employee detail--------------------------------------------------------
	public boolean addEmployee(EmployeeModel em,String name)
	{		
		try {
			int eid=this.getId();
			if(eid!=0)
			{
			pmt=conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?)");
			pmt.setInt(1, eid);
			pmt.setString(2, em.getFname());
			pmt.setString(3, em.getLname());
			pmt.setString(4, em.getEmail());
			pmt.setString(5, em.getContact());
			pmt.setString(6, em.getUsername());
			pmt.setString(7, em.getPassword());
			pmt.setDate(8, em.getJoindate());
			pmt.setInt(9, em.getSal());
			int value=pmt.executeUpdate();
			if(value>0)
			{
				int did = this.getDepartmentid(name);
				if (did != -1) {
				//........insert value in employee and department join-.......................
					pmt = conn.prepareStatement("insert into empdptjoin values(?,?)");
					pmt.setInt(1, eid);
					pmt.setInt(2, did);
					return pmt.executeUpdate() > 0 ? true : false;
				} 
				else if (did == -1) {
					System.out.println("department not found");
					return false;
				} else {
					System.out.println("Some problem there.......");
					return false;
				}
				
			}
			else {
				return false;
			}
		}
		
	}
		catch(Exception ex)
		{
			System.out.println("Error is -->"+ex);
			return false;
		}
		
		return false;
	}
//--------------------------upload bulk employee----------------------------------------------------------
	public boolean uploadBulkEmployee(String empWithdetail[],String name)
	{
		try {
			int eid=this.getId();
			if(eid!=0)
			{
			pmt=conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?)");
			pmt.setInt(1, eid);
			pmt.setString(2, empWithdetail[0]);
			pmt.setString(3, empWithdetail[1]);
			pmt.setString(4, empWithdetail[2]);
			pmt.setString(5, empWithdetail[3]);
			pmt.setString(6, empWithdetail[4]);
			pmt.setString(7, empWithdetail[5]);
			pmt.setString(8, empWithdetail[6]);
			pmt.setInt(9, Integer.parseInt(empWithdetail[7].trim()));
			int value=pmt.executeUpdate();
			if(value>0)
			{
				int did = this.getDepartmentid(name);
				if (did != -1) {
				//........insert value in employee and department join-.......................
					pmt = conn.prepareStatement("insert into empdptjoin values(?,?)");
					pmt.setInt(1, eid);
					pmt.setInt(2, did);
					return pmt.executeUpdate() > 0 ? true : false;
				} 
				else if (did == -1) {
					System.out.println("department not found");
					return false;
				} else {
					System.out.println("Some problem there.......");
					return false;
				}
				
			}
			else {
				return false;
			}
		}
		
	}
		catch(Exception ex)
		{
			System.out.println("Error is -->"+ex);
			return false;
		}
		
		return false;
	}
	
//	----------here return all employee data to service by using Arraylist-----------------------------------
	public List <EmployeeModel> employeelist()
	{
		try {
			pmt=conn.prepareStatement("select *from employee");
			rs=pmt.executeQuery();
			while(rs.next())
			{
				EmployeeModel em=new EmployeeModel();
				em.setId(rs.getInt(1));
				em.setFname(rs.getString(2));
				em.setLname(rs.getString(3));
				em.setEmail(rs.getString(4));
				em.setContact(rs.getString(5));
				em.setUsername(rs.getString(6));
				em.setPassword(rs.getString(7));
				em.setJoindate(rs.getDate(8));
				em.setSal(rs.getInt(9));
				al.add(em);
			}
			return al.size()>0?al:null;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return null;
		}
	}
//	------------------------------DELETE LOGIC---------------------------------------------------
	public boolean isDelete(int id)
	{
		try {
			pmt=conn.prepareStatement("delete from employee where emp_id=?");
			pmt.setInt(1, id);
			value=pmt.executeUpdate();
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
			System.out.println("Error is-->"+ex);
			return false;
		}		
	}
//----------logic Display all deleted employee or leave employee---------------------------
	public List <Leave_empModel> leaveEmpList(){
		try {
			pmt=conn.prepareStatement("select *from del_employee");
			rs=pmt.executeQuery();
			while(rs.next())
			{
				Leave_empModel em1=new Leave_empModel();
				em1.setId(rs.getInt(1));
				em1.setFname(rs.getString(2));
				em1.setLname(rs.getString(3));
				em1.setEmail(rs.getString(4));
				em1.setContact(rs.getString(5));
				em1.setUsername(rs.getString(6));
				em1.setPassword(rs.getString(7));
				em1.setJoindate(rs.getDate(8));
				em1.setSal(rs.getInt(9));
				al1.add(em1);
			}
			return al1.size()>0?al1:null;
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return null;
		}
		
		
	}
//-----------------------------------Update logic-------------------------------------------------------------
	public boolean isUpdateEmployee(EmployeeModel em,String user,String pass)
	{
		try {
			pmt=conn.prepareStatement("update employee set emp_fname=?,lname=?,email=?,contact=?,salary=? where username=? and password=?");
			pmt.setString(1, em.getFname());
			pmt.setString(2, em.getLname());
			pmt.setString(3, em.getEmail());
			pmt.setString(4,em.getContact());
			pmt.setInt(5, em.getSal());
			pmt.setString(6, user);
			pmt.setString(7, pass);
			value=pmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return false;
		}
	}
//------------------------------------SERCH EMPLOYEE BY ID--------------------------------------------------------
	public void isSerchEmployee(int id)
	{
		try {
			pmt=conn.prepareStatement("select *from employee where emp_id=?");
			pmt.setInt(1, id);
			rs=pmt.executeQuery();
			if(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t"+rs.getDate(8)+"\t"+rs.getInt(9));
			}
			else {
				System.out.println("Employee not found...!");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
		}
	}

}
