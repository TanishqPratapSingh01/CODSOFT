import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int numberToGuess;
    private int numberOfAttempts;
    private Random random;

    private JLabel instructionsLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel imageLabel;

    public NumberGuessingGameGUI() {
        random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        numberOfAttempts = 0;

        setTitle("Number Guessing Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        instructionsLabel = new JLabel("Guess a number between 1 and 100:");
        instructionsLabel.setForeground(Color.BLUE);
        guessTextField = new JTextField(10);
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("");
        feedbackLabel.setForeground(Color.YELLOW);
        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setForeground(Color.YELLOW);

        // Load an example image
        ImageIcon icon = new ImageIcon("C:\\Users\\LENOVO\\Downloads.jpeg");
        imageLabel = new JLabel(icon);

        // Set background colors
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.red);
        inputPanel.add(instructionsLabel);
        inputPanel.add(guessTextField);
        inputPanel.add(guessButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLACK);
        centerPanel.add(feedbackLabel);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLACK);
        southPanel.add(attemptsLabel);

        JPanel westPanel = new JPanel();
        westPanel.setBackground(Color.BLACK);
        westPanel.add(imageLabel);

        // Add panels to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(guessTextField.getText());
            numberOfAttempts++;
            attemptsLabel.setText("Attempts: " + numberOfAttempts);

            if (userGuess < numberToGuess) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                feedbackLabel.setText("Too high! Try again.");
            } else {
                feedbackLabel.setText("Congratulations ! You have guess the correct number ");
                guessButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGuessingGameGUI game = new NumberGuessingGameGUI();
                game.setVisible(true);
            }
        });
    }
}
