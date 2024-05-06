package Service;

import Repository.SalaryRepository;

public class SalaryService {
	SalaryRepository sr = new SalaryRepository();
	public boolean SalaryService(String user,int month)
	{
		return sr.SalaryService(user, month)?true:false;
	}
	public int PerDaysal(String user)
	{
		return sr.PerDaysal(user);
	}
}
