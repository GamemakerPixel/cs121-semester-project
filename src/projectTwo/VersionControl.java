package projectTwo;

import java.util.HashMap;
import java.util.Arrays;
import java.util.function.Consumer;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileNotFoundException;


// Inspired by git

public class VersionControl{
  public static final String REPOSITORY_DIRECTORY_NAME = "repository-dir";
  public static final String OBJECT_DIRECTORY_NAME = "objects";

  public static final String[] PREINITIALIZE_COMMANDS = {
    "help",
    "init",
  };

  private static final HashMap<String, Consumer<String[]>> commands = new HashMap<>();

  // Used exclusively for the wrapper script to use it's own seperate directory.
  private static String workingPathOverride;

  private static String workingPath;
  private static String repoPath;

  private static String objectsPath;

  private static void selfInitialize(){
    commands.put("help", (String[] args) -> { help(); });
    commands.put("init", (String[] args) -> { init(); });
    commands.put("commit-all", (String[] args) -> { commitAll(args); });
    commands.put("cat-object", (String[] args) -> { catObject(args); });
    commands.put("store-all", (String[] args) -> { storeAll(); });
    commands.put("store-blob", (String[] args) -> { storeBlob(args); });
    commands.put("store-tree", (String[] args) -> { storeTree(args); });
    commands.put("create-branch", (String[] args) -> { createBranch(args); });
    commands.put("set-branch", (String[] args) -> { setBranch(args); });
    commands.put("restore-commit", (String[] args) -> { restoreCommit(args); });
    commands.put("restore-last-commit", (String[] args) -> { restoreLastCommit(); });

    setDirectoryPaths();
  }

  static void overrideWorkingPath(String overridePath){
    workingPathOverride = overridePath;
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

    boolean preinitializeAllowed = false;
    for (String preinitializeCommand: PREINITIALIZE_COMMANDS){
      if (args[0].equals(preinitializeCommand)){
        // Legal to call before initialization
        preinitializeAllowed = true;
        break;
      }
    }

    if (!preinitializeAllowed){
      try{
        if (!validateAllDirectories()){
          System.out.println("Must initialize a repository here first!");
          return;
        }
      }
      catch (IllegalArgumentException exception){
        System.out.println("Cannot work on corrupt repository!");
        return;
      }
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
        "commit-all [author] [message] : Stores the working directory and then references it in a commit.\n" +
        "create-branch <branch-name> : Creates a new branch, and switches to it.\n" +
        "set-branch <branch-name> : Switches to a different branch.\n" +
        "restore-last-commit : Restores the latest commit made to this branch.\n" +
        "cat-object <object-hash> : Prints the contents of an existing VCObject.\n" +
        "restore-commit <commit-hash> : Restores a commit, specified by hash.\n" +
        "store-all : Stores the working directory as a tree.\n" +
        "store-blob <file path> : Stores a file's contents as an object.\n" +
        "store-tree <directory path> : Stores a directory and pointers to it's children in an object.\n"
        );
  }

  private static void init(){
    try{
      if (validateAllDirectories()){
        System.out.println("Already initialized.");
        return;
      }
    }
    catch (IllegalArgumentException exception){
      System.out.println("Already initialized, but branch data is corrupt.");
    }

    System.out.printf("Initializing VC in directory %s\n", workingPath);

    File repoDirectory = new File(repoPath);
    File objectDirectory = new File(objectsPath);

    repoDirectory.mkdir();
    objectDirectory.mkdir();

    Branches.init();
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

  private static void commitAll(String[] args){
    String author = (args.length >= 1) ? args[0] : null;
    String message = (args.length >= 2) ? args[1] : null;

    try{
      File workingDirectory = new File(workingPath);
      Tree workingTree = new Tree(workingDirectory);
      String workingTreeHash = workingTree.storeObject();

      Commit commit = new Commit(workingTreeHash, Branches.getLastCommitHash(), 
          author, message);
      String commitHash = commit.storeObject();

      Branches.storeNewCommit(commitHash);

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

  private static void createBranch(String[] args){
    if (args.length == 0) {
      System.out.println("Must provide a branch name.");
      return;
    }

    try{
      Branches.createBranch(args[0]);
    }
    catch (IllegalArgumentException exception){
      System.out.println("Name cannot be whitespace or empty.");
      return;
    }
    catch (IOException exception){
      System.out.printf("Branch %s already exists!", args[0]);
      return;
    }

    System.out.println("Branch created successfully.");
  }

  private static void setBranch(String[] args){
    if (args.length == 0){
      System.out.println("Must provide a branch name.");
      return;
    }

    try{
      Branches.switchBranch(args[0]);
    }
    catch (IOException exception){
      System.out.println("Non-existant branch!");
      return;
    }

    restoreCommitFromHash(Branches.getLastCommitHash());

    System.out.println("Set branch successfully and loaded last commit.");
  }

  private static void restoreLastCommit(){
    String lastCommitHash = Branches.getLastCommitHash();

    if (lastCommitHash == null){
      System.out.println("No commit history yet!");
      return;
    }

    restoreCommitFromHash(lastCommitHash);

    System.out.println("Restored the last commit to this branch.");
  }

  private static void restoreCommit(String[] args){
    if (args.length == 0){
      System.out.println("Must provide commit hash.");
      return;
    }

    // Would be wise to have parsing check type, but don't have time.
    if (!objectExists(args[0])){
      System.out.println("Object does not exist.");
      return;
    }

    restoreCommitFromHash(args[0]);

    System.out.println("Restored commit.");
  }

  private static void restoreCommitFromHash(String commitHash){
    HashMap<String, String> parsedCommit = Commit.parseCommit(getObject(commitHash));

    clearWorkingDirectory();
    
    restoreTree(new File(workingPath), parsedCommit.get("tree"));
  }

  private static void restoreTree(File treeDirectory, String treeHash){
    HashMap<String, String[]> parsedTree = Tree.parseTree(getObject(treeHash));

    try{
      for (String objectHash: parsedTree.keySet()){
        String[] objectInfo = parsedTree.get(objectHash);
        if (objectInfo[1].equals("tree")){
          File newDirectory = new File(treeDirectory, objectInfo[0]);
          newDirectory.mkdir();
          restoreTree(newDirectory, objectHash);
        }
        // Blob
        else{
          File newFile = new File(treeDirectory, objectInfo[0]);
          newFile.createNewFile();
          restoreBlob(newFile, objectHash);
        }
      }
    }
    catch (IOException exception){
      exception.printStackTrace();
    }
  }

  private static void clearWorkingDirectory(){
    File workingDirectory = new File(workingPath);

    clearDirectory(workingDirectory);
  }

  private static void clearDirectory(File directory){
    File repoDirectory = new File(repoPath);

    for (File object: directory.listFiles()){
      if (object.equals(repoDirectory)){ continue; }
      if (object.isDirectory()){
        clearDirectory(object);
      }
      object.delete();
    }
  }

  private static void restoreBlob(File file, String blobHash){
    try{
      FileOutputStream outputStream = new FileOutputStream(file);

      outputStream.write(Blob.parseBlob(getObject(blobHash)));
      outputStream.close();
    }
    catch (Exception exception){
      exception.printStackTrace();
    }
  }

  private static void setDirectoryPaths() throws IllegalArgumentException{
    workingPath = System.getProperty("user.dir");
    if (workingPathOverride != null){
      workingPath = workingPathOverride;
    }
    repoPath = addToPath(workingPath, REPOSITORY_DIRECTORY_NAME);
    objectsPath = addToPath(repoPath, OBJECT_DIRECTORY_NAME);

    Branches.initializeSelf();
  }

  private static String addToPath(String parentPath, String childPath){
    File file = new File(parentPath, childPath);
    return file.getPath();
  }

  public static boolean[] validateDirectories() throws IllegalArgumentException{
    File[] theoreticalDirectories = {
      new File(workingPath),
      new File(repoPath),
    };

    return new boolean[] {
      theoreticalDirectories[0].isDirectory(),
      theoreticalDirectories[1].isDirectory(),
      Branches.validateBranchSetup(),
    };
  }

  public static boolean validateAllDirectories(){
    boolean[] directoriesExist = validateDirectories();
    return directoriesExist[0] && directoriesExist[1] && directoriesExist[2];
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
