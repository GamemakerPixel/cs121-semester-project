package weekTwo.wordGameActivity;

import java.util.HashMap;
import java.util.Scanner;

public class WordGame {
    enum Prompt
    {
        NAME,
        AGE,
        CITY,
        COLLEGE,
        MAJOR,
        PET_TYPE,
        PET_NAME
    }

    private static final String[] prompts = {
            "What is your name?",
            "How old are you?",
            "What city did you live in before you came to college?",
            "What college did you go to?",
            "What did you major in?",
            "What type of pet do you have?",
            "What is your pet's name?"
    };

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] promptResponses = collectPromptResponses();
        HashMap<Prompt, String> promptResponseMap = mapPromptResponses(promptResponses);

        System.out.printf
                (
                        "There once was a person named %s who lived in %s.\n" +
                                "At the age of %s, %s went to college at %s.\n" +
                                "%s graduated with a %s degree. Then, %s adopted a(n)\n" +
                                "%s named %s. They both lived happily ever\n" +
                                "after!",
                        promptResponseMap.get(Prompt.NAME),
                        promptResponseMap.get(Prompt.CITY),
                        promptResponseMap.get(Prompt.AGE),
                        promptResponseMap.get(Prompt.NAME),
                        promptResponseMap.get(Prompt.COLLEGE),
                        promptResponseMap.get(Prompt.NAME),
                        promptResponseMap.get(Prompt.MAJOR),
                        promptResponseMap.get(Prompt.NAME),
                        promptResponseMap.get(Prompt.PET_TYPE),
                        promptResponseMap.get(Prompt.PET_NAME)
                );
    }

    private static String[] collectPromptResponses(){
        String[] responses = new String[prompts.length];

        for (int promptIndex = 0; promptIndex < prompts.length; promptIndex++){
            System.out.println(prompts[promptIndex]);
            responses[promptIndex] = scanner.nextLine();
        }

        return responses;
    }

    private static HashMap<Prompt, String> mapPromptResponses(String[] responses){
        HashMap<Prompt, String> responseMap = new HashMap<>(prompts.length);

        responseMap.put(Prompt.NAME, responses[0]);
        responseMap.put(Prompt.AGE, responses[1]);
        responseMap.put(Prompt.CITY, responses[2]);
        responseMap.put(Prompt.COLLEGE, responses[3]);
        responseMap.put(Prompt.MAJOR, responses[4]);
        responseMap.put(Prompt.PET_TYPE, responses[5]);
        responseMap.put(Prompt.PET_NAME, responses[6]);

        return responseMap;
    }
}
