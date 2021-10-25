package com.zhidisoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormatDate {
	/**
	 * ??????????????????
	 * @param date ???????
	 * @return ????????????????
	 */
	public static String format(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	/**
	 * ?????????????????????????????
	 * @param executeTime ??????
	 * @param recordDate ??????
	 * @return ??????????
	 */
	public static String getTimeOut(String executeTime,String recordDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long out  = 0;
		try {
			if(recordDate!=null&&executeTime!=null){
				out = sdf.parse(recordDate).getTime() - sdf.parse(executeTime).getTime();
				return String.valueOf((out/1000/60/60/24));
			}				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
