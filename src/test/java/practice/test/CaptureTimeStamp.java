package practice.test;

import java.util.Date;

public class CaptureTimeStamp {
	
	public static void main(String[] args) {
		 String time = new Date().toString();
		 System.out.println(time);
		 
		 System.out.println("================");
		 
		 String time1 = new Date().toString().replace(" ", "_").replace(":","_");
		 System.out.println(time1);
		 
		 
	}

}
