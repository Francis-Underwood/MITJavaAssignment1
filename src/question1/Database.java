package question1;

import java.io.*;

public class Database {
	public Object loadDatabase(String filePath) {
		Object ob = null;
		try {
	        FileInputStream fis = new FileInputStream(filePath);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        ob = (Object)ois.readObject();
	        ois.close();
	        fis.close();
	     }  
		catch(Exception e) {
			e.printStackTrace();
		}
	    return ob;  
	}
	public void saveDatabase(Object object, String filePath) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.flush();
			oos.close();
			fos.close();
		} 
		catch(Exception e ) {
			e.printStackTrace();
		}
	}
}
