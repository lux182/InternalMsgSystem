package com.msg.utils.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyy-MM-dd");
	public static String getY_M_D(Date date){
		return sFormat.format(date);
	}
}
