import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradesGUI extends JFrame {
    private JLabel titleLabel;
    private JLabel[] subjectLabels;
    private JTextField[] marksFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public StudentGradesGUI() {
        setTitle("Student Grades Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Enter marks for each subject (out of 100):");
        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(mainPanel, BorderLayout.CENTER);
        
        int numSubjects = getNumberOfSubjects();
        if (numSubjects > 0) {
            marksFields = new JTextField[numSubjects];
            subjectLabels = new JLabel[numSubjects];

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(numSubjects, 2, 10, 10));
            for (int i = 0; i < numSubjects; i++) {
                subjectLabels[i] = new JLabel("Subject " + (i + 1) + ": ");
                marksFields[i] = new JTextField(10);
                inputPanel.add(subjectLabels[i]);
                inputPanel.add(marksFields[i]);
            }

            calculateButton = new JButton("Calculate");
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calculateResults();
                }
            });

            JPanel resultPanel = new JPanel();
            resultPanel.setLayout(new GridLayout(3, 1, 10, 10));
            resultPanel.add(totalMarksLabel);
            resultPanel.add(averagePercentageLabel);
            resultPanel.add(gradeLabel);

            mainPanel.add(inputPanel, BorderLayout.CENTER);
            mainPanel.add(calculateButton, BorderLayout.SOUTH);
            contentPane.add(resultPanel, BorderLayout.SOUTH);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid number of subjects. The application will exit.");
            System.exit(0);
        }
    }

    private int getNumberOfSubjects() {
        String input = JOptionPane.showInputDialog(this, "Enter the number of subjects:");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0; // Return 0 if the input is invalid
        }
    }

    private void calculateResults() {
        int numSubjects = marksFields.length;
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            try {
                marks[i] = Integer.parseInt(marksFields[i].getText());
                totalMarks += marks[i];
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks (numbers).");
                return;
            }
        }

        double averagePercentage = (double) totalMarks / numSubjects;

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        gradeLabel.setText("Grade: " + grade);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradesGUI gui = new StudentGradesGUI();
            gui.setVisible(true);
        });
    }
}
