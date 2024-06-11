import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class start extends JPanel{
    public JButton bb;
    private Image backgroundImage;
    public JButton aa;

    


    public start(){
        setLayout(new GridBagLayout());
        //setSize(400, 300);   // 设置宽和高
        bb = new JButton("Start");
        bb.setBackground(Color.WHITE);
        bb.setPreferredSize(new Dimension(150, 50));
        aa = new JButton("Instructions");
        aa.setBackground(Color.WHITE);
        aa.setPreferredSize(new Dimension(150, 50));
        setOpaque(false);

        //ImageIcon icon2 = new ImageIcon(getClass().getResource("/image/pic.jpg"));
        //JLabel picLabel = new JLabel(icon2);

        

        JPanel jpanel = new JPanel(new GridBagLayout());
        jpanel.setOpaque(false);
        //jpanel.add(picLabel);


        

       //Add image to the panel
       GridBagConstraints imageConstraints = new GridBagConstraints();
       imageConstraints.gridx = 0;
       imageConstraints.gridy = 0;
       imageConstraints.fill = GridBagConstraints.BOTH;
       //add(picLabel, imageConstraints);

       // Add "Start" button to the panel
       GridBagConstraints startButtonConstraints = new GridBagConstraints();
       startButtonConstraints.gridx = 0; // Place in the first column
       startButtonConstraints.gridy = 0; // Place in the second row
       startButtonConstraints.insets = new Insets(10, 0, 0, 0); // Add some top padding
       startButtonConstraints.anchor = GridBagConstraints.CENTER; // Center horizontally
       jpanel.add(bb, startButtonConstraints);

       // Add "Instructions" button to the panel
       GridBagConstraints instructionsButtonConstraints = new GridBagConstraints();
       instructionsButtonConstraints.gridx = 0; // Place in the first column
       instructionsButtonConstraints.gridy = 1; // Place in the third row
       instructionsButtonConstraints.insets = new Insets(10, 0, 0, 0); // Add some top padding
       instructionsButtonConstraints.anchor = GridBagConstraints.CENTER; // Center horizontally
       jpanel.add(aa, instructionsButtonConstraints);

        add(jpanel);
        setVisible(true);    
        // 设置窗口可见

        /*bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    // 关闭 MainWindow
                    setVisible(false);
                }
            }
        );*/
    
    
    }
        
}




