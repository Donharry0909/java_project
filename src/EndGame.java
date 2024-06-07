import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class EndGame  extends JFrame {
    public int score;
    public int highs;
    public String highn;
    private final JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private final JButton button;
    public boolean newscore=false;
    
    public EndGame(int s,String name) {
    	super("Scoring Window");
    	/*
    	Scan txt here
    	*/
    	score=s;
    	checkHighScore(name);
    	
    	setLayout(null);        // 使用绝对布局
    	
    	if(newscore) {
    		label = new JLabel("!!!New High Score!!!");
            label.setBounds(130, 50, 200, 30);  // 设置x，y，宽，高
            setLabelFontSize(label, 20); // Set font size to 20
            add(label);
    		label2 = new JLabel(name);
            label2.setBounds(140, 100, 200, 30);  // 设置x，y，宽，高
            setLabelFontSize(label2, 25); // Set font size to 20
            add(label2);
    		label3 = new JLabel(score+"");
            label3.setBounds(180, 150, 200, 30);  // 设置x，y，宽，高
            setLabelFontSize(label3, 25); // Set font size to 20
            add(label3);
    	}
    	else {
    		label = new JLabel("High Score");
            label.setBounds(160, 50, 200, 30);  // 设置x，y，宽，高
            add(label);
            
    		label2 = new JLabel(highn);
            label2.setBounds(170, 80, 200, 30);  // 设置x，y，宽，高
            add(label2);
    		label3 = new JLabel(highs+"");
            label3.setBounds(180, 110, 200, 30);  // 设置x，y，宽，高
            add(label3);
            
    		label6 = new JLabel("My Score");
            label6.setBounds(162, 160, 200, 30);  // 设置x，y，宽，高
            add(label6);
    		label4 = new JLabel(name);
            label4.setBounds(170, 190, 200, 30);  // 设置x，y，宽，高
            add(label4);
    		label5 = new JLabel(score+"");
            label5.setBounds(180, 220, 200, 30);  // 设置x，y，宽，高
            add(label5);
    	}

        
        
        button = new JButton("restart");  // 创建按钮
        button.setBounds(140, 260, 100, 25);
        add(button);
        
     // 添加按钮的动作监听器
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    dispose();
                    new MainWindow();
            }
        });
        
        setSize(400, 400);   // 设置宽和高
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 设置默认的关闭窗口
        setVisible(true);    // 设置窗口可见
    }
    
    public void checkHighScore(String name) {
        String highScoreFilePath = "src/highScore.txt"; // Relative path to the high score file
        String highScoreName = "";
        int highscore = 0;

        // Read the high score from the file
        try {
            File file = new File(highScoreFilePath);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextLine()) {
                    highScoreName = scanner.nextLine();
                    highn=highScoreName;
                }
                if (scanner.hasNextInt()) {
                    highscore = scanner.nextInt();
                    highs=highscore;
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Check if the current score is a new high score
        if (score > highscore) {
            newscore = true;
            // Update the high score in the file
            try {
                FileWriter writer = new FileWriter(highScoreFilePath);
                writer.write(name + "\n" + score);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Method to set the font size of a JLabel
    private void setLabelFontSize(JLabel label, int size) {
        Font currentFont = label.getFont();
        Font newFont = currentFont.deriveFont((float) size);
        label.setFont(newFont);
    }
}
