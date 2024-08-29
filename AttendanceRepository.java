package org.mess.repository;
import org.mess.model.*;
import java.time.LocalTime;
import org.mess.config.*;
public class AttendanceRepository extends DbConfig {
	
	public boolean InsertionAttendanceRepo(AttendanceModel model) {
		try {
			stmt = conn.prepareStatement("Insert into attendance(Atid, Cid, Time_M_N)"
					+ " values ('0', ?, ?)");
			stmt.setInt(1, model.getCid());
			stmt.setString(2,getTimeBasedMessage() );
			
			int value = stmt.executeUpdate();
			
			if (value > 0) {
				return true;
			}
			return false;
			
			
		}catch(Exception ex) {
			System.out.println("Error is : " + ex);
			return false;
		}
		
	}
	
	public String CheckTimeAttRepo() {
		
		try {
			stmt = conn.prepareStatement("SELECT CASE "
					+ "        WHEN CURTIME() < ? THEN 1"
					+ "        WHEN CURTIME() > ? THEN 2"
					+ "        ELSE 0 ");
			
			stmt.setString(1, "15:00:00");
			stmt.setString(2, "19:00:00");
			
			rs = stmt.executeQuery();
			int value = rs.getInt(1);
			if(value == 1) {
				return "M";
			}else if(value == 2) {
				return "N";
			}else {
				return "0";
			}
		}catch(Exception ex) {
			System.out.println("Error is : " + ex);
			return "0";
		}
	}
	
	public static String getTimeBasedMessage() {
        LocalTime currentTime = LocalTime.now();
        LocalTime threePM = LocalTime.of(15, 0);
        LocalTime sixThirtyPM = LocalTime.of(18, 30);

        if (currentTime.isBefore(threePM)) {
            return "M";
        } else if (currentTime.isAfter(sixThirtyPM)) {
            return "N";
        } else {
            return "0";
        }
    }
}
