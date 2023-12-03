package projectTwo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class VCObject{
  public static final boolean COMPRESS_OBJECTS = false;

  private static MessageDigest shaDigest;

  public abstract void storeObject();

  /*
  public static String readObject(String objectHash){
    
  }*/

  // objectString is the data contained in this VCObject
  protected static void storeObjectData(byte[] objectData){
    initializeDigest();

    String objectsPath = VersionControl.getObjectsPath();

    if (objectsPath == null){ return; }

    byte[] hashedObject = shaDigest.digest(objectData);

    System.out.println(new String(hashedObject));
  }

  private static void initializeDigest(){
    if (shaDigest != null){ return; }

    try{
      shaDigest = MessageDigest.getInstance("SHA-1");
    }
    catch (NoSuchAlgorithmException exception){
      exception.printStackTrace();
    }
  }

}
