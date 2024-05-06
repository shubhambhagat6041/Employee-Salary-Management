package Model;

import java.sql.Date;

public class EmployeeModel {
	private int id;
	private String fname;
	private String lname;
	private String email;
	private String contact;
	private String username;
	private String password;
	private Date joindate;
	private int sal;
	public EmployeeModel(String fname,String lname,String email,String contact,String username,String password,Date joindate,int sal)
	{
		this.fname=fname;
		this.lname=lname;
		this.email=email;
		this.contact=contact;
		this.username=username;
		this.password=password;
		this.joindate=joindate;
		this.sal=sal;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public EmployeeModel()
	{
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}


}
