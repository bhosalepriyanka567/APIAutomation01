package api.com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.json.simple.parser.ParseException;

public class Helper {

	public static final String commonFilePath = System.getProperty("user.dir") + "/common.properties"; 
	
	public static String propertyReader(String key)
	{
		String value=null;
		try(InputStream input = new FileInputStream(commonFilePath)) {
			Properties prop = new Properties();
			
			//load properties file
			prop.load(input);
			
			//getProperty will fetch the value according to the key
			value = prop.getProperty(key);
			
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return value;
	}
	public static String getUri() throws IOException, ParseException
	{
		//create uri
		return Helper.propertyReader("qaBaseUrl") + ReadTestData.getTestData("endpointGetUsers");
	}
	/* 
	 * Create Folder
	 */
	public static void CreateFolder(String path) {
		//File is a class inside java.io package
		File file = new File(path);
		if(!file.exists()) {
			file.mkdir(); //mkdir is used to create folder
		}
		else
			System.out.println("Folder already created");
	}
	 /*
	  * Return current time stamp
	  */
	public static String Timestamp()
	{
		Date now = new Date();
		String Timestamp = now.toString().replace(":","-");
		return Timestamp;
	}
}
