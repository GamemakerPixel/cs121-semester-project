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

  public void storeObject(){
    storeObjectData(fileContents);
  }


}
