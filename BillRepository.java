package org.mess.repository;

import java.sql.Date;

import org.mess.config.DbConfig;
import org.mess.model.BillModel;

public class BillRepository extends DbConfig {
	public Date getDate()
	{
		try {
		stmt=conn.prepareStatement("select curDate()");
		rs=stmt.executeQuery();
		if(rs.next())
		{
			return rs.getDate(1);
		}
		
	}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	
	// InsertBillRepo function to insert a bill into bill table
	public boolean InsertBillRepo(BillModel obj) {
		try {
			Date d= getDate();
			if(obj.getCid() != 0 && obj.getMid() != 0) {
				// only registered user
				stmt = conn.prepareStatement("Insert into bill(Bid, Date, Mid, TotalAmnt, Qty, Cid) "
						+ "values('0', ?, ?, ?, ?, ?)");
				stmt.setDate(1, d);
				stmt.setInt(2, obj.getMid());
				stmt.setInt(3, obj.getTotalAmnt());
				stmt.setInt(4, obj.getQty());			
				stmt.setInt(5, obj.getCid());
			}else if(obj.getMid() == 0) {
				// mess member
				stmt = conn.prepareStatement("Insert into bill(Bid, Date, TotalAmnt, Qty, Cid) "
						+ "values('0', ?, ?, ?, ?)");
				stmt.setDate(1, d);
				stmt.setInt(2, obj.getTotalAmnt());
				stmt.setInt(3, 1);	
				stmt.setInt(4, obj.getCid());
				
			}else {
				// walking user
				stmt = conn.prepareStatement("Insert into bill(Bid, Date, Mid, TotalAmnt, Qty) "
						+ "values('0', ?, ?, ?, ?)");
				stmt.setDate(1, d);
				stmt.setInt(2, obj.getMid());
				stmt.setInt(3, obj.getTotalAmnt());
				stmt.setInt(4, obj.getQty());
			}
			
			int value = stmt.executeUpdate();
			
			if(value > 0) {
				return true;
			}
			return false;
		}catch(Exception ex) {
			System.out.println("Error is : " + ex);
			return false;
		}
	}
	
}
