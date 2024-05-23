import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

    private final JLabel label;
    private final JButton button;
    private final JTextField textField;
    static MainWindow gw;

    public MainWindow() {
        super("Hello Swing");   //建立標題名稱
        setLayout(null);

        label = new JLabel("Enter your name:");
        label.setBounds(140, 50, 100, 30);  //設定x，y，寬，高
        add(label);

        button = new JButton("Click me!");
        button.setBounds(125, 200, 150, 25);
        add(button);

        textField = new JTextField(10); //設定10列
        textField.setBounds(140, 100, 120, 30);
        add(textField);

        // 設置視窗屬性
        setSize(400, 300);   //設定寬，長
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //設定預設的關閉視窗
        setVisible(true);    //視窗預設是不可見的
    }

    public static void main(String[] args) {
        gw = new MainWindow();
    }
}
