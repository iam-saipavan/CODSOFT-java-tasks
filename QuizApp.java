import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    
    static Scanner scanner = new Scanner(System.in);
    static String[] questions = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "Who wrote 'Romeo and Juliet'?",
    };
    
    static String[][] options = {
        {"1. paris","2. London","3. berlin","4. Madrid"},
        {"1. earth","2. Mars","3. venus","4. Jupiter"},
        {"1. shakespeare","2. Dickens","3. orwell","4. Austen"},
    };
    
    static int[] correctAnswers = {1,2,1};  // Correct answer index (1-based)
    static int score = 0;
    static int currentQuestion = 0;
    
    public static void main(String[] args) {
        for (currentQuestion = 0; currentQuestion < questions.length; currentQuestion++) {
            askQuestion(currentQuestion);
        }
        displayResult();
    }
    
    public static void askQuestion(int questionIndex) {
        System.out.println(questions[questionIndex]);
        for (String option : options[questionIndex]) {
            System.out.println(option);
        }
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                MethodAnswer(-1); // If time is up Method as no answer selected
            }
        };
        
        timer.schedule(task,10000); // 10 seconds timer for each question
        
        System.out.print("Select an option (1-4): ");
        int answer = scanner.nextInt();
        
        if (answer >= 1 && answer <= 4) {
            MethodAnswer(answer);
        }
        
        timer.cancel(); // Cancel the timer if the answer is provided before time runs out
    }
    
    public static void MethodAnswer(int answer) {
        if (answer == correctAnswers[currentQuestion]) {
            System.out.println("Correct!");
            score++;
        } else if (answer != -1) {
            System.out.println("Incorrect!");
        }
    }
    
    public static void displayResult() {
        System.out.println("\nQuiz Finished!");
        System.out.println("Your final score: " + score + "/" + questions.length);
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.length - score));
    }
}
