import java.awt.*;
import javax.swing.*;

public class start extends JPanel {
    public JButton bb;
    public JButton aa;

    public start() {
        setLayout(new GridBagLayout());

        bb = createCustomButton("/image/start.png", 250, 50);
        aa = createCustomButton("/image/instruction.png", 60, 60);

        setOpaque(false);

        JPanel jpanel = new JPanel(new GridBagLayout());
        jpanel.setOpaque(false);

        GridBagConstraints startButtonConstraints = new GridBagConstraints();
        startButtonConstraints.gridx = 0;
        startButtonConstraints.gridy = 1;
        startButtonConstraints.insets = new Insets(0, 50, 100, 0); // Move up by 100 units
        startButtonConstraints.anchor = GridBagConstraints.CENTER;
        jpanel.add(bb, startButtonConstraints);

        GridBagConstraints instructionsButtonConstraints = new GridBagConstraints();
        instructionsButtonConstraints.gridx = 0;
        instructionsButtonConstraints.gridy = 0;
        instructionsButtonConstraints.insets = new Insets(10, 250, 30, 0); // No movement for aa button
        instructionsButtonConstraints.anchor = GridBagConstraints.CENTER;
        jpanel.add(aa, instructionsButtonConstraints);

        add(jpanel);
        setVisible(true);    
    }

    private JButton createCustomButton(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back

        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(width, height));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}
