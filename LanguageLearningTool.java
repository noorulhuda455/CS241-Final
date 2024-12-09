import java.util.HashMap;
import java.util.Scanner;

public class LanguageLearningTool {
    private static HashMap<String, String> vocabulary = new HashMap<>();
    private static CustomQueue<String> questionQueue = new CustomQueue<>();
    private static CustomStack<String> incorrectStack = new CustomStack<>();
    private static int correctAnswers = 0;
    private static int totalQuestions = 0;

    public static void main(String[] args) {
        initializeVocabulary();
        populateQuestionQueue();
        runQuiz();
        displayResults();
        reviewIncorrectAnswers();
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
        while (!questionQueue.isEmpty()) {
            String englishWord = questionQueue.dequeue();
            System.out.println("What is the Turkish translation for: " + englishWord + "?");
            String userAnswer = scanner.nextLine().trim();

            if (vocabulary.get(englishWord).equalsIgnoreCase(userAnswer)) {
                System.out.println("Correct!");
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + vocabulary.get(englishWord));
                incorrectStack.push(englishWord);
            }
            totalQuestions++;
        }
    }

    private static void displayResults() {
        System.out.println("\nQuiz Complete!");
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        int accuracy = (int) ((correctAnswers / (double) totalQuestions) * 100);
        System.out.println("Your accuracy: " + accuracy + "%");
        if (accuracy < 70) {
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
