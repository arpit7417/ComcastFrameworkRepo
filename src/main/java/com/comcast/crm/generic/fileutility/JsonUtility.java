package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	
	public String getDataFroamJson(String key) throws IOException, ParseException {
		
		FileReader fileR = new FileReader("./configAppData/appcommondata.json");
		
		JSONParser parser= new JSONParser();
		Object obj=parser.parse(fileR);
		JSONObject map= (JSONObject)obj;
		
		String data=map.get(key).toString();
		
		return data;
		
		
		
		
		
		
		
	}

}
