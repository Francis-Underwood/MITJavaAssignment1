package humanres;

import java.io.*;
import java.util.*;

class CifesObject {
   
  Object ob = null;

   public Object ppSerial(String f) {

     try {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ob = (Object)ois.readObject();
        ois.close();
	  fis.close();
     }  catch(Exception e) {e.printStackTrace();}
     return ob;   
   }

   public void ppSerial(Object o, String f) {
     try {
       FileOutputStream fos = new FileOutputStream(f);
       ObjectOutputStream oos = new ObjectOutputStream(fos);
       oos.writeObject(o);
       oos.flush();
       oos.close();
	 fos.close();
     } catch(Exception e ){e.printStackTrace();}
   }

}

