import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RockPaperScissorsGUI extends JFrame implements ActionListener {
    private JLabel resultLabel;
    private JButton rockButton, paperButton, scissorsButton;
    private int userScore, computerScore;

    public RockPaperScissorsGUI() {
        setTitle("Rock Paper Scissors Game");
        setSize(20000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        rockButton = new JButton("Rock");
        rockButton.addActionListener(this);
        paperButton = new JButton("Paper");
        paperButton.addActionListener(this);
        scissorsButton = new JButton("Scissors");
        scissorsButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        resultLabel = new JLabel("Make your choice!");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        add(resultLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        userScore = 0;
        computerScore = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userChoice = "";
        if (e.getSource() == rockButton) {
            userChoice = "Rock";
        } else if (e.getSource() == paperButton) {
            userChoice = "Paper";
        } else if (e.getSource() == scissorsButton) {
            userChoice = "Scissors";
        }

        String computerChoice = generateComputerChoice();

        String result = determineWinner(userChoice, computerChoice);

        resultLabel.setText(result);

        if (userScore == 5 || computerScore == 5) {
            rockButton.setEnabled(false);
            paperButton.setEnabled(false);
            scissorsButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Game Over!");
        } else {
            JOptionPane.showMessageDialog(this, result);
        }
    }

    private String generateComputerChoice() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        int randomIndex = (int) (Math.random() * choices.length);
        return choices[randomIndex];
    }

    private String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (userChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            userScore++;
            return "You win! User: " + userScore + ", Computer: " + computerScore;
        } else {
            computerScore++;
            return "Computer wins! User: " + userScore + ", Computer: " + computerScore;
        }
    }

    public static void main(String[] args) {
            RockPaperScissorsGUI game = new RockPaperScissorsGUI();
            game.setVisible(true);
        }
    }