package projectTwo;

import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

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
      commitContents += String.format("message %s\n", message);
    }
  }

  public String storeObject(){
    return storeObjectData(commitContents.getBytes());
  }

  public static HashMap<String, String> parseCommit(File commit){
    HashMap<String, String> parsedCommit = new HashMap<>();

    parsedCommit.put("tree", null);
    parsedCommit.put("parent", null);
    parsedCommit.put("author", null);
    parsedCommit.put("message", null);

    try{
      Scanner scanner = new Scanner(commit);
      
      while (scanner.hasNextLine()){
        String line = scanner.nextLine();
        int spaceIndex = line.indexOf(" ");
        if (spaceIndex == -1){ continue; }
        String label = line.substring(0, spaceIndex);
        String contents = line.substring(spaceIndex + 1);

        parsedCommit.put(label, contents);
      }
    }
    catch (FileNotFoundException exception){
      exception.printStackTrace();
    }

    return parsedCommit;
  }
}
