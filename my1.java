import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class my1 extends JFrame implements ActionListener {
    private JButton playButton;
    private JComboBox<String> difficultyComboBox;
    private JLabel statusLabel;
    private ImageIcon backgroundImage;

    public my1() { // Constructor name matches class name
        setTitle("Guessing Game");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Background image
        backgroundImage = new ImageIcon("logo.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        add(backgroundLabel);

        // Panel for controls
        JPanel controlPanel = new JPanel();
        controlPanel.setOpaque(false);
        controlPanel.setLayout(new FlowLayout());

        // Play button
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        playButton.setBackground(Color.BLUE); // Set background color
        playButton.setForeground(Color.WHITE); // Set text color
        controlPanel.add(playButton);

        // Difficulty levels
        String[] difficultyOptions = {"Easy", "Medium", "Hard"};
        difficultyComboBox = new JComboBox<>(difficultyOptions);
        difficultyComboBox.setBackground(Color.GREEN); // Set background color
        difficultyComboBox.setForeground(Color.BLACK); // Set text color
        controlPanel.add(difficultyComboBox);

        add(controlPanel, BorderLayout.SOUTH);

        // Status label
        statusLabel = new JLabel("Click 'Play' to start the game.");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(my1::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            startGame();
        }
    }

    private void startGame() {
        // Retrieve selected difficulty level
        String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
        int maxNumber,attempts;
        switch (selectedDifficulty) {
            case "Easy":
                maxNumber = 10;
		attempts = 15;
                break;
            case "Medium":
                maxNumber = 50;
		attempts = 10;
                break;
            case "Hard":
                maxNumber = 100;
		attempts = 5;
                break;
            default:
                maxNumber = 10;
		attempts = 15;
        }
	int score=0;
        // Generate random number
        int randomNumber = (int) (Math.random() * maxNumber) + 1;
        try {
            int flag = 0;
                for (int i = 1; i <= attempts; i++) {
                    String input = JOptionPane.showInputDialog("Guess a number between 0 and " + (maxNumber - 1) + ":");
                    int num = Integer.parseInt(input);
                    String result = (num == randomNumber) ? "Congratulations! You guessed the number."
                            : (num > randomNumber) ? "Your guess is greater than the random number."
                            : "Your guess is smaller than the random number.";
                    JOptionPane.showMessageDialog(null, result);
                    if (result.equals("Congratulations! You guessed the number.")) {
                        flag = 1;
			score = 100 - (attempts - 1) * 10;
                        break;
                    }
                }
                
                if (flag == 0) {
                    JOptionPane.showMessageDialog(null,
                            "Your attempts are over. The answer was: " + randomNumber);
                }
else
{
JOptionPane.showMessageDialog (null, "Your score"+score, "Title", JOptionPane.INFORMATION_MESSAGE);}
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }
}
