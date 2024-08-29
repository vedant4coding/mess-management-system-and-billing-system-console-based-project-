package org.mess.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PathHelper {
	
	static Properties p = new Properties();
	static {
		File f = new File(".");
		String filePath = f.getAbsolutePath();
		String mainPath = filePath.substring(0,filePath.length()-1) + "src\\org\\mess\\config\\db.properties";
		FileInputStream fin;
		
		try {
			fin = new FileInputStream(mainPath);
			p.load(fin);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
