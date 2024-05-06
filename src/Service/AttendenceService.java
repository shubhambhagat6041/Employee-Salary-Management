package Service;

import Repository.AttendenceRepository;

public class AttendenceService {
	AttendenceRepository ar = new AttendenceRepository();
	public boolean isAttendence(String user,String pass)
	{
		return ar.isVerifylogin(user,pass)?ar.isAttendence():false;
	}
//=========================all month ===============================================================================	
	public void calAttMonthwise(String user)
	{
		ar.calAttMonthwise(user);
	}
	//===============================Specific month====================================================================================
	public int calAttendense(String user,int month)
	{
		return ar.calAttendense(user,month);
	}

}
