package projectTwo;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Branches{
  public static final String BRANCHES_DIRECTORY_NAME = "branches";
  public static final String HEAD_FILE_NAME = "HEAD";
  public static final String INITIAL_BRANCH_NAME = "master";

  private static File branchesDirectory;
  private static File currentBranch;
  private static String currentCommitHash;
  private static File headFile;

  public static boolean initializeSelf() throws IllegalArgumentException{
    String repositoryPath = VersionControl.getRepoPath();

    branchesDirectory = new File(repositoryPath, BRANCHES_DIRECTORY_NAME);
    headFile = new File(repositoryPath, HEAD_FILE_NAME);

    if (!validateBranchSetup()){
      // Not initialized.
      // If IllegalArgumentException thrown, repository is corrupt.
      return false;
    }

    try{
      Scanner headScanner = new Scanner(headFile);

      currentBranch = getBranchFile(headScanner.nextLine());

      Scanner branchScanner = new Scanner(currentBranch);

      String commitHash;
      if (branchScanner.hasNextLine()){
        commitHash = branchScanner.nextLine();
      }
      else{
        commitHash = "";
      }

      if (!commitHash.isEmpty()){
        currentCommitHash = commitHash;
      }
      else{
        // No history yet.
        currentCommitHash = null;
      }
    }
    catch (FileNotFoundException exception){
      exception.printStackTrace();
    }

    return true;
  }

  public static boolean validateBranchSetup() throws IllegalArgumentException{
    if (!branchesDirectory.exists() || !headFile.exists()){ return false; }

    try{
      Scanner headScanner = new Scanner(headFile);
      String headBranch = headScanner.nextLine();
      if (headBranch.isEmpty()){
        // If head exists, should always have at least the initial branch created.
        throw new IllegalArgumentException();
      }

      File branchFile = getBranchFile(headBranch);

      if (!branchFile.exists()){
        // If branch is referenced in head, should always exist.
        throw new IllegalArgumentException();
      }

      Scanner branchScanner = new Scanner(branchFile);

      String commitHash;
      if (!branchScanner.hasNextLine()){
        commitHash = "";
      }
      else{
        commitHash = branchScanner.nextLine();
      }

      // If a branch file is empty, it has no commit history, and is still allowed.
      if (!commitHash.isEmpty() && !VersionControl.objectExists(commitHash)){
        // However, if it references a non-existant commit, it is corrupt.
        throw new IllegalArgumentException();
      }
    }
    catch (FileNotFoundException exception){
      exception.printStackTrace();
    }

    return true;
  }

  public static void init(){
    String repositoryPath = VersionControl.getRepoPath();

    branchesDirectory = new File(repositoryPath, BRANCHES_DIRECTORY_NAME);
    headFile = new File(repositoryPath, HEAD_FILE_NAME);

    try{
      branchesDirectory.mkdir();
      headFile.createNewFile();
    }
    catch (Exception exception){
      exception.printStackTrace();
    }

    try{
      createBranch(INITIAL_BRANCH_NAME);
    }
    catch (Exception exception){
      exception.printStackTrace();
    }
    currentCommitHash = null; // Repository just initialized, we haven't saved anything yet.
  }

  public static void createBranch(String name)
      throws IllegalArgumentException, IOException{
    if (name.isBlank()){
      throw new IllegalArgumentException();
    }
    if (branchExists(name)){
      throw new IOException();
    }

    File branchFile = getBranchFile(name);
    branchFile.createNewFile();
    currentBranch = branchFile;
    // Will be empty until we have made our first commit.
    
    if (currentCommitHash != null){
      try{
        FileWriter branchWriter = new FileWriter(branchFile);
        branchWriter.write(currentCommitHash);
        branchWriter.close();
      }
      catch (IOException exception){
        exception.printStackTrace();
      }
    }
    
    switchBranch(name);
  }

  public static void switchBranch(String name) throws IOException{
    if (!branchExists(name)){
      throw new IOException();
    }

    try {
      FileWriter headWriter = new FileWriter(headFile);
      headWriter.write(name + "\n");
      headWriter.close();
    }
    catch (IOException exception){
      exception.printStackTrace();
    }

    currentBranch = getBranchFile(name);

    try {
      Scanner currentBranchScanner = new Scanner(currentBranch);

      if (currentBranchScanner.hasNextLine()){
        currentCommitHash = currentBranchScanner.nextLine();
      }
      else{
        currentCommitHash = null;
      }
    }
    catch (FileNotFoundException exception){
      exception.printStackTrace();
    }
  }

  public static void storeNewCommit(String commitHash){
    currentCommitHash = commitHash;

    try{
      FileWriter branchWriter = new FileWriter(currentBranch);
      branchWriter.write(commitHash);
      branchWriter.close();
    }
    catch (IOException exception){
      exception.printStackTrace();
    }
  }

  public static String getLastCommitHash(){
    return currentCommitHash;
  }

  private static boolean branchExists(String name){
    return getBranchFile(name).exists();
  }

  private static File getBranchFile(String name){
    return new File(branchesDirectory, name);
  }
}
