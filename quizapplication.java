import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class quizapplication extends JFrame implements ActionListener {
    private String[] questions = {
        "Who is the prime minister of India ?",
        "What is the factorial of 4 ?",
        "Who is known as the king of cricket?"
    };

    private String[][] options = {
        {"Narendra Hodi", "Narendra Godi", "Narendra Modi", "Narendra Damodar Das Nodi"},
        {"32", "24", "16", "48"},
        {"Virat Kohli", "Sachin Tendulkar", "Rohit Sharma ", "Breet Lee"}
    };

    private int[] correctAnswers = {2, 1, 0};
    private int currentQuestion = 0;
    private int score = 0;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton nextButton;
    private Timer timer;
    private JLabel timerLabel;

    public quizapplication() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.yellow); 


        questionLabel = new JLabel(questions[currentQuestion]);
        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton(options[currentQuestion][i]);

            optionButtons[i].setForeground(Color.blue);

            buttonGroup.add(optionButtons[i]);
        }
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        timerLabel = new JLabel("Time left: 10");

        setLayout(new GridLayout(7, 1));
        add(questionLabel);
        for (JRadioButton optionButton : optionButtons) {
            add(optionButton);
        }
        add(timerLabel);
        add(nextButton);

        startTimer();
    }

    private void startTimer() {
        int timeLimit = 10; // 10 seconds for each question
        timer = new Timer(1000, new ActionListener() {
            int timeLeft = timeLimit;
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft);
                if (timeLeft <= 0) {
                    timer.stop();
                    checkAnswer();
                    loadNextQuestion();
                }
            }
        });
        timer.start();
    }

    private void checkAnswer() {
        int selectedOption = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }
        if (selectedOption == correctAnswers[currentQuestion]) {
            score++;
        }
    }

    private void loadNextQuestion() {
        currentQuestion++;
        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion]);
            buttonGroup.clearSelection();
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(options[currentQuestion][i]);
            }
            timerLabel.setText("Time left: 10");
            startTimer();
        } else {
            showResult();
        }
    }

    private void showResult() {
        UIManager.put("OptionPane.background", Color.red);

        JOptionPane.showMessageDialog(this, "Quiz Over!\nYour score: " + score + "/" + questions.length);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        checkAnswer();
        loadNextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            quizapplication app = new quizapplication();
            app.setVisible(true);
        });
    }
}
