package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random randnum=new Random();
		int randomNumber=randnum.nextInt(5000);
		
		return randomNumber;
	}
	
	public String getSyatemDAteYYYYMMDD() {
		Date dateobj=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
		String date=sdf.format(dateobj);
		return date;
		
	}

}
