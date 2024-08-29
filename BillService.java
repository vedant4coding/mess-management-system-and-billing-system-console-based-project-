package org.mess.service;

import org.mess.model.*;
import org.mess.repository.*;


public class BillService {

	// function for Insertion purpose
	public boolean InsertBillService(BillModel obj) {

		try {
			int totalAmnt = 50;
			if(obj.getMid() != 0) {
				totalAmnt = generateTotalAmnt(obj.getMid(), obj.getQty()); 
			}
			
			obj.setTotalAmnt(totalAmnt);
			
			// calling BillRepository function InsertBillRepo 
			BillRepository objBillRepo = new BillRepository();
			boolean value = objBillRepo.InsertBillRepo(obj); // giving BillModel object as argument
			
			return value;

		} catch (Exception ex) {
			System.out.println("Error is :" + ex);
			return false;
		}
	}
	public int generateTotalAmnt(int Mid, int Qty) {
		try {
			// fetch price from menu table
			MenuRepository objMenuRepo = new MenuRepository();
			int price = objMenuRepo.fetchPrice(Mid);
			int totalAmnt = price * Qty;
			
			return totalAmnt;
		}catch(Exception ex) {
			System.out.println("Error is : " + ex);
			return 0;
		}
		
	}
}
