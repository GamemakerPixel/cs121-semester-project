package weekOne;

public class Aidan {
    public static void main(String[] args) {
        int age = 1;
        float gpa = 6.0f;
        char letterGrade = 'A';
        boolean csMajor = true;
        String name = "Aidan";

        System.out.printf(
                "My name is %s, I have a GPA of %.1f, and a grade of %c, " +
                        "it is %b that I am a CS major, and I am %d years old. \n",
                name, gpa, letterGrade, csMajor, age
        );
    }
}
