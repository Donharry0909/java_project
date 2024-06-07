import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class GameWindow extends JFrame {
    private boolean movingUp = false;
    private boolean movingLeft = false;
    private boolean movingDown = false;
    private boolean movingRight = false;
    private Random r;
    private TetrisCanvas canvas;
    private Block b;
    private JLabel label; // Declare the label
    public int score;
    public int difficulty;
    public int grayblocks;
    public GrayBlock gray;


    public GameWindow(int diff,String name) {
        super("Game Window");
        difficulty=diff;

        // Create TetrisCanvas instance
        canvas = new TetrisCanvas(name);
        canvas.setLayout(null);
        canvas.test(this);

        // Use BorderLayout layout manager
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);

        score = 0;

        // Create and configure the label
        label = new JLabel("Score: " + score); // Initialize the label with the score
        JPanel labelPanel = new JPanel();
        labelPanel.add(label);

        // Add the label panel to the right side of the window
        add(labelPanel, BorderLayout.EAST);

        setSize(800, 700); // Set the width to 800 to accommodate the label
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setVisible(true); // Set the window visible

        // Set KeyBindings
        setupKeyBindings(canvas); // Pass canvas to set up key bindings
        
        gray=new GrayBlock(canvas.grid, canvas.checkMap, this);
        
        block_generate();
        block_operate();
    }

    public void block_generate() {
    	if(!this.isDisplayable()) {
    		return;
    	}
        r = new Random();
        int n = r.nextInt(1, 8);
        b = new Block(n, canvas, this);
        
        // Update the score when a block is generated
        score += 4;
        grayblocks++;
        if(grayblocks==15) {
        	grayblocks=0;
        	gray.generateGrayBlock();
        }
        updateScoreLabel();
    }

    private void block_operate() {
    	if(!this.isDisplayable()) {
    		return;
    	}
        // Use Timer to handle continuous movement
        Timer timer = new Timer(60*difficulty, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movingUp) {
                    b.moveUp();
                    movingUp = false;
                }
                if (movingLeft) {
                    b.moveLeft();
                }
                if (movingDown) {
                    b.moveDown();
                }
                if (movingRight) {
                    b.moveRight();
                }
            }
        });
        timer.start();

        // Create a Timer to trigger an ActionEvent every 750 milliseconds
        Timer timer1 = new Timer(375*difficulty, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.moveDown();
            }
        });
        timer1.start();
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
                
            }
        });
        actionMap.put("moveUpReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingUp = true;
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

    // Method to update the score and the label's text
    private void updateScoreLabel() {
        label.setText("Score: " + score);
    }

    
}
