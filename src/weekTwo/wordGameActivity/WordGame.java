package weekTwo.wordGameActivity;

public class WordGame {
    private static final String[] prompts = {
            "What is your name?",
            "How old are you?",
            "What city did you live in before you came to college?",
            "What college did you go to?",
            "What did you major in?",
            "What type of pet do you have?",
            "What is your pet's name?"
    };

    private static 

    public static void main(String[] args) {

    }

    public static String[] collectPromptResponses(){
        String[] responses = new String[prompts.length];

        for (int promptIndex = 0; promptIndex < prompts.length; promptIndex++){
            System.out.println(prompts[promptIndex]);

        }
    }
}
