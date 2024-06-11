import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainWindow extends JFrame {

    private final JLabel label;
    private final JButton button;
    private final JButton button2;
    private final JTextField textField;
    private start jj;
    static MainWindow gw;

    public MainWindow() {  
        setSize(800, 800); 
        setResizable(false);
        JPanel qq = new JPanel();
        qq.setSize(800, 800);
        qq.setLayout(null);  
        jj = new start();
        qq.setOpaque(false);

        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/image/pic2.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        JLayeredPane layeredPane = new JLayeredPane();

        JPanel e = new JPanel();
        e.add(backgroundLabel);
        e.setBounds(0, 0, 800, 800);
        jj.setBounds(0, 0, 800, 800);
        layeredPane.add(e, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(jj, JLayeredPane.PALETTE_LAYER);
        add(layeredPane);

        label = new JLabel("Enter your name:");
        Font font = new Font("Arial", Font.BOLD, 30);
        label.setFont(font);
        label.setForeground(Color.BLUE);
        label.setBounds(300, 330, 300, 50); 
        qq.add(label);

        textField = new JTextField(10);
        textField.setPreferredSize(new Dimension(200, 30)); 
        textField.setBounds(330, 390, 200, 30);
        qq.add(textField);
        
        button = createCustomButton("/image/easy.png", 310, 440, 100, 100);
        qq.add(button);

        button2 = createCustomButton("/image/hard.png", 450, 440, 100, 100);
        qq.add(button2);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = textField.getText();
                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(MainWindow.this, "请输入名字", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    dispose();
                    new GameWindow(2, playerName);
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = textField.getText();
                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(MainWindow.this, "请输入名字", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    dispose();
                    new GameWindow(1, playerName);
                }
            }
        });

        jj.bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jj.setVisible(false);
                setLayout(null); 
                layeredPane.add(qq, JLayeredPane.PALETTE_LAYER);
            }
        });

        jj.aa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainWindow.this, "wasd to control\nW to rotate\nA to move right\nD to move left\nS to fall faster\nScore adds 4 Everytime a block spawns\nGood Luck!", "instructions", JOptionPane.WARNING_MESSAGE);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createCustomButton(String imagePath, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back

        JButton button = new JButton(icon);
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    public static void main(String[] args) {
        gw = new MainWindow();
    }
}
