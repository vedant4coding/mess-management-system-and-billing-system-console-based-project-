package org.mess.repository;

import java.sql.Date;
import org.mess.config.*;
import org.mess.model.DatesModel;

public class DatesRepository extends DbConfig {

	public Date getDate() {
		try {
			stmt = conn.prepareStatement("select curdate()");
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getDate(1);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean insertDatesRepo(DatesModel obj) {

		try {
			Date d = getDate();
			stmt = conn.prepareStatement("Insert into dates  values ('0', ?, ?, ?, ?)");
			stmt.setInt(1, obj.getCid());
			stmt.setDate(2,d);
			stmt.setInt(3, obj.getUsedAmnt());
			stmt.setInt(4, obj.getTimeid());

			int value = stmt.executeUpdate();

			if (value > 0) {
				return true;
			}
			return false;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	public boolean UpdateUsedAmnt(DatesModel obj) {
		
		try {
			if(obj.getUsedAmnt() == 0) {
				obj.setUsedAmnt(50);
			}
			stmt = conn.prepareStatement("Update Dates set UsedAmnt = UsedAmnt + ? where Cid = ?" );
			stmt.setInt(1, obj.getUsedAmnt());
			stmt.setInt(2, obj.getCid());
			
			int value = stmt.executeUpdate();

			if (value > 0) {
				return true;
			}
			return false;
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	public boolean CheckMessMember(DatesModel obj) {
		
		try {
			
			stmt = conn.prepareStatement("select UsedAmnt, Timeid from dates where (select max(Start_date) from "
					+ "Dates group by Cid having Cid = ?) and Cid = ?");
			stmt.setInt(1, obj.getCid());
			stmt.setInt(2, obj.getCid());
			rs = stmt.executeQuery();
			int usedAmount, time ;
			if(rs.next()) {
				usedAmount = rs.getInt(1);
				time = rs.getInt(2);
				
				if(time == 1) {
					if(usedAmount + 50 < 1500 ) {
						return true;
					}else {
						return false;
					}
				}else {
					if(usedAmount + 50 < 3000 ) {
						return true;
					}else {
						return false;
					}
				}
				
			}else {
				return false;
			}
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
}
