package software.ulpgc.imageviewer.architecture.swing;

import software.ulpgc.imageviewer.architecture.io.ImageDisplay;
import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.architecture.view.ImageViewerDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements ImageViewerDisplay, ImageDisplay {
    private final JLabel imageLabel;
    private final JButton previousButton;
    private final JButton nextButton;
    private int initX;
    private Shift shift = Shift.Null;
    private Released released = Released.Null;
    private final List<Paint> paints = new ArrayList<>();

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

        JLabel nameLabel = new JLabel("BY: Meng Fei Dai");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        bottomPanel.add(nameLabel, BorderLayout.WEST);

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        configureMouseNavigation();
    }

    private void configureMouseNavigation() {
        imageLabel.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                initX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int offsetX = e.getX() - initX;
                released.offset(offsetX);
            }

            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        imageLabel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int offsetX = e.getX() - initX;
                shift.offset(offsetX);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
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

    @Override
    public void paint(String id, int offset) {
        paints.add(new Paint(id, offset));
        repaint();
    }

    @Override
    public void clear() {
        paints.clear();
        repaint();
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Paint paint : paints) {
            g.drawString("Image: " + paint.id, paint.offset, getHeight() / 2);
        }
    }

    private record Paint(String id, int offset) {}
}
