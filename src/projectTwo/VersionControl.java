package projectTwo;

import java.util.HashMap;
import java.io.File;

// Inspired by git

public class VersionControl{
  public static final String REPOSITORY_DIRECTORY_NAME = "repository-dir";
  public static final String OBJECT_DIRECTORY_NAME = "objects";

  private static final HashMap<String, Runnable> commands = new HashMap<>();

  private static String workingPath;
  private static String repoPath;

  private static String objectsPath;

  private static void selfInitialize(){
    commands.put("help", () -> { help(); });
    commands.put("init", () -> { init(); });

    setDirectoryPaths();
  }

  public static String getWorkingPath(){
    return workingPath;
  }

  public static String getRepoPath(){
    return repoPath;
  }

  public static void main(String[] args) {
    selfInitialize();

    if (args.length == 0){
      commands.get("help").run();
      return;
    }

    Runnable commandRunnable = commands.get(args[0]);

    if (commandRunnable == null){
      commands.get("help").run();
      return;
    }

    commandRunnable.run();
  }

  private static void help(){
    System.out.println("Possible Commands: \n" + 
        "help : Displays this dialog.\n"
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
