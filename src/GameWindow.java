import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private Player player;
    private boolean movingUp = false;
    private boolean movingLeft = false;
    private boolean movingDown = false;
    private boolean movingRight = false;

    public GameWindow(Player player) {
        super("Game Window");

        // 创建一个 JPanel 来使用 KeyBindings
        JPanel panel = new JPanel(null);  // 使用 null 布局
        panel.setPreferredSize(new java.awt.Dimension(500, 800));
        panel.add(player.imageLabel);
        panel.add(player.nameLabel);

        setContentPane(panel);
        setSize(500, 800);   // 设置宽和高
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 设置默认的关闭窗口
        setVisible(true);    // 设置窗口可见

        // 创建 Player 对象
        this.player = new Player(player.getName(), player.imageLabel, player.nameLabel, player.x, player.y);

        // 设置 KeyBindings
        setupKeyBindings(panel);

        // 使用 Timer 处理连续移动
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movingUp) {
                    player.moveUp();
                }
                if (movingLeft) {
                    player.moveLeft();
                }
                if (movingDown) {
                    player.moveDown();
                }
                if (movingRight) {
                    player.moveRight();
                }
            }
        });
        timer.start();
    }

    private void setupKeyBindings(JPanel panel) {
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = panel.getInputMap(condition);
        ActionMap actionMap = panel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "moveUpPressed");
        inputMap.put(KeyStroke.getKeyStroke("released W"), "moveUpReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "moveLeftPressed");
        inputMap.put(KeyStroke.getKeyStroke("released A"), "moveLeftReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "moveDownPressed");
        inputMap.put(KeyStroke.getKeyStroke("released S"), "moveDownReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "moveRightPressed");
        inputMap.put(KeyStroke.getKeyStroke("released D"), "moveRightReleased");

        actionMap.put("moveUpPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingUp = true;
            }
        });
        actionMap.put("moveUpReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingUp = false;
            }
        });
        actionMap.put("moveLeftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingLeft = true;
            }
        });
        actionMap.put("moveLeftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingLeft = false;
            }
        });
        actionMap.put("moveDownPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingDown = true;
            }
        });
        actionMap.put("moveDownReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingDown = false;
            }
        });
        actionMap.put("moveRightPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingRight = true;
            }
        });
        actionMap.put("moveRightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingRight = false;
            }
        });
    }
}
