import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.font.*;



public class MainWindow extends JFrame {

    private final JLabel label;
    private final JButton button;
    private final JButton button2;
    private final JTextField textField;
    private start jj;
    Font customfont;
    static MainWindow gw;

    public MainWindow() {  // 建立标题名称
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);
        //JPanel qq = new JPanel();
        //qq.setSize(800, 700);
        jj = new start();
        //qq.setOpaque(false);

        

        
        

        label = new JLabel("Enter your name:");
        Font font = new Font("Arial", Font.BOLD, 50);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBounds(190, 100, 450, 50); 
        //qq.add(label);

        textField = new JTextField(10);
        textField.setPreferredSize(new Dimension(200, 30)); // 设置10列
        textField.setBounds(290, 200, 200, 30);
        //qq.add(textField);
        
        int difficulty=0;
        button = new JButton("easy");  // 创建按钮
        
        button.setBounds(200, 400, 100, 100);
        button.setBackground(Color.WHITE);
        //qq.add(button);


        button2 = new JButton("hard");  // 创建按钮
        button2.setBounds(460, 400, 100, 100);
        button2.setBackground(Color.WHITE);
        //qq.add(button2);


        // 添加按钮的动作监听器
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = textField.getText();
                if (playerName.isEmpty()) {
                    // 显示警告消息框
                    JOptionPane.showMessageDialog(MainWindow.this, "请输入名字", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    // 创建一个 JLabel 用于显示图片
                    ImageIcon icon = new ImageIcon(getClass().getResource("/image/block2.png"));
                    if (icon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
                        System.err.println("Error: Image not found");
                    }
                    // 关闭 MainWindow
                    dispose();
                    // 打开 GameWindow，并传递 Player 对象
                    new GameWindow(2,playerName);
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = textField.getText();
                if (playerName.isEmpty()) {
                    // 显示警告消息框
                    JOptionPane.showMessageDialog(MainWindow.this, "请输入名字", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    // 创建一个 JLabel 用于显示图片
                    ImageIcon icon = new ImageIcon(getClass().getResource("/image/block2.png"));
                    if (icon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
                        System.err.println("Error: Image not found");
                    }
                    // 关闭 MainWindow
                    dispose();
                    // 打开 GameWindow，并传递 Player 对象
                    new GameWindow(1,playerName);
                }
            }
        });
        jj.bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    // 关闭 MainWindow
                    jj.setVisible(false);
                    setLayout(null); 
                    setSize(800, 800);
                    add(label);
                    add(textField);
                    add(button);
                    add(button2);
                }
            }
        );
        jj.aa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                    JOptionPane.showMessageDialog(MainWindow.this, "wasd to control\nW to rotate\nA to move right\nD to move left\nS to fall faster\nScore adds 4 Everytime a block spawns\nGood Luck!","instructions", JOptionPane.WARNING_MESSAGE);
                
                }
            }
        );
        add(jj);
        
        

        // 设置窗口属性
        setSize(400, 300);   // 设置宽和高
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 设置默认的关闭窗口
        setVisible(true);    // 设置窗口可见
    }
    public static void main(String[] args) {
        gw = new MainWindow();
    }
}
