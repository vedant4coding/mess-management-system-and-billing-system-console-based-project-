package org.mess.repository;
import org.mess.config.*;
import java.util.List;
import org.mess.model.MenuModel;
import java.util.ArrayList;
public class MenuRepository extends DbConfig{
	
	// printMenu function will print menu for admin
	public List<MenuModel> printMenu(){
		List <MenuModel> MenuList = new ArrayList<MenuModel> ();
		try {
			stmt = conn.prepareStatement("select *from Menu");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				// create an object of MenuModel class
				MenuModel m = new MenuModel();
				
				// store the data we get from rs statement in MenuModel object
				m.setMid(rs.getInt(1));
				m.setMenuName(rs.getString(2));
				m.setPrice(rs.getInt(3));
				
				// add this object into list 
				MenuList.add(m);
				
			}
			
			return MenuList.size() > 0 ? MenuList : null;
		
		}catch(Exception ex) {
			System.out.println("Error is : " + ex);
			return null;
		}
	}
	
	// checkMenuId function will check that MenuId is present or not in menu table
	public boolean checkMenuId (int menuId) {
			List <MenuModel> menuList = new ArrayList<MenuModel> ();
			try {
				stmt = conn.prepareStatement("select * from Menu where Mid = ?");
				stmt.setInt(1, menuId);
				rs = stmt.executeQuery();
				
				while(rs.next()){
					// create an object of MenuModel class
					MenuModel m = new MenuModel();
					
					// store the data we get from rs statement in MenuModel object
					m.setMid(rs.getInt(1));
					m.setMenuName(rs.getString(2));
					m.setPrice(rs.getInt(3));
					
					// add this object into list 
					menuList.add(m);
					
				}
				
				return menuList.size() > 0 ? true : false;
				
			}catch(Exception ex){
				System.out.println("Error is :" + ex);
				return false;
			}	
	}
	// fetchPrice function to get Price of menu using menuId
	public int fetchPrice(int menuId) {
		try {
			stmt = conn.prepareStatement("select Price from menu where Mid = ?");
			stmt.setInt(1, menuId);
			rs = stmt.executeQuery();
			// creating MenuModel obj to set Price and return it
			MenuModel m = new MenuModel();
			
			if(rs.next()) {
				m.setPrice(rs.getInt(1));
			}
			return m.getPrice();
		}catch(Exception ex) {
			System.out.println("Error is :" + ex);
			return 0;
		}
	}
}
