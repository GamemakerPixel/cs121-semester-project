package projectTwo;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class Blob extends VCObject{
  byte[] fileContents;

  public Blob(byte[] fileContents){
    this.fileContents = fileContents;
  }

  public Blob(File file) throws IOException{
    this.fileContents = Files.readAllBytes(file.toPath());
  }

  public String storeObject(){
    return storeObjectData(fileContents);
  }

  public static byte[] parseBlob(File blob){
    try{
      return Files.readAllBytes(blob.toPath());
    }
    catch (IOException exception){
      exception.printStackTrace();
      return new byte[0];
    }
  }

}
