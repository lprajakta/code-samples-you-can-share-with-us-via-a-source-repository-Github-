package com.itrix.utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Conversion {
	public java.sql.Date utilToSqlDate(java.util.Date util){
		
		java.sql.Date sql=new java.sql.Date(util.getTime());
		return sql;
	}
	
	public  java.util.Date sqlToUtilDate(java.sql.Date sql){
		java.util.Date util=new java.util.Date(sql.getTime());
		return util;
	}
	
	public java.util.Date sqlToUtilFormat(java.sql.Date sql, String formats)
	   {
		SimpleDateFormat sdf = new SimpleDateFormat();
		java.util.Date utidt=null;
		try{
			java.util.Date jdate=new java.util.Date(sql.getTime());
		
	        sdf.applyPattern(formats);
	        sdf.setLenient(false);
		String Format=sdf.format(jdate);
		System.out.println("from conversion"+Format);
		 utidt=sdf.parse(Format);
		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		    }
		System.out.println("before returning"+utidt.getMonth());
		return utidt; 
		
	   }
	public java.util.Date stringToDate(String stringdate){
		java.util.Date utilDate=null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			utilDate=formatter.parse(stringdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return utilDate;
	}
	public java.util.Date stringToDateFormate(String stringdate){
		java.util.Date utilDate=null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try{
			utilDate=formatter.parse(stringdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return utilDate;
	}

	public java.util.Date stringToDateFormatee(String stringdate){
		java.util.Date utilDate=null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			utilDate=formatter.parse(stringdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return utilDate;
	}
	
	public String sqlDatetoStringConvert(Date date)
	{
		String stringFormate = null;
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			stringFormate = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringFormate;
	}
	

	public java.sql.Timestamp getCurrentDateTime(){
		java.util.Date today=new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	
	
	
	
	
	
	
	
	
/*	public java.sql.Date convertStrToDate(String strDate) {
		java.sql.Date sqlDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date utilDate = sdf.parse(strDate);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (Exception e) {
			System.out
					.println("Exception Generated during conversion of String to Date in util- Coversion class");
			e.printStackTrace();
		}

		return sqlDate;
	}*/
		public static int calculateAgeFinal(String dob) {
	// TODO Auto-generated method stub
		int yearDOB = Integer.parseInt(dob.substring(0, 4));
        int monthDOB = Integer.parseInt(dob.substring(5, 7));
        int dayDOB = Integer.parseInt(dob.substring(8, 10));
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        java.util.Date date = new java.util.Date();
        int thisYear = Integer.parseInt(dateFormat.format(date));
        dateFormat = new SimpleDateFormat("MM");
        date = new java.util.Date();
        int thisMonth = Integer.parseInt(dateFormat.format(date));
        dateFormat = new SimpleDateFormat("dd");
        date = new java.util.Date();
        int thisDay = Integer.parseInt(dateFormat.format(date));
        int age = thisYear - yearDOB;
        age=age+1;
        if(thisMonth < monthDOB){
        age = age -1;
        }
        if(thisMonth == monthDOB && thisDay < dayDOB){
        age = age -1;
        }
	return age ;
}
	public Timestamp getTimestamp()
	{
			Date currentDate = new Date();
			Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
			return currentTimestamp;
	}
	public int getNameCount(String[] generateName)
	{
		int counter=0;
		for(int i=0;i<generateName.length;i++)
		{
			if (generateName[i] != null)
		        counter ++;
		
		}
		return counter;
		
	}

	
	public String convrtDtToStr(Date dt){
		SimpleDateFormat dateformatddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
		String date_to_string = dateformatddMMyyyy.format(dt);
		return date_to_string;
	}
	public int convertStrToInt(String str)
	{
		int intVal =0;
		try
		{
			intVal = Integer.parseInt(str);
		}
		catch(Exception e)
		{
			System.out.println("Exception generated during conversion of string to integer in util conversion class");
			e.printStackTrace();
		}
		return intVal ;
	}
	public java.sql.Date convertStrToDate(String strDate)
	{
		java.sql.Date sqlDate=null;
		try 
		{
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
			java.util.Date utilDate=sdf.parse(strDate);
			sqlDate=new java.sql.Date(utilDate.getTime());
		} 
		catch (Exception e)
		{
			System.out.println("Exception Generated during conversion of String to Date in util- Coversion class");
			e.printStackTrace();
		}		
		return sqlDate;
	}
	
	
	public java.util.Date convertStrToDateUtil(String strDate)
	{
		java.util.Date utilDate=null;
		try 
		{
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
			utilDate=sdf.parse(strDate);
		} 
		catch (Exception e)
		{
			System.out.println("Exception Generated during conversion of String to Date in util- Coversion class");
			e.printStackTrace();
		}		
		return utilDate;
	}
	
	
	public java.sql.Date convertStrToDateForAcct(String strDate)
	{
		java.sql.Date sqlDate=null;
		try 
		{
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date utilDate=sdf.parse(strDate);
			sqlDate=new java.sql.Date(utilDate.getTime());
		} 
		catch (Exception e)
		{
			System.out.println("Exception Generated during conversion of String to Date in util- Coversion class");
			e.printStackTrace();
		}		
		return sqlDate;
	}
	
	public Integer[] strArrayToIntArray(String[] str)
	{
		Integer[] intarray=new Integer[str.length];
		int i=0;
		try 
		{
			for(String strings:str)
			{
				intarray[i]=Integer.parseInt(strings);
				i++;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception Generated during conversion of String array to int array in util- Coversion class");
		}
		return intarray;
	}
	public double convertStrToDouble(String str)
	{
		double doubleVal=0;
		try 
		{
			doubleVal=Double.parseDouble(str);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception generated during conversion of string to double in util conversion class");
			e.printStackTrace();
		}
		return doubleVal;
	}
	
	public Timestamp subtractMonthToTimeStamp(Timestamp ts){
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		cal.add(Calendar.MONTH, -1);
		ts.setTime(cal.getTime().getTime()); // or
		ts = new Timestamp(cal.getTime().getTime());
		return ts;
	}
	
	
	public Timestamp setTimeTo0(Timestamp ts){
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
		cal.set(Calendar.MINUTE, 0);                 // set minute in hour
		cal.set(Calendar.SECOND, 0);                 // set second in minute
		cal.set(Calendar.MILLISECOND, 0);		
		ts.setTime(cal.getTime().getTime()); // or
		ts = new Timestamp(cal.getTime().getTime());
		return ts;
	}
	
	public Timestamp setTimeTo59(Timestamp ts){
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		cal.set(Calendar.HOUR_OF_DAY, 23);            // set hour to midnight
		cal.set(Calendar.MINUTE, 59);                 // set minute in hour
		cal.set(Calendar.SECOND, 59);                 // set second in minute
		cal.set(Calendar.MILLISECOND, 0);		
		ts.setTime(cal.getTime().getTime()); // or
		ts = new Timestamp(cal.getTime().getTime());
		return ts;
	}

}
