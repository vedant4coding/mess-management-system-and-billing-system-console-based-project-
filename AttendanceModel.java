package org.mess.model;
import java.sql.Date;
public class AttendanceModel {

	private int Atid;
	private int Cid;
	private Date date;
	private String Time_M_N;
	
	public int getAtid() {
		return Atid;
	}
	public void setAtid(int atid) {
		Atid = atid;
	}
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime_M_N() {
		return Time_M_N;
	}
	public void setTime_M_N(String time_M_N) {
		Time_M_N = time_M_N;
	}
	
	
}
