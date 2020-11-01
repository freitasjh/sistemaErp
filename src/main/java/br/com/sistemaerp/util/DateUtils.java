package br.com.sistemaerp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	
	public static Date convertStringToDate(String date) throws Exception{
		if(date != null && !date.isEmpty()) {
			return new SimpleDateFormat().parse(date);
		}
		return null;
	}
}
