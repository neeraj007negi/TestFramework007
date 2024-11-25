package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.ui.constants.Environment;

public class PropertiesUtility {

	public static String ReadPropertyFile(Environment env,String propertyname)
	{
		File file = new File(System.getProperty("user.dir")+"//config//"+env+".properties");
		Properties pr;
		FileReader reader;
		String value=null;
		try {
			reader = new FileReader(file);
			pr = new Properties();
			pr.load(reader);
			value= pr.getProperty(propertyname);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Issue in Reading Properties file");
			e.printStackTrace();
		}
		
		return value;

	}
}
