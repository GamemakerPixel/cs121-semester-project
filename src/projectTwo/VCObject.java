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

    System.out.println(bytesToHexString(hashedObject));
    System.out.println("Store at " + objectsPath);
  }

  protected static String bytesToHexString(byte[] bytes){
    StringBuilder builder = new StringBuilder();

    for (byte b: bytes){
      builder.append(String.format("%02x", b));
    }

    return builder.toString();
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
