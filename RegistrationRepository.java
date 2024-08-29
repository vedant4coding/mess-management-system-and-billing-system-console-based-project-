package org.mess.repository;
import java.util.ArrayList;
import java.util.List;

import org.mess.config.DbConfig;
import org.mess.model.RegistrationModel;

public class RegistrationRepository extends DbConfig{
	// Customer column Insert
	List<RegistrationModel> list = new ArrayList<RegistrationModel>();
	
	public boolean isAddCustomer(RegistrationModel obj) {
		try {
			stmt = conn.prepareStatement("insert into registration values('0',?,?,?)");
			stmt.setString(1,obj.getName());
			stmt.setString(2,obj.getPhoneNo());
			stmt.setString(3,obj.getEmail());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		}catch(Exception ex) {
			System.out.println("Error is :" + ex);
			return false;
		}
	}
	public List<RegistrationModel> isCheckCustomerUsingPhoneNo(RegistrationModel obj) {
		try {
			list  = new ArrayList<RegistrationModel>();
			stmt = conn.prepareStatement("select * from registration where phoneNo = ?");
			stmt.setString(1, obj.getPhoneNo());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				RegistrationModel model = new RegistrationModel();
				model.setCustomerId(rs.getInt(1));
				model.setName(rs.getString(2));
				model.setPhoneNo(rs.getString(3));
				model.setEmail(rs.getString(4));
				list.add(model);

				return list.size() > 0? list : null;
			}
			return list.size() > 0? list : null;
		}catch (Exception ex){
			System.out.println("Error is :" + ex);
			return null;
		}
	}
	public int fetchCidRepo(String phoneNo) {
		try {
			stmt = conn.prepareStatement("select Cid from registration where phoneNo = ?");
			stmt.setString(1, phoneNo);
			rs = stmt.executeQuery();
			if(rs.next())
			{
			return  rs.getInt(1);
			}
			else
			{
				return 0;
			}
		}catch(Exception ex) {
			System.out.println("Error is :" + ex);
			return 0;
		}
	}
	
	
}
