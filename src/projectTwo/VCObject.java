package projectTwo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
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
  protected static boolean storeObjectData(byte[] objectData){
    initializeDigest();

    String objectsPath = VersionControl.getObjectsPath();

    if (objectsPath == null){ 
      System.out.println("Repository not yet initialized!");
      return false;
    }

    byte[] hashedObject = shaDigest.digest(objectData);

    String hexString = bytesToHexString(hashedObject);

    if (objectPreviouslyStored(objectsPath, hexString)){ return false; }

    // Technically, separating these objects with directories is unnessesary since this program is
    // likely only going to be run on ext4, btrfs, ntfs, and apfs, as opposed to fat-32, which has a
    // relatively small limit on how many files can be in a directory.
    File directory = new File(objectsPath, hexString.substring(0, 2));
    directory.mkdir();

    File objectFile = new File(directory, hexString.substring(2));

    try{
      FileOutputStream objectOutputStream = new FileOutputStream(objectFile);

      objectOutputStream.write(objectData);
      objectOutputStream.close();
    }
    catch (FileNotFoundException exception){
      exception.printStackTrace();
    }
    catch (IOException exception){
      exception.printStackTrace();
    }

    return true;
  }

  protected static boolean objectPreviouslyStored(String objectsPath, String objectHash){
    File directory = new File(objectsPath, objectHash.substring(0, 2));

    File objectFile = new File(directory, objectHash.substring(2));

    return objectFile.exists();
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
