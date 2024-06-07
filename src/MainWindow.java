import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private final JLabel label;
    private final JButton button;
    private final JButton button2;
    private final JTextField textField;
    static MainWindow gw;

    public MainWindow() {
        super("Hello Swing");   // 建立标题名称
        setLayout(null);        // 使用绝对布局

        label = new JLabel("Enter your name:");
        label.setBounds(140, 50, 100, 30);  // 设置x，y，宽，高
        add(label);

        textField = new JTextField(10); // 设置10列
        textField.setBounds(140, 100, 120, 30);
        add(textField);
        
        int difficulty=0;
        button = new JButton("easy");  // 创建按钮
        button.setBounds(100, 200, 70, 25);
        add(button);
        button2 = new JButton("hard");  // 创建按钮
        button2.setBounds(230, 200, 70, 25);
        add(button2);

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

        // 设置窗口属性
        setSize(400, 300);   // 设置宽和高
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 设置默认的关闭窗口
        setVisible(true);    // 设置窗口可见
    }

    public static void main(String[] args) {
        gw = new MainWindow();
    }
}
