package com.app.doublecheck;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.doublecheck.data.Record;
import com.app.doublecheck.data.User;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

public class Util{
	
	public static String addressToStr(Address a){
		int length = a.getMaxAddressLineIndex();
		String str = "";
		for(int i = 0; i < length; i++){
			str += a.getAddressLine(i) + "\n";
		}
		str += a.getAddressLine(length);
		
		return str;
	}
	
	public static String addressToHtmlStr(Address a){
		int length = a.getMaxAddressLineIndex();
		String str = "";
		for(int i = 0; i < length; i++){
			str += a.getAddressLine(i) + "%20";
		}
		str += a.getAddressLine(length);
		String str2 = str.replaceAll(" ", "%20");
		//str.replaceAll("\n", "<br>");
		
		return str2;
	}
	
	public static String dateToTimeStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String d = sdf.format(date);
        return d;
	}
	
	public static String dateToDateStr(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
		String d = sdf.format(date);
        return d;
	}
	
	public static String dateToStr(Date date){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		String s1 = sdf1.format(date);
		String s2 = sdf2.format(date);
		String s = s1 + "%20" + s2;
		return s;
	}
	
	public static Date strToDate(String dateStr){
		// TODO
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy HH:mm");
			Date date = sdf.parse(dateStr);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static Date strToDateJson(String dateStr){
		// TODO
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(dateStr);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static int compareDateDay(Date oldDate, Date newDate){
		Date myOldDate = new Date(oldDate.getYear(),oldDate.getMonth()-1,oldDate.getDay());
		Date myNewDate = new Date(newDate.getYear(),newDate.getMonth()-1,newDate.getDay());
		return myOldDate.compareTo(myNewDate);
		/**
	    if(today.compareTo(myDate)<0)
	    	System.out.println("Today Date is Lesser than my Date");
	    else if(today.compareTo(myDate)>0)
	    	System.out.println("Today Date is Greater than my date"); 
	    else
	    	System.out.println("Both Dates are equal");
	    */      
	}
	
	public static com.app.doublecheck.data.Record serverRecordToClientRecord(com.iratao.thumb.client.model.Record rs){
		com.app.doublecheck.data.Record rc = new com.app.doublecheck.data.Record(rs.recordID, rs.userID, 
				rs.userName, rs.startAddress, rs.endAddress, rs.startDate, rs.endDate, rs.startLat, rs.startLon, 
				rs.endLat, rs.endLon, rs.other, rs.type, rs.category);
		return rc;
	}
	
	public static com.app.doublecheck.data.User serverUserToClientUser(com.iratao.thumb.client.model.User us){
		com.app.doublecheck.data.User uc = new com.app.doublecheck.data.User(
				us.userID, us.userName, us.phoneNumber, us.email);
		return uc;
	}
	
	public static List<Record> getSampleRecords(int start, int end){
		List<Record> records = new ArrayList<Record>();
		
		records.add(new Record(1, 1, "Dora", "Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014", "Wall St\nMiddleboro, MA 02346", new Date(2011-1900, 5-1, 26, 8, 0), new Date(2011-1900, 5-1, 26, 18, 0), 
				42.3793435424587,  -71.11664772033691, 41.903619, -70.82551, "", Constants.TYPE_NEED_RIDE, Constants.CAT_CARPOOL));
		records.add(new Record(2, 2, "Lory", "Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014", "Wall St\nMiddleboro, MA 02346", new Date(2011-1900, 5-1, 26, 8, 0), new Date(2011-1900, 5-1, 26, 18, 0), 
				42.3793435424587,  -71.11664772033691, 41.903619, -70.82551, "", Constants.TYPE_NEED_RIDE, Constants.CAT_CARPOOL));
		records.add(new Record(3, 2, "Lory", "Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014", "Wall St\nMiddleboro, MA 02346", new Date(2011-1900, 5-1, 26, 8, 0), new Date(2011-1900, 5-1, 26, 18, 0), 
				42.3793435424587,  -71.11664772033691, 41.903619, -70.82551, "", Constants.TYPE_NEED_RIDE, Constants.CAT_CARPOOL));
		records.add(new Record(4, 2, "Lory", "Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014", "Wall St\nMiddleboro, MA 02346", new Date(2011-1900, 5-1, 25, 8, 0), new Date(2011-1900, 5-1, 25, 18, 0), 
				42.3793435424587,  -71.11664772033691, 41.903619, -70.82551, "", Constants.TYPE_NEED_RIDE, Constants.CAT_CARPOOL));
		records.add(new Record(5, 2, "Lory", "Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014", "Wall St\nMiddleboro, MA 02346", new Date(2011-1900, 5-1, 25, 8, 0), new Date(2011-1900, 5-1, 25, 18, 0), 
				42.3793435424587,  -71.11664772033691, 41.903619, -70.82551, "", Constants.TYPE_NEED_RIDE, Constants.CAT_CARPOOL));
		records.add(new Record(6, 2, "Lory", "Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014", "Wall St\nMiddleboro, MA 02346", new Date(2011-1900, 5-1, 23, 8, 0), new Date(2011-1900, 5-1, 23, 18, 0), 
				42.3793435424587,  -71.11664772033691, 41.903619, -70.82551, "", Constants.TYPE_NEED_RIDE, Constants.CAT_CARPOOL));
		records.add(new Record(7, 2, "Lory", "Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014", "Wall St\nMiddleboro, MA 02346", new Date(2011-1900, 5-1, 23, 8, 0), new Date(2011-1900, 5-1, 23, 18, 0), 
				42.3793435424587,  -71.11664772033691, 41.903619, -70.82551, "", Constants.TYPE_NEED_RIDE, Constants.CAT_CARPOOL));
		records.add(new Record(8, 2, "Lory", "Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014", "Wall St\nMiddleboro, MA 02346", new Date(2011-1900, 5-1, 23, 8, 0), new Date(2011-1900, 5-1, 23, 18, 0), 
				42.3793435424587,  -71.11664772033691, 41.903619, -70.82551, "", Constants.TYPE_NEED_RIDE, Constants.CAT_CARPOOL));
		
		return records.subList(start, end);
	}
	
	public static User getSampleUser(){
		User user = new User(1, "Ira", "12312312123", "iratao@liamg.com");
		return user;
		
	}
	
	public static void log(String content){
    	FileOutputStream out;
        PrintStream prt;
        
        try {
            out = new FileOutputStream("H:\\log.txt");
            prt = new PrintStream(out);
            prt.println(content);
            prt.close();
        } catch(Exception e) {
            System.out.println("Write error");
        }
    }
	
	public static String testAddress = "University of Harvard\n" +
			"12 Holyoke Street, Cambridge, MA";
	
	

}
