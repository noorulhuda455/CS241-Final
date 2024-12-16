import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LanguageLearningToolTest {

    @BeforeEach
    void setUp() {
        // Initialize vocabulary and populate question queue before each test
        LanguageLearningTool.initializeVocabulary();
        LanguageLearningTool.populateQuestionQueue();
    }

    @Test
    void testVocabularyInitialization() {
        // Check that vocabulary is initialized with the correct size
        assertEquals(10, LanguageLearningTool.getVocabularySize(), 
            "Vocabulary should contain 10 entries.");
    }

    @Test
    void testQuestionQueuePopulation() {
        // Check if the question queue is populated correctly
        assertEquals(10, LanguageLearningTool.getQuestionQueueSize(), 
            "Question queue should contain 10 questions.");
    }

    @Test
    void testDequeueQuestion() {
        // Test that dequeue removes a question from the queue
        String firstWord = LanguageLearningTool.dequeueQuestion();
        assertNotNull(firstWord, "Dequeued question should not be null.");
        assertEquals(9, LanguageLearningTool.getQuestionQueueSize(), 
            "Question queue size should decrease by 1 after dequeue.");
    }

    @Test
    void testCorrectAnswerHandling() {
    // Check if correct answers are identified properly
    boolean isCorrect = LanguageLearningTool.checkAnswer("Hello", "Merhaba");
    assertTrue(isCorrect, "The answer should be correct.");
    assertEquals(1, LanguageLearningTool.getCorrectAnswersCount(), 
        "Correct answers count should increase after a correct answer.");
    }

    @Test
    void testIncorrectAnswerHandling() {
    // Check if incorrect answers are added to the stack
    boolean isCorrect = LanguageLearningTool.checkAnswer("Hello", "WrongAnswer");
    assertFalse(isCorrect, "The answer should be incorrect.");
    assertEquals(1, LanguageLearningTool.getIncorrectStackSize(), 
        "Incorrect stack size should increase after an incorrect answer.");
    }   

    @Test
    void testAccuracyCalculation() {
    // Simulate quiz performance
    LanguageLearningTool.incrementCorrectAnswers();
    LanguageLearningTool.incrementTotalQuestions();
    LanguageLearningTool.incrementTotalQuestions();

    int accuracy = LanguageLearningTool.calculateAccuracy();
    assertEquals(50, accuracy, "Accuracy should be 50% when 1/2 questions are correct.");
    }

    @Test
    void testGetTranslationForNullWord() {
    // Ensure an exception is thrown when a null word is passed
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        LanguageLearningTool.getTranslation(null);
    });

    // Check the exception message
    assertEquals("Word cannot be null", exception.getMessage(),
        "Exception message should indicate that the input word cannot be null.");
}

}

