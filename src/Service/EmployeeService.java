package Service;

import java.util.List;
import java.io.*;
import Model.EmployeeModel;
import Model.Leave_empModel;
import Repository.EmployeeRepository;

public class EmployeeService {
	EmployeeRepository er = new EmployeeRepository();
	DepartmentService ds=new DepartmentService();
//	----------------------Add employee----------------------------------------------
	public int addEmployee(EmployeeModel em,String name)
	{
		return er.isEmployeePresent(em.getUsername(),em.getPassword())?-1:er.addEmployee(em,name)?1:0;
	}
	
//	-------------------------Add Bulk employee data-----------------------------------------------------
	public Boolean createFiles()
	{
	
		try {
			String path="D:\\Giri tech hub notes\\project\\employee";
			File f=new File(path);
			if(!(f.exists()))
			{
				f.mkdir();
			}
			List <String> deptList=ds.getAllEmployee();
			if(deptList!=null)
			{
				for(String deptName:deptList)
				{
					f=new File(path+"\\"+deptName+".csv");
					if(!(f.exists()))
					{
						f.createNewFile();
					}
					
				}
			}
			else {
				System.out.println("Department not found");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is -->"+ex);
			
		}
		return true;
	}
	public boolean uploadBulkEmployee(String name)
	{
		boolean b=this.createFiles();
		if(b)
		{
			String paths="D:\\Giri tech hub notes\\project\\employee";
			File f= new File(paths);
			File []fileList = f.listFiles();
			boolean flag=false;
			for(File file:fileList)
			{
				if(file.isFile())
				{
					int index=file.toString().indexOf(name);
					if(index!=-1)
					{
						flag=true;
						break;
					}
				}
			}
			if(flag)
			{
				try {
					FileReader fr=new FileReader(paths+"\\"+name+".csv");
					BufferedReader br=new BufferedReader(fr);
					String employee;
					while((employee=br.readLine())!=null)
					{
						String empWithdetail[]=employee.split(",");
						flag=er.uploadBulkEmployee(empWithdetail,name);
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else {
				System.out.println("Department file not found");
			}
			return true;
		}
		else {
			return false;
		}
	
	}
//	---------------------------Employee List-----------------------------------------------------
	public List <EmployeeModel> employeelist()
	{
		return er.employeelist();
	}
//	-------------------------Delete Employee-------------------------------------------------------------
	public int isDelete(int id)
	{
		return er.isDelete(id)?1:0;
	}
	
//------------------------deleted Employee by using trigger--------------------------------------------
	public List <Leave_empModel> leaveEmpList()
	{
		return er.leaveEmpList();
	}
//--------------------------UPDATE EMPLOYEE--------------------------------------------------------------
	public boolean isUpdateEmployee(EmployeeModel em,String user,String pass)
	{
		return er.isUpdateEmployee(em,user,pass)?true:false;
	}
	
//-----------------------------SEARCH EMPLOYEE------------------------------------------------------
	public void isSerchEmployee(int id) {
		er.isSerchEmployee(id);
	}

}
