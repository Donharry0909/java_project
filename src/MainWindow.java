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
    private final JTextField textField;
    static MainWindow gw;

    public MainWindow() {
        //test
    	//test2
    	//test3
        super("Hello Swing");   // 建立标题名称
        setLayout(null);        // 使用绝对布局

        label = new JLabel("Enter your name:");
        label.setBounds(140, 50, 100, 30);  // 设置x，y，宽，高
        add(label);

        textField = new JTextField(10); // 设置10列
        textField.setBounds(140, 100, 120, 30);
        add(textField);

        button = new JButton("Click me!");  // 创建按钮
        button.setBounds(125, 200, 150, 25);
        add(button);

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
                    JLabel imageLabel = new JLabel(icon);
                    imageLabel.setBounds(230, 620, icon.getIconWidth(), icon.getIconHeight());
                    // 创建一个 JLabel 用于显示角色名字
                    JLabel nameLabel = new JLabel(playerName);
                    nameLabel.setBounds(250, 600, 100, 30);  // 根据需要调整位置
                    Player player = new Player(playerName, imageLabel, nameLabel, 230, 620);
                    // 关闭 MainWindow
                    dispose();
                    // 打开 GameWindow，并传递 Player 对象
                    new GameWindow(player);
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
