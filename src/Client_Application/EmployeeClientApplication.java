package Client_Application;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import Model.AdminModel;
import Model.DepartmentModel;
import Model.EmployeeModel;
import Model.Leave_empModel;
import Service.AdminService;
import Service.AttendenceService;
import Service.DepartmentService;
import Service.EmployeeService;
import Service.SalaryService;

public class EmployeeClientApplication {

	public static void main(String[] args) {
		AdminService as = new AdminService();
		DepartmentService ds = new DepartmentService();
		EmployeeService es = new EmployeeService();
		AttendenceService atten = new AttendenceService();
		SalaryService ss= new SalaryService();
		Scanner xyz = new Scanner(System.in);
		
		System.out.println("Enter the userName");
		String user = xyz.nextLine();
		System.out.println("Enter the passWord");
		String pass = xyz.nextLine();
		boolean b = as.isVerifyAdmin(user, pass);

		if (b) {
			System.out.println("\n######  LOGIN SUCCESSFULLY  ######");
		int choice;	
		
			do {
				System.out.println("\n\ncase 0: Add new Admin");
				System.out.println("ncase 1:Add Department Detail");
				System.out.println("case 2:About Employee Operation"); 
				System.out.println("Case 3: take Attendense");
				System.out.println("Case 4: Show Attendense Month Wise");//2,3
				System.out.println("Case 5:Show Employee Attendense Specific month");//2
				System.out.println("Case 6:Show Per day salary of Employee");
				System.out.println("Case 7:Show Employee salary by user_name");
				
				System.out.println("\nEnter the Choice");
				choice = xyz.nextInt();
				
				switch (choice) {
				case 0:
					xyz.nextLine();
					System.out.println("Enter the Admin id");
					int id = xyz.nextInt();
					xyz.nextLine();
					System.out.println("Enter the username");
					user = xyz.nextLine();
					System.out.println("Enter the password");
					pass = xyz.nextLine();
					AdminModel am = new AdminModel(id, user, pass);
					b = as.isAddAdmin(am);
					System.err.println((b) ? "Admin added successfully...!" : "Some problem is there");
					break;
				case 1:
					xyz.nextLine();
					System.out.println("Enter the Department Name");
					String name = xyz.nextLine();
					DepartmentModel dm = new DepartmentModel(name);
					int result = ds.addDepartment(dm);
					if (result == 1) {
						System.err.println("Department added successfully..!");
					} else if (result == -1) {
						System.err.println("Department already present..!");
					} else {
						System.err.println("some problem is there..!");
					}
					break;
				case 2:					//Nested switch case
					String ch;
					do {
						System.out.println("\nPress 'a' for :Add New Employee");
						System.out.println("Press 'bulkdata' for :Add Multiple Employee at one time");
						System.out.println("Press 'v' for Display Employee");
						System.out.println("Press 'd' for :Delete Employee");
						System.out.println("press 'leave_emp' for :how many employee leave job");
						System.out.println("Press 'u' for :Update Employee details");
						System.out.println("Press 's' for :Serch Employee");
						System.out.println("press 'e' for exit from nested switch case");				
						ch=xyz.next();
		//				String ch1=ch1.equalsIgnoreCase(ch);
						switch(ch)
						{
							case "a":
								xyz.nextLine();
								System.out.println("Enter the Employee first name");
								String fname = xyz.nextLine();
								System.out.println("Enter the Employee last name");
								String lname = xyz.nextLine();
								System.out.println("Enter email");
								String email = xyz.nextLine();
								System.out.println("Enter contact");
								String contact = xyz.nextLine();
								System.out.println("Enter the username of employee");
								user = xyz.nextLine();
								System.out.println("Enter the password");
								pass = xyz.nextLine();
								System.out.println("Enter the join date");
								String date = xyz.nextLine();
								Date d1=Date.valueOf(date);
								System.out.println("Enter the Salary");
								int sal = xyz.nextInt();
								xyz.nextLine();
								System.out.println("Enter the department name");							
								name = xyz.nextLine();
								EmployeeModel em = new EmployeeModel(fname, lname, email, contact, user, pass, d1,sal);
								result = es.addEmployee(em, name);
								if (result == 1) {
									System.out.println("Employee added successfully..!");
								} else if (result == -1) {
									System.err.println("Employee not added..!");
								} else {
									System.err.println("some problem is there..!");
								}
								break;
								
							case "bulkdata":
								xyz.nextLine();
								System.out.println("Enter the Department name for store Bulk employee");
								name = xyz.nextLine();
									b=es.uploadBulkEmployee(name);
									if(b)
									{
										System.out.println("Employees added successfullly..!");
									}
									else {
										System.out.println("Employees not added");
									}
									
								break;
							case "v":
								List <EmployeeModel> al=es.employeelist();
								System.out.println("id \t fname \t lname \t email \t\t \tcontact \t username \t password \t join date \t salary ");
								for(EmployeeModel a:al)
								{
									System.out.println(a.getId()+"\t"+a.getFname()+"\t"+a.getLname()+"\t"+a.getEmail()+"\t"+a.getContact()+"\t"+a.getUsername()+"\t\t"+a.getPassword()+"\t"+a.getJoindate()+"\t"+a.getSal());
								}
								break;
							case "d":
								System.out.println("Enter  the ID for Delete Employee ");
								 id=xyz.nextInt();
								int val=es.isDelete(id);
								if(val==1)
								{
									System.out.println("Employee delete successfully");
								}
								else {
									System.err.println("Employee not deleted...!");
								}
								break;
							case "leave_emp":
								List <Leave_empModel> al1=es.leaveEmpList();
								if(al1!=null)
								{
									System.out.println("id \t fname \t lname \t email \t\t \tcontact \t username \t password \t join date \t salary ");
									for(Leave_empModel aa:al1)
									{
										System.out.println(aa.getId()+"\t"+aa.getFname()+"\t"+aa.getLname()+"\t"+aa.getEmail()+"\t"+aa.getContact()+"\t"+aa.getUsername()+"\t\t"+aa.getPassword()+"\t"+aa.getJoindate()+"\t"+aa.getSal());
									}
								}
								else {
									System.err.println("data not found...!");
								}
							
								break;
							case "u":
								xyz.nextLine();
								System.out.println("Enter the user_name and password for update employee detail");
								user=xyz.nextLine();
								pass=xyz.nextLine();
								System.out.println("Enter the Employee first name,last name,email,contact,"
										+ ",salary ");
								fname=xyz.nextLine();
								lname=xyz.nextLine();
								email=xyz.nextLine();
								contact=xyz.nextLine();
								sal=xyz.nextInt();
								em=new EmployeeModel();
								em.setFname(fname);
								em.setLname(lname);
								em.setEmail(email);
								em.setContact(contact);
								em.setSal(sal);
								 b=es.isUpdateEmployee(em,user,pass);
								 if(b)
								 {
									 System.out.println("Employee update successfully");
								 }
								 else {
									 System.err.println("Employee not update");
								 }
								break;
							case "s":
								System.out.println("Enter the employee id for serch");
								id=xyz.nextInt();
								 es.isSerchEmployee(id);
								
								break;
							case "e":
								break;
								default:
									System.out.println("Enter the valid choice");
						}
					}while(!("e".equals(ch)));
					
					break;
				case 3:
					xyz.nextLine();
					System.out.println("Enter the user_name and password for track attendense");
					user=xyz.nextLine();
					pass=xyz.nextLine();
					b=atten.isAttendence(user,pass);
					if(b)
					{
						System.out.println("Your Attendense has marked..!");
					}
					else {
						System.err.println("ERROR..!");
					}				
					break;
				case 4:
					xyz.nextLine();
					System.out.println("Enter the user_name of employee ");
					user=xyz.nextLine();
					atten.calAttMonthwise(user);
					
					break;
				case 5:		
					xyz.nextLine();
					System.out.println("Enter the user_name of employee ");
					user=xyz.nextLine();
					System.out.println("Enter the month for calculate Attendence(like as 1,2,3)");
					int month=xyz.nextInt();
					int value=atten.calAttendense(user,month);
					
					if(value!=-1 && value!=0)
					{
						System.out.println("name"+"\t"+"Month"+"\t"+"count");
						System.out.println(user+"\t"+month+"\t"+value);
					//	System.out.println("Attendense Show Successfully");
					}
					else if(value==-1)
					{
						System.err.println("Attendense Reacord not found");
					}
					else {
						System.err.println("Employee not found");
					}
					break;
				case 6:
					xyz.nextLine();
					System.out.println("Enter the user_name of employee");
					user=xyz.nextLine();
					value=ss.PerDaysal(user);
					System.out.println("Perday Salary of Employee is:"+value);
					break;
				case 7:
					xyz.nextLine();
					System.out.println("Enter the user_name of employee");
					user=xyz.nextLine();
					System.out.println("Enter the month for calculate salary (like as 1,2,3)");
					month=xyz.nextInt();
					 b=ss.SalaryService(user,month);
					 if(!b)
					 {
						 System.out.println("Some Problem is there...........");
					 }
					break;
					default:
						System.err.println("Enter valid choice");
				}
				
			} while (true);

		} else {
			System.err.println("SORRY..!Enter valid user name and password..!");
		}	
	}

}
