package projectTwo;

import java.util.HashMap;
import java.util.Arrays;
import java.util.function.Consumer;
import java.io.File;
import java.nio.file.Files;
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
    commands.put("commit-all", (String[] args) -> { commitAll(); });
    commands.put("cat-object", (String[] args) -> { catObject(args); });
    commands.put("store-all", (String[] args) -> { storeAll(); });
    commands.put("store-blob", (String[] args) -> { storeBlob(args); });
    commands.put("store-tree", (String[] args) -> { storeTree(args); });
    commands.put("snapshot-repository", (String[] args) -> { snapshotRepository(); });

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
        "commit-all : Stores the working directory and then references it in a commit.\n" +
        "cat-object <object-hash>: Prints the contents of an existing VCObject.\n" +
        "store-all : Stores the working directory as a tree.\n" +
        "store-blob <file path> : Stores a file's contents as an object.\n" +
        "store-tree <directory path> : Stores a directory and pointers to it's children in an object.\n"
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

  private static void snapshotRepository(){
    storeTree(new String[] {workingPath});
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

    if (file.isDirectory()){
      System.out.println(filePath + " is a directory, must be stored as a tree.");
      return;
    }

    try{
      Blob blob = new Blob(file);
      blob.storeObject();
    }
    catch (IOException exception){
      System.out.println("Invallid file path: " + filePath);
    }

    System.out.printf("Stored %s as a blob.", file.getName());
  }

  private static void storeTree(String[] args){
    String directoryPath;
    try{
      directoryPath = args[0];
    }
    catch (ArrayIndexOutOfBoundsException exception){
      System.out.println("Must provide a directory path.");
      return;
    }

    File directory;

    try{
      directory = new File(directoryPath);
    }
    catch (NullPointerException exception){
      exception.printStackTrace();
      return;
    }

    if (directory.isFile()){
      System.out.println(directoryPath + " is a file, must be stored as a blob.");
      return;
    }

    try{
      Tree tree = new Tree(directory);
      tree.storeObject();
    }
    catch (IOException exception){
      System.out.println("Invallid directory path: " + directoryPath);
    }

    System.out.printf("Stored %s as a tree.", directory.getName());
  }

  private static void storeAll(){
    try{
      File workingDirectory = new File(workingPath);
      Tree workingTree = new Tree(workingDirectory);
      workingTree.storeObject();
    }
    catch (IOException exception){
      exception.printStackTrace();
    }

    System.out.println("Stored the working directory as a tree.");
  }

  private static void commitAll(){
    try{
      File workingDirectory = new File(workingPath);
      Tree workingTree = new Tree(workingDirectory);
      String workingTreeHash = workingTree.storeObject();

      Commit commit = new Commit(workingTreeHash, null, "AuthorName", "MessageText");
      commit.storeObject();
    }
    catch (IOException exception){
      exception.printStackTrace();
    }

    System.out.println("Stored the working directory, and commited all changes.");
  }

  private static void catObject(String[] args){
    if (args.length < 1){
      System.out.println("Must provide an object's hash.");
      return;
    }

    if (!objectExists(args[0])){
      System.out.println("Nonexistant object.");
      return;
    }

    File object = getObject(args[0]);

    try{
      byte[] fileContents = Files.readAllBytes(object.toPath());
      System.out.println(new String(fileContents));
    }
    catch (IOException exception){
      exception.printStackTrace();
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

  public static boolean objectExists(String objectHash){
    return getObject(objectHash).exists();
  }

  public static File getObject(String objectHash){
    File objectFile = new File(objectsPath, objectHash.substring(0, 2));
    objectFile = new File(objectFile, objectHash.substring(2));
    return objectFile;
  }
}
