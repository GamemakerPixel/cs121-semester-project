package projectTwo;

import java.io.File;
import java.util.Scanner;

public class VersionControlWrapper{
  public static final String WORKING_DIRECTORY_NAME = "Working-Directory";

  public static void main(String[] args) {
    System.out.println("Hello! This is a program designed to help you test my project, " +
        "VersionControl.java. This program will create a directory in its parent folder where " +
        "you can experiment with the program. It is strongly advised to have a file explorer " +
        "open to this new directory, and make sure it is refereshed after you run a command. " +
        "Feel free to add whatever files and directories you choose! " +
        "I will warn you, the \"store-\" commands are intended to be used for experimentation, " +
        "and the objects created by them are not restoreable without the use of commit-all. " +
        "Only commits can be restored.");
    System.out.println("\nWith that out of the way, you can access the help menu through VersionControl " +
        "by typing \"help\", and you can exit this wrapper program by typing \"exit\". Feel free to explore :). " +
        "Any input (other than exit) will now be passed straight into the main() method of VersionControl.java, " +
        "with the working path being overriden to be the newly created \"Working-Directory\".");

    File workingDirectory = new File(System.getProperty("user.dir"));
    workingDirectory = new File(workingDirectory.getParent(), WORKING_DIRECTORY_NAME);

    workingDirectory.mkdir();

    VersionControl.overrideWorkingPath(workingDirectory.getPath());

    System.out.println("Your working directory: " + workingDirectory.getPath());

    Scanner scanner = new Scanner(System.in);

    while (true){
      System.out.print("VersionControl> ");
      String nextLine = scanner.nextLine();

      if (nextLine.equals("exit")){ return; }

      String[] vcArgs = nextLine.split(" ");

      VersionControl.main(vcArgs);
    }
  }
}
