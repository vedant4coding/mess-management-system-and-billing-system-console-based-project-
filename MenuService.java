package org.mess.service;
import org.mess.repository.MenuRepository;
import org.mess.model.*;
import java.util.List;

public class MenuService {
	// created object of MenuRepository to call functions from this class
	MenuRepository m = new MenuRepository();
	
	public List<MenuModel> printMenu(){
		return m.printMenu();
	}
	
	public boolean checkMenuService(int menuId) {
		return m.checkMenuId(menuId);
	}
	
}
