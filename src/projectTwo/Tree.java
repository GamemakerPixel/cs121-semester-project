package projectTwo;

import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Tree extends VCObject{
  private File directory;
  private String treeContents = "";

  public Tree(File directory) throws IOException{
    if (!directory.exists()){
      throw new IOException();
    }
    this.directory = directory;
  }

  public String storeObject(){
    HashMap<String, String[]> childHashes = storeChildren();

    for (String childHash : childHashes.keySet()){
      treeContents += String.format(
          "%s %s %s\n",
          childHashes.get(childHash)[1], childHash, 
          childHashes.get(childHash)[0]
          );
    }

    return storeObjectData(treeContents.getBytes());
  }

  private HashMap<String, String[]> storeChildren(){
    String[] children = directory.list();

    // <objectHash, objectName>
    HashMap<String, String[]> childHashes = new HashMap<>();
    for (String child: children){
      if (child.equals(VersionControl.REPOSITORY_DIRECTORY_NAME)){
        continue;
      }

      File childFile = new File(directory, child);
      try{
        if (childFile.isFile()){
          Blob blob = new Blob(childFile);
          childHashes.put(blob.storeObject(), 
              new String[] {child, "blob"});
        }
        else {
          Tree tree = new Tree(childFile);
          childHashes.put(tree.storeObject(), 
              new String[] {child, "tree"});
        }
      }
      catch (IOException exception){
        exception.printStackTrace();
      }
    }

    return childHashes;
  }

  public static HashMap<String, String[]> parseTree(File tree){
    HashMap<String, String[]> parsedTree = new HashMap<>();

    try{
      Scanner scanner = new Scanner(tree);

      while(scanner.hasNextLine()){
        String line = scanner.nextLine();
        int firstSpaceIndex = line.indexOf(" ");
        int secondSpaceIndex = line.indexOf(" ", firstSpaceIndex + 1);
        String type = line.substring(0, firstSpaceIndex);
        String hash = line.substring(firstSpaceIndex + 1, secondSpaceIndex);
        String name = line.substring(secondSpaceIndex + 1);

        parsedTree.put(hash, new String[] {name, type});
      }
    }
    catch (FileNotFoundException exception){
      exception.printStackTrace();
    }

    return parsedTree;
  }
}
