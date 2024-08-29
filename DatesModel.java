package org.mess.model;
import java.sql.Date;
public class DatesModel {
	
	private int Did;
	private Date Start_date;
	private int UsedAmnt;
	private int Timeid;
	private int Cid;
	
	public int getDid() {
		return Did;
	}
	public void setDid(int did) {
		Did = did;
	}
	public Date getStart_date() {
		return Start_date;
	}
	public void setStart_date(Date start_date) {
		Start_date = start_date;
	}
	public int getUsedAmnt() {
		return UsedAmnt;
	}
	public void setUsedAmnt(int usedAmnt) {
		UsedAmnt = usedAmnt;
	}
	public int getTimeid() {
		return Timeid;
	}
	public void setTimeid(int timeid) {
		Timeid = timeid;
	}
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	
	
}
