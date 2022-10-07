package api.com.util;

import java.security.SecureRandom;

public class RandomUtils {
	public static SecureRandom random = new SecureRandom();
	
	public static String generateRandomString(int length)
	{
		//String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String text = "AaBbCc123415";
		StringBuilder sb = new StringBuilder(length);
		for(int i=0; i<length; i++)
			sb.append(text.charAt(random.nextInt(text.length())));
		return sb.toString();
	}
	
	public static String generateRandomNumberString(int length)
	{
		String textNumber = "0123456789";
		StringBuilder sb = new StringBuilder(length);
		for(int i=0; i<length; i++)
			sb.append(textNumber.charAt(random.nextInt(textNumber.length())));
		return sb.toString();
	}
	
	//Use Random method for integer data
}
