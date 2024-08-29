package org.mess.model;
import java.sql.Date;
public class BillModel {
	private int Bid;
	private Date date;
	private int Cid ;
	private int Mid;
	private int TotalAmnt;
	private int Qty;
	
	public int getQty() {
		return Qty;
	}
	public void setQty(int qty) {
		Qty = qty;
	}
	public int getBid() {
		return Bid;
	}
	public void setBid(int bid) {
		Bid = bid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	public int getMid() {
		return Mid;
	}
	public void setMid(int mid) {
		Mid = mid;
	}
	public int getTotalAmnt() {
		return TotalAmnt;
	}
	public void setTotalAmnt(int totalAmnt) {
		TotalAmnt = totalAmnt;
	}
	 
	
	
}
