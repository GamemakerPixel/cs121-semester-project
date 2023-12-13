package projectTwo;

import java.io.IOException;

public class Commit extends VCObject{
  private String commitContents = "";

  public Commit(String treeHash, String parentHash,
      String author, String message) throws IOException
  {
    if (!VersionControl.objectExists(treeHash)){
      throw new IOException();
    }
    commitContents += String.format("tree %s\n", treeHash);
    if (parentHash != null){
      if (!VersionControl.objectExists(parentHash)){
        throw new IOException();
      }
      commitContents += String.format("parent %s\n", parentHash);
    }
    if (author != null){
      commitContents += String.format("author %s\n", author);
    }
    if (message != null){
      message += String.format("message %s\n", message);
    }
  }

  public String storeObject(){
    return storeObjectData(commitContents.getBytes());
  }
}
