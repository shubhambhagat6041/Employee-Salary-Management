package Service;

import Model.AdminModel;
import Repository.AdminRepository;

public class AdminService {
	AdminRepository ar=new AdminRepository();
	public boolean isAddAdmin(AdminModel am)
	{
		return ar.isAddAdmin(am)?true:false;
	}
	public boolean isVerifyAdmin(String userName,String password)
	{
		return ar.isVerifyAdmin(userName, password)?true:false;
	}
}
