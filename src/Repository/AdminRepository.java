package Repository;

import Model.AdminModel;

public class AdminRepository extends DBConfig{

	public boolean isAddAdmin(AdminModel am)
	{
		try {
			pmt=conn.prepareStatement("insert into admin values(?,?,?)");
			pmt.setInt(1, am.getId());
			pmt.setString(2, am.getUserName());
			pmt.setString(3,am.getPassword());
			int value=pmt.executeUpdate();
			if(value>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is-->"+ex);
			return false;
		}
	}
	public boolean isVerifyAdmin(String userName,String password)
	{
		try {
			pmt=conn.prepareStatement("select *from admin where admin_username=? and admin_password=?");
			pmt.setString(1, userName);
			pmt.setString(2, password);
			rs=pmt.executeQuery();
			if(rs.next())
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
}
