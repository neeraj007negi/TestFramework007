package com.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ReadExcelUtility 
{
	public static Iterator<User> ReadUserData(String filename) 
	{
		// TODO Auto-generated method stub
		File file = new File(System.getProperty("user.dir")+"//TestData//"+filename+".xlsx");
		XSSFWorkbook xssfworkbook;
		XSSFSheet xssfsheet;
		Row row ;
		Cell firstcell ;
		Cell secondcell ;
		User user ;
		List<User> Userlist = null;
		Iterator<Row> rowiterator;
		try {
			xssfworkbook = new XSSFWorkbook(file);
			xssfsheet = xssfworkbook.getSheet("login");
			Userlist = new ArrayList<User>();
			rowiterator= xssfsheet.iterator();
			rowiterator.next();
			while(rowiterator.hasNext())
			{
				row = rowiterator.next();
				firstcell = row.getCell(0);
				secondcell = row.getCell(1);
				user= new User(firstcell.toString(),secondcell.toString());
				Userlist.add(user);
			
			}

			xssfworkbook.close();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Userlist.iterator();
	}

}
