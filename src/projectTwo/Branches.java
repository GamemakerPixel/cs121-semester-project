package projectTwo;



public class Branches{
  public static final String BRANCHES_DIRECTORY_NAME = "branches";
  public static final String HEAD_FILE_NAME = "HEAD";

  private File branchesDirectory;
  private File currentBranch;
  private String currentTreeHash;
  private File headFile;

  public static void init(){
    String workingPath = VersionControl.getWorkingPath();


  }
}
