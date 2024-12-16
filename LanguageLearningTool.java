import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class LanguageLearningTool {

    // Vocabulary hashmap to store English words and their Turkish translations
    private static HashMap<String, String> vocabulary = new HashMap<>();

    // Custom queue to hold quiz questions (English words)
    private static CustomQueue<String> questionQueue = new CustomQueue<>();

    // Custom stack to hold incorrectly answered questions for later review
    private static CustomStack<String> incorrectStack = new CustomStack<>();

    // Counters to track correct answers and total questions asked
    private static int correctAnswers = 0;
    private static int totalQuestions = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to your Turkish Study Guide!");
        System.out.println("Would you like to begin? (Type 'Yes' to start)");

        // Get user input to start or exit
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("Yes")) {
            System.out.println("Great! Let's get started!");
            initializeVocabulary(); // Load vocabulary words
            populateQuestionQueue(); // Prepare randomized quiz questions
            runQuiz(scanner); // Start the quiz
            displayResults(); // Show quiz results
            reviewIncorrectAnswers(); // Review any incorrect answers
        } else {
            System.out.println("Alright, see you next time!");
        }

        // Close scanner resource
        scanner.close();
    }

    /**
     * Initializes the vocabulary with English words and their Turkish translations.
     */
    public static void initializeVocabulary() {
        vocabulary.put("Hello", "Merhaba");
        vocabulary.put("Good Morning", "Günaydın");
        vocabulary.put("Good Night", "İyi Geceler");
        vocabulary.put("Goodbye", "Hoşça kal");
        vocabulary.put("Thank You", "Teşekkür ederim");
        vocabulary.put("Welcome", "Hoş geldin");
        vocabulary.put("Congratulations", "Tebrikler");
        vocabulary.put("Hi", "Selam");
        vocabulary.put("Great to see you", "Sizi görmek harika");
        vocabulary.put("Happy birthday", "Doğum günün kutlu olsun");
    }

    /**
     * Populates the question queue with English words in random order.
     */
    public static void populateQuestionQueue() {
        // Convert vocabulary keys to a list for shuffling
        ArrayList<String> wordsList = new ArrayList<>(vocabulary.keySet());

        // Shuffle the list to randomize the quiz order
        Collections.shuffle(wordsList);

        // Enqueue shuffled words into the question queue
        for (String englishWord : wordsList) {
            questionQueue.enqueue(englishWord);
        }
    }

    /**
     * Runs the quiz, prompting the user to translate English words into Turkish.
     */
    private static void runQuiz(Scanner scanner) {
        // Provide accented character assistance
        System.out.println("Note: You can copy these accented characters for your answers if needed:");
        System.out.println("Available accents: ü, ç, ş, ğ, ı, ö, İ");
        System.out.println();

        // Process each question in the queue
        while (!questionQueue.isEmpty()) {
            String englishWord = questionQueue.dequeue(); // Dequeue the next question
            System.out.println("What is the Turkish translation for: " + englishWord + "?");
            String userAnswer = scanner.nextLine().trim();

            // Check if the user's answer is correct
            if (vocabulary.get(englishWord).equalsIgnoreCase(userAnswer)) {
                System.out.println("Correct!");
                correctAnswers++;
                playSoundForWord(englishWord); // Play success sound for the word
            } else {
                System.out.println("Incorrect. The correct answer is: " + vocabulary.get(englishWord));
                incorrectStack.push(englishWord); // Add to incorrect stack for later review
                SoundPlayer.playSound("sounds/au.wav"); // Play incorrect sound
            }
            totalQuestions++;
        }
    }

    /**
     * Plays the audio sound for the given English word.
     */
    private static void playSoundForWord(String englishWord) {
        switch (englishWord.toLowerCase()) {
            case "hello":
                SoundPlayer.playSound("sounds/merhaba.wav");
                break;
            case "good morning":
                SoundPlayer.playSound("sounds/gunaydin.wav");
                break;
            case "good night":
                SoundPlayer.playSound("sounds/iyigecelar.wav");
                break;
            case "goodbye":
                SoundPlayer.playSound("sounds/hoscakal.wav");
                break;
            case "thank you":
                SoundPlayer.playSound("sounds/tesekkurederim.wav");
                break;
            case "welcome":
                SoundPlayer.playSound("sounds/Hoşgeldin.wav");
                break;
            case "congratulations":
                SoundPlayer.playSound("sounds/Tebrikler.wav");
                break;
            case "hi":
                SoundPlayer.playSound("sounds/Selam.wav");
                break;
            case "great to see you":
                SoundPlayer.playSound("sounds/Sizigörmekharika.wav");
                break;
            case "happy birthday":
                SoundPlayer.playSound("sounds/Doğumgününkutluolsun.wav");
                break;
            default:
                break; // Do nothing if no sound is mapped for the word
        }
    }

    /**
     * Displays the final results of the quiz, including accuracy.
     */
    private static void displayResults() {
        System.out.println("\nQuiz Complete!");
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        int accuracy = (int) ((correctAnswers / (double) totalQuestions) * 100);
        System.out.println("Your accuracy: " + accuracy + "%");

        if (correctAnswers == totalQuestions && totalQuestions == 5) {
            SoundPlayer.playSound("sounds/fanfare.wav"); // Play fanfare sound for perfect score
            System.out.println("Congratulations! You got all the answers correct!");
        } else if (accuracy < 70) {
            System.out.println("Keep practicing!"); // Encourage improvement
        } else {
            System.out.println("Great job!"); // Positive feedback for high accuracy
        }
    }

    /**
     * Reviews the stack of incorrectly answered questions, showing the correct translations.
     */
    private static void reviewIncorrectAnswers() {
        System.out.println("\nReviewing Incorrect Answers...");
        while (!incorrectStack.isEmpty()) {
            String incorrectWord = incorrectStack.pop();
            System.out.println("Revisit this word: " + incorrectWord + " - " + vocabulary.get(incorrectWord));
        }
    }

    // Additional utility methods for testing and metrics
    public static int getVocabularySize() {
        return vocabulary.size();
    }

    public static int getQuestionQueueSize() {
        return questionQueue.size();
    }

    public static String dequeueQuestion() {
        return questionQueue.dequeue();
    }

    public static boolean checkAnswer(String englishWord, String userAnswer) {
        totalQuestions++;
        if (vocabulary.get(englishWord).equalsIgnoreCase(userAnswer)) {
            correctAnswers++;
            return true;
        } else {
            incorrectStack.push(englishWord);
            return false;
        }
    }

    public static int getCorrectAnswersCount() {
        return correctAnswers;
    }

    public static int getIncorrectStackSize() {
        return incorrectStack.size();
    }

    public static void incrementCorrectAnswers() {
        correctAnswers++;
    }

    public static void incrementTotalQuestions() {
        totalQuestions++;
    }

    public static int calculateAccuracy() {
        return (int) ((correctAnswers / (double) totalQuestions) * 100);
    }

    public static String getTranslation(String englishWord) {
        if (englishWord == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        return vocabulary.getOrDefault(englishWord, null);
    }
}


