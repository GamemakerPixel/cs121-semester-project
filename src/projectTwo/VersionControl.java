package projectTwo;

import java.util.HashMap;
import java.util.Arrays;
import java.util.function.Consumer;
import java.io.File;
import java.io.IOException;


// Inspired by git

public class VersionControl{
  public static final String REPOSITORY_DIRECTORY_NAME = "repository-dir";
  public static final String OBJECT_DIRECTORY_NAME = "objects";

  private static final HashMap<String, Consumer<String[]>> commands = new HashMap<>();

  private static String workingPath;
  private static String repoPath;

  private static String objectsPath;

  private static void selfInitialize(){
    commands.put("help", (String[] args) -> { help(); });
    commands.put("init", (String[] args) -> { init(); });
    commands.put("store-blob", (String[] args) -> { storeBlob(args); });

    setDirectoryPaths();
  }

  public static String getWorkingPath(){
    return workingPath;
  }

  public static String getRepoPath(){
    return repoPath;
  }

  public static String getObjectsPath(){
    return objectsPath;
  }

  public static void main(String[] args) {
    selfInitialize();

    if (args.length == 0){
      commands.get("help").accept(new String[0]);
      return;
    }

    Consumer<String[]> commandConsumer = commands.get(args[0]);

    if (commandConsumer == null){
      commands.get("help").accept(new String[0]);
      return;
    }

    // If no arguments for command
    if (args.length <= 1){
      commandConsumer.accept(new String[0]);
      return;
    }

    commandConsumer.accept(Arrays.copyOfRange(args, 1, args.length));
  }

  private static void help(){
    System.out.println("Possible Commands: \n" + 
        "help : Displays this dialog.\n" +
        "init : Initializes a repository in this directory.\n" +
        "store-blob <file path> : Stores a file's contents as an object.\n"
        );
  }

  private static void init(){
    if (validateBothDirectories()){
      System.out.println("Already initialized.");
      return;
    }

    System.out.printf("Initializing VC in directory %s\n", workingPath);

    File repoDirectory = new File(repoPath);
    File objectDirectory = new File(objectsPath);

    repoDirectory.mkdir();
    objectDirectory.mkdir();
  }

  private static void storeBlob(String[] args){
    String filePath;
    try{
      filePath = args[0];
    }
    catch (ArrayIndexOutOfBoundsException exception){
      System.out.println("Must provide a file path.");
      return;
    }

    File file;

    try{
      file = new File(filePath);
    }
    catch (NullPointerException exception){
      exception.printStackTrace();
      return;
    }

    try{
      Blob blob = new Blob(file);
      blob.storeObject();
    }
    catch (IOException exception){
      System.out.println("Invallid file path: " + filePath);
    }
    
  }

  private static void setDirectoryPaths(){
    workingPath = System.getProperty("user.dir");
    repoPath = addToPath(workingPath, REPOSITORY_DIRECTORY_NAME);
    objectsPath = addToPath(repoPath, OBJECT_DIRECTORY_NAME);
  }

  private static String addToPath(String parentPath, String childPath){
    File file = new File(parentPath, childPath);
    return file.getPath();
  }

  public static boolean[] validateDirectories(){
    File[] theoreticalDirectories = {
      new File(workingPath),
      new File(repoPath),
    };

    return new boolean[] {
      theoreticalDirectories[0].isDirectory(),
      theoreticalDirectories[1].isDirectory(),
    };
  }

  public static boolean validateBothDirectories(){
    boolean[] directoriesExist = validateDirectories();
    return directoriesExist[0] && directoriesExist[1];
  }
}
