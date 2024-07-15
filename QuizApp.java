import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private static final int QUESTION_TIME_LIMIT = 10; // Time limit for each question in seconds
    private static Question[] questions;
    private static int score = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static Timer timer;

    public static void main(String[] args) {
        // Initialize quiz questions
        questions = new Question[] {
            new Question("What is the capital of India", new String[] {"1. Berlin", "2. London", "3. New Delhi", "4. Madrid"}, 3),
            new Question("What is the factorial of 4 ?", new String[] {"1. 21", "2. 24", "3. 20", "4. 34"}, 2),
            new Question("What is the differentiation of tanx ?", new String[] {"1. secxtanx", "2. sec^2x", "3. sec x", "4. tanxsec^2x"}, 2),
        };


        // Run the quiz
        for (int i = 0; i < questions.length; i++) {
            displayQuestion(questions[i]);
            if (!submitAnswer(questions[i])) {
                System.out.println("Time's up! Moving to the next question.");
            }
        }

        // Display the final results
        displayResults();
    }

    private static void displayQuestion(Question question) {
        System.out.println(question.getQuestionText());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }
    }

    private static boolean submitAnswer(Question question) {
        timer = new Timer();
        AnswerTask answerTask = new AnswerTask();
        timer.schedule(answerTask, QUESTION_TIME_LIMIT * 1000);
        
        int userAnswer = scanner.nextInt();
        timer.cancel();

        if (userAnswer == question.getCorrectOption()) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect.");
        }
        return true;
    }

    private static void displayResults() {
        System.out.println("Quiz finished!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
        for (int i = 0; i < questions.length; i++) {
            System.out.println((i + 1) + ". " + questions[i].getQuestionText());
            System.out.println("Correct answer: " + questions[i].getOptions()[questions[i].getCorrectOption() - 1]);
        }
    }

    private static class AnswerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Time's up!");
            timer.cancel();
        }
    }
}
