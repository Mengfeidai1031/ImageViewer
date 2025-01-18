package software.ulpgc.imageviewer.view;

import software.ulpgc.imageviewer.model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ImageViewerDisplay {
    private final JLabel imageLabel;
    private final JButton previousButton;
    private final JButton nextButton;

    public MainFrame() {
        setTitle("Image Viewer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Image Viewer", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel, BorderLayout.NORTH);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JLabel nameLabel = new JLabel("Meng Fei Dai");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        bottomPanel.add(nameLabel, BorderLayout.WEST);

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void display(Image image) {
        ImageIcon imageIcon = new ImageIcon(image.content());
        imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(640, 480, java.awt.Image.SCALE_SMOOTH)));
    }

    @Override
    public void onPrevious(ActionListener listener) {
        previousButton.addActionListener(listener);
    }

    @Override
    public void onNext(ActionListener listener) {
        nextButton.addActionListener(listener);
    }
}
