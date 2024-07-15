import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int numberOfTries = 0;
        int guess = 0;
        boolean win = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have randomly chosen a number between 1 and 100.");
        System.out.println("Try to guess it!");

        while (!win) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            numberOfTries++;

            if (guess < 1 || guess > 100) {
                System.out.println("Invalid input! Please enter a number between 1 and 100.");
            } else if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (guess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                win = true;
                System.out.println("Congratulations! You've guessed the number in " + numberOfTries + " tries.");
            }
        }
        scanner.close();
    }
}
