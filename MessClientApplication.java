package org.mess.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.mess.model.*;
import org.mess.repository.DatesRepository;
import org.mess.service.*;

public class MessClientApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegistrationService cs = new RegistrationService();

		List<RegistrationModel> list = new ArrayList<RegistrationModel>();
		RegistrationModel regModel = new RegistrationModel();

		List<MenuModel> menuModelList = new ArrayList<MenuModel>();

		String phoneNo;
		MenuService menuSerObj = new MenuService();

		do {
			// Admin Login
			Scanner xyz = new Scanner(System.in);
			System.out.println("*****Enter Username*****");
			String username = xyz.nextLine();
			System.out.println("*****Enter Password*****");
			String password = xyz.nextLine();

			boolean once = false;
			if (username.equals("mess") && password.equals("mess")) {
				do {
					if (!once) {
						System.out.println("*****Logged in Successfully*****");
						once = true;
					}
					// Options for Admin
					System.out.println("---> 1. Customer Operations ");
					System.out.println("---> 2. Generate Bill ");
					System.out.println("Enter choice ");
					int choice = xyz.nextInt();

					switch (choice) {

					case 1:
						// ********** Admin wants to do Customer Operations
						
						
						
						
						
						
						
						
						
						
						
						break;
					case 2:
						// ********* Admin wants to Generate Bill directly **********

						System.out.println("*****Enter PhoneNo*****");
						xyz.nextLine();
						// take phone no to check whether the customer is registered or not
						phoneNo = xyz.nextLine();

						regModel.setPhoneNo(phoneNo);
						list = cs.isCheckCustomerUsingPhoneNo(regModel);
						if (list != null) {
							System.out.println("Yes, Customer Exist");
							int Cid = 0 ;
							for (RegistrationModel m : list) {
								Cid = m.getCustomerId();
								System.out.println(m.getCustomerId() + "\t" + m.getName() + "\t" + m.getPhoneNo() + "\t"
										+ m.getEmail());
							}
						// check monthly member or not
						DatesModel obj1 = new DatesModel();
						obj1.setCid(Cid);
						DatesService model = new DatesService();
						if(model.CheckMessMember(obj1)) {
							System.out.println("Yes Cutomer is Member and He can Eat.");
							
							// auto attendance
							// now automatic attendance and generating bill
							AttendanceModel modelAttModel = new AttendanceModel();
							AttendanceService modelAttSer = new AttendanceService();
							
							modelAttModel.setCid(Cid);
							
							//call
							boolean val = modelAttSer.InsertionAttendanceSer(modelAttModel);
							if(val) {
								System.out.println("Attendance recorded successfully.");
								
								// automatic bill
								BillModel modelBillModel1 = new BillModel();
								modelBillModel1.setCid(Cid);
								System.out.println(modelBillModel1.getMid());
								// call bill service
								BillService modelBillSer = new BillService();
								
								if(modelBillSer.InsertBillService(modelBillModel1)) {
									System.out.println("Bill Inserted Successfully");
								}else {
									
									System.out.println("Bill Insertion Unsuccessfull");
									break;
								}
								// now update dates table UsedAmnt column
								
								DatesService objDatesSer = new DatesService();
								
								if(objDatesSer.UpdateUsedAmnt(obj1)) {
									System.out.println("UsedAmnt updated successfully");
								}else {
									System.out.println("UsedAmnt Updation Unsuccessfull");
								}
							}else {
								System.out.println("Attendance Unsuccessful.");
							}
							
							}else {
								System.out.println("Customer is Not a Member");
								
								
								System.out.println("***** Enter Choice *****  ");
								System.out.println("1. Want to Take Admission");
								System.out.println("2. Go to Bill");
								int key = xyz.nextInt();
								switch (key) {
								case 1: {
									// Want to take admission 
									// yes
									RegistrationService objRegSer = new RegistrationService();
									int Cid1 = objRegSer.fetchCidService(phoneNo);

									System.out.println("Enter Time (1 time mess/ 2 time mess)");
									int time = xyz.nextInt();

									DatesService datesService = new DatesService();
									DatesModel model1 = new DatesModel();
									model1.setCid(Cid1);

									model1.setUsedAmnt(0);
									model1.setTimeid(time);
									boolean b = datesService.insertDatesService(model1);

									if (b) {
										System.out.println("Insertion Successfully");
									} else {
										System.out.println("Insertion failed");
									}
									
									// now automatic attendance and generating bill
									AttendanceModel modelAttModel = new AttendanceModel();
									AttendanceService modelAttSer = new AttendanceService();
									
									modelAttModel.setCid(Cid1);
									
									//call
									boolean val = modelAttSer.InsertionAttendanceSer(modelAttModel);
									if(val) {
										System.out.println("Attendance recorded successfully.");
										
										// automatic bill
										BillModel modelBillModel1 = new BillModel();
										modelBillModel1.setCid(Cid1);
										System.out.println(modelBillModel1.getMid());
										// call bill service
										BillService modelBillSer = new BillService();
										
										if(modelBillSer.InsertBillService(modelBillModel1)) {
											System.out.println("Bill Inserted Successfully");
										}else {
											
											System.out.println("Bill Insertion Unsuccessfull");
											break;
										}
										
										// now update dates table UsedAmnt column
										
										DatesService objDatesSer = new DatesService();
										
										if(objDatesSer.UpdateUsedAmnt(model1)) {
											System.out.println("UsedAmnt updated successfully");
										}else {
											System.out.println("UsedAmnt Updation Unsuccessfull");
										}
										
										
									}else {
										System.out.println("Attendance failed");
										break;
									}
									
									
									break;
								}
								case 2:{
									// go to bill
									menuModelList = menuSerObj.printMenu();
									if (menuModelList != null) {
										System.out.println("*****Menu List*****");
										for (MenuModel m : menuModelList) {
											System.out
													.println(m.getMid() + "\t" + m.getMenuName() + "\t" + m.getPrice());
										}
									}

									// now we will generate bill
									// taking input from admin for menuId and qty
									System.out.println("***** Enter menuId : ");
									int menuId = xyz.nextInt();
									// checking menuId is valid or not
									if (!menuSerObj.checkMenuService(menuId)) {
										System.out.println("$$$$$ Invalid menuId $$$$$");
									}
									System.out.println("***** Enter Quantity : ");
									int qty = xyz.nextInt();
									if (qty <= 0) {
										System.out.println("$$$$$ Invalid Qty $$$$$");
										break;
									}

									// create object of BillModel and set Cid, Mid, Qty and pass object as
									// parameter to BillService
									BillModel objBillModel = new BillModel();
									objBillModel.setMid(menuId);
									objBillModel.setQty(qty);

									// now to set Cid we have to fetch it we will fetch it using phoneNo
									int fetchedCid = cs.fetchCidService(phoneNo);

									objBillModel.setCid(fetchedCid);

									// now calling BillService class function to insert into bill table
									BillService modelBillService = new BillService();
									if(modelBillService.InsertBillService(objBillModel)) {
										System.out.println("Bill Rocord Added Successfully");
									}else {
										System.out.println("Bill Record Unsuccessfull");
									}
									
									
									break;
								}
								default:
									System.out.println("*****Enter valid choice*****");
								}
								
								
							}
							
						} else {
							System.out.println("Customer Doesn't Exist");

							// choice
							System.out.println("*****Enter Choice*****");
							System.out.println("1. Want to Register");
							System.out.println("2. Direct go to Bill");

							choice = xyz.nextInt();
							switch (choice) {
							case 1:
								xyz.nextLine();
								// this case is for if ****** user want to register *****
								System.out.println("---> Enter name ");
								String name = xyz.nextLine();

								System.out.println("---> Enter Email ");
								String email = xyz.nextLine();
								// now we have to save name, email, phoneNo into registration table

								RegistrationModel objRegModel = new RegistrationModel();

								// set values into object
								objRegModel.setName(name);
								objRegModel.setPhoneNo(phoneNo);
								objRegModel.setEmail(email);

								// create obj of RegistrationService and call method ........
								if (cs.isAddCustomer(objRegModel)) {
									System.out.println("***** Customer Added Successfully *****");
								} else {
									System.out.println("$$$$$ Something Error Customer Not added $$$$$");
								}

								System.out.println("---> Want to Join mess");
								System.out.println("---> 1. Yes");
								System.out.println("---> 2. No");
								// xyz.nextLine();
								choice = xyz.nextInt();

								if (choice == 1) {
									// yes
									RegistrationService objRegSer = new RegistrationService();
									int Cid1 = objRegSer.fetchCidService(phoneNo);

									System.out.println("Enter Time (1 time mess/ 2 time mess)");
									int time = xyz.nextInt();

									DatesService datesService = new DatesService();
									DatesModel model1 = new DatesModel();
									model1.setCid(Cid1);

									model1.setUsedAmnt(0);
									model1.setTimeid(time);
									boolean b = datesService.insertDatesService(model1);

									if (b) {
										System.out.println("Insertion Successfully");
									} else {
										System.out.println("Insertion failed");
									}
									
									// now automatic attendance and generating bill
									AttendanceModel modelAttModel = new AttendanceModel();
									AttendanceService modelAttSer = new AttendanceService();
									
									modelAttModel.setCid(Cid1);
									
									//call
									boolean val = modelAttSer.InsertionAttendanceSer(modelAttModel);
									if(val) {
										System.out.println("Attendance recorded successfully.");
										
										// automatic bill
										BillModel modelBillModel1 = new BillModel();
										modelBillModel1.setCid(Cid1);
										System.out.println(modelBillModel1.getMid());
										// call bill service
										BillService modelBillSer = new BillService();
										
										if(modelBillSer.InsertBillService(modelBillModel1)) {
											System.out.println("Bill Inserted Successfully");
										}else {
											
											System.out.println("Bill Insertion Unsuccessfull");
											break;
										}
										
										// now update dates table UsedAmnt column
										
										DatesService objDatesSer = new DatesService();
										
										if(objDatesSer.UpdateUsedAmnt(model1)) {
											System.out.println("UsedAmnt updated successfully");
										}else {
											System.out.println("UsedAmnt Updation Unsuccessfull");
										}
										
										
									}else {
										System.out.println("Attendance failed");
										break;
									}
									
									
									
									
								} else if (choice == 2) {
									// no
									menuModelList = menuSerObj.printMenu();
									if (menuModelList != null) {
										System.out.println("*****Menu List*****");
										for (MenuModel m : menuModelList) {
											System.out
													.println(m.getMid() + "\t" + m.getMenuName() + "\t" + m.getPrice());
										}
									}

									// now we will generate bill
									// taking input from admin for menuId and qty
									System.out.println("***** Enter menuId : ");
									int menuId = xyz.nextInt();
									// checking menuId is valid or not
									if (!menuSerObj.checkMenuService(menuId)) {
										System.out.println("$$$$$ Invalid menuId $$$$$");
									}
									System.out.println("***** Enter Quantity : ");
									int qty = xyz.nextInt();
									if (qty <= 0) {
										System.out.println("$$$$$ Invalid Qty $$$$$");
										break;
									}

									// create object of BillModel and set Cid, Mid, Qty and pass object as
									// parameter to BillService
									BillModel objBillModel = new BillModel();
									objBillModel.setMid(menuId);
									objBillModel.setQty(qty);

									// now to set Cid we have to fetch it we will fetch it using phoneNo
									int fetchedCid = cs.fetchCidService(phoneNo);

									objBillModel.setCid(fetchedCid);

									// now calling BillService class function to insert into bill table
									BillService modelBillService = new BillService();
									if(modelBillService.InsertBillService(objBillModel)) {
										System.out.println("Bill Rocord Added Successfully");
									}else {
										System.out.println("Bill Record Unsuccessfull");
									}

								} else {
									System.out.println("$$$$$ Invalid Choice $$$$$");
								}

								break;
							case 2:
								// printing menu to get id of items from menu table

								menuModelList = menuSerObj.printMenu();
								if (menuModelList != null) {
									System.out.println("*****Menu List*****");
									for (MenuModel m : menuModelList) {
										System.out.println(m.getMid() + "\t" + m.getMenuName() + "\t" + m.getPrice());
									}
								}

								// now we will generate bill
								// taking input from admin for menuId and qty
								System.out.println("***** Enter menuId : ");
								int menuId = xyz.nextInt();
								// checking menuId is valid or not
								if (!menuSerObj.checkMenuService(menuId)) {
									System.out.println("$$$$$ Invalid menuId $$$$$");
								}
								System.out.println("***** Enter Quantity : ");
								int qty = xyz.nextInt();
								if (qty <= 0) {
									System.out.println("$$$$$ Invalid Qty $$$$$");
									break;
								}
								// call BillService class method InsertBillService(int Mid, int Qty)
								// first create obj of BillService
								BillService objBillService = new BillService();
								BillModel modelBillModel = new BillModel();
								modelBillModel.setMid(menuId);
								modelBillModel.setQty(qty);
								
								if (objBillService.InsertBillService(modelBillModel)) {
									System.out.println("*****Successfull Insertion into Bill Table*****");
								} else {
									System.out.println("*****Unsuccessfull Insertion*****");
								}

								break;
							default:
								System.out.println("***** Invalid Choice *****");
								break;
							}

						}

						break;
					default:
						System.out.println("***** Invalid Choice *****");
						break;
					}

				} while (false);

			} else {
				System.out.println("*****Incorrect username/password");
			}

		} while (false);
	}

}