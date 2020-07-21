package util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static String getCurrentDate() {//날짜구하기 (public 접근제어자를 붙혀야 다른패키지에서도 접근가능)
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");				
		Date time = new Date();					
		String time1 = format1.format(time);	
		return time1;
	}
	

}
