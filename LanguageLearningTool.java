import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
//import greenfoot.*;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class LanguageLearningTool {

    // A hashmap to store the vocabulary (English word and its Turkish translation)
    private static HashMap<String, String> vocabulary = new HashMap<>();

    // A custom queue to hold questions (English words) to be asked in the quiz
    private static CustomQueue<String> questionQueue = new CustomQueue<>();

    // A custom stack to hold incorrectly answered questions for review later
    private static CustomStack<String> incorrectStack = new CustomStack<>();

    // Counters for correct answers and total questions asked
    private static int correctAnswers = 0;
    private static int totalQuestions = 0;

    public static void main(String[] args) {
        initializeVocabulary();
        populateQuestionQueue();
        runQuiz();
        displayResults();
        reviewIncorrectAnswers();
            Scanner scanner = new Scanner(System.in);
    
            // Display welcome message
            System.out.println("Welcome to your Turkish Study Guide!");
            System.out.println("Would you like to begin? (Type 'Yes' to start)");
    
            // Get user input
            String response = scanner.nextLine();
    
            // Check user response
            if (response.equalsIgnoreCase("Yes")) {
                System.out.println("Great! Let's get started!");
                // Add more functionality here as needed
            } else {
                System.out.println("Alright, see you next time!");
            }
    
            // Close the scanner
            scanner.close();
        
    }

    private static void initializeVocabulary() {
        // Adding Turkish greetings to the vocabulary
        vocabulary.put("Hello", "Merhaba");
        vocabulary.put("Good Morning", "Günaydın");
        vocabulary.put("Good Night", "İyi Geceler");
        vocabulary.put("Goodbye", "Hoşça kal");
        vocabulary.put("Thank You", "Teşekkür ederim");
    }

    private static void populateQuestionQueue() {
        for (String englishWord : vocabulary.keySet()) {
            questionQueue.enqueue(englishWord);
        }
    }

    private static void runQuiz() {
        Scanner scanner = new Scanner(System.in);
    
        // Display accented character assistance
        System.out.println("Note: You can copy these accented characters for your answers if needed:");
        System.out.println("Available accents: ü, ç, ş, ğ, ı, ö, İ");
        System.out.println();
    
        while (!questionQueue.isEmpty()) {
            String englishWord = questionQueue.dequeue();
            System.out.println("What is the Turkish translation for: " + englishWord + "?");
            String userAnswer = scanner.nextLine().trim();
    
            if (vocabulary.get(englishWord).equalsIgnoreCase(userAnswer)) {
                System.out.println("Correct!");
                correctAnswers++;
    
                // Play the corresponding sound
                if (englishWord.equalsIgnoreCase("Hello")) {
                    SoundPlayer.playSound("sounds/merhaba.wav");
                } else if (englishWord.equalsIgnoreCase("Good Morning")) {
                    SoundPlayer.playSound("sounds/gunaydin.wav");
                } else if (englishWord.equalsIgnoreCase("Good Night")) {
                    SoundPlayer.playSound("sounds/iyigecelar.wav");
                } else if (englishWord.equalsIgnoreCase("Goodbye")) {
                    SoundPlayer.playSound("sounds/hoscakal.wav");
                } else if (englishWord.equalsIgnoreCase("Thank You")) {
                    SoundPlayer.playSound("sounds/tesekkurederim.wav");
                }
            } else {
                System.out.println("Incorrect. The correct answer is: " + vocabulary.get(englishWord));
                incorrectStack.push(englishWord);
    
                // Play the "au.wav" sound for incorrect answers
                SoundPlayer.playSound("sounds/au.wav");
            }
            totalQuestions++;
        }
    }    
    

    private static void displayResults() {
        System.out.println("\nQuiz Complete!");
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        int accuracy = (int) ((correctAnswers / (double) totalQuestions) * 100);
        System.out.println("Your accuracy: " + accuracy + "%");
        
        if (correctAnswers == totalQuestions && totalQuestions == 5) {
            // Play the "fanfare" sound when all 5 answers are correct
            SoundPlayer.playSound("sounds/fanfare.wav");
            System.out.println("Congratulations! You got all the answers correct!");
        } else if (accuracy < 70) {
            System.out.println("Keep practicing!");
        } else {
            System.out.println("Great job!");
        }
    }
    

    private static void reviewIncorrectAnswers() {
        System.out.println("\nReviewing Incorrect Answers...");
        while (!incorrectStack.isEmpty()) {
            String incorrectWord = incorrectStack.pop();
            System.out.println("Revisit this word: " + incorrectWord + " - " + vocabulary.get(incorrectWord));
        }
    }

}
