package projectTwo;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class Tree extends VCObject{
  private File directory;
  private String treeContents;

  public Tree(File directory) throws IOException{
    if (!directory.exists()){
      throw new IOException();
    }
    this.directory = directory;
  }

  public String storeObject(){
    storeChildren();
    //return storeObjectData(treeContents.getBytes());
    return "";
  }

  private void storeChildren(){
    String[] children = directory.list();
    for (String child: children){ System.out.println(child); }
  }
}
