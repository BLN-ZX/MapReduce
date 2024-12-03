package my.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


public class PropertyUtil {


	static String propertyPathString = "main.properties";
	
	public static Properties properties = new Properties();
	
	static{
		try {
			InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyPathString);
			properties.load(inStream);
			inStream.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static String doChinese(String chinese){
    	try  
        {  
            chinese = new String(chinese.getBytes("ISO-8859-1"), "GBK"); 
        }  
        catch (UnsupportedEncodingException e)  
        {  
            e.printStackTrace();  
        }
    	return chinese;
    }
    

    public static String getKeyValue(String key) {
        return properties.getProperty(key);
    }  


    public static String getKeyValue(String key, String defaultValue) {
    	String strValue = getKeyValue(key);
    	if (strValue!=null && strValue!=""){
    		return strValue;
    	}
    	else{
    		return defaultValue;
    	}
    }  

    public static long getKeyLongValue(String key, long defaultValue) {
    	String strValue = getKeyValue(key);
    	if (strValue!=null && strValue!=""){
	    	long lValue = defaultValue;
	    	try{
	    		lValue = Integer.parseInt(strValue);
	    		return lValue;
	    	}catch(Exception e){
	    		//e.toString();
	    		return defaultValue;
	    	}
    	}
        return defaultValue;
    }  
  

    public static String readValue(String filePath, String key) {   
        Properties props = new Properties();   
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(   
                    filePath));   
            props.load(in);   
            String value = props.getProperty(key);   
            System.out.println(key +"键的值是："+ value);  
            return value;   
        } catch (Exception e) {   
            e.printStackTrace();   
            return null;   
        }   
    }   
       
    public static void writeProperties(String keyname, String keyvalue) {
        try {
            
            OutputStream fos = new FileOutputStream(propertyPathString);   
            properties.setProperty(keyname, keyvalue);   
       
            properties.store(fos, "Update '" + keyname + "' value");   
        } catch (IOException e) {   
            System.err.println("属性文件更新错误");   
        }
    }
     
    public void updateProperties(String keyname, String keyvalue) {   
        try {   
            properties.load(new FileInputStream(propertyPathString));   
            OutputStream fos = new FileOutputStream(propertyPathString);               
            properties.setProperty(keyname, keyvalue);   
            properties.store(fos, "Update '" + keyname + "' value");   
        } catch (IOException e) {   
            System.err.println("属性文件更新错误");   
        }
    }

    public static void main(String[] args) {   
        readValue("mail.properties", "MAIL_SERVER_PASSWORD");   
        writeProperties("MAIL_SERVER_INCOMING", "327@qq.com");          
        System.out.println("操作完成");   
    }
}
