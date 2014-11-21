package com.msg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.msg.utils.SystemMessage.Hint;

public class DateFormat {
	
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyy-MM-dd mm:hh");
	
	public static String getStr(Date date){
		return sFormat.format(date);
	}
	public static Date getDate(String str){
		try {
			return sFormat.parse(str);
		} catch (ParseException e) {
			throw new NormalException(Hint.DATE_FORMAT_ERROR);
		}
	}
}
