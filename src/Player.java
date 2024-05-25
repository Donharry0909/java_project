import javax.swing.JLabel;

public class Player {
    private String name;
    public JLabel imageLabel;
    public JLabel nameLabel;
    public int x;
    public int y;
    private int step = 10; // 每次移动的步长

    public Player(String name, JLabel imageLabel, JLabel nameLabel, int startX, int startY) {
        this.name = name;
        this.imageLabel = imageLabel;
        this.nameLabel = nameLabel;
        this.x = startX;
        this.y = startY;
        updatePosition();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void moveUp() {
        y -= step;
        updatePosition();
    }

    public void moveLeft() {
        x -= step;
        updatePosition();
    }

    public void moveDown() {
        y += step;
        updatePosition();
    }

    public void moveRight() {
        x += step;
        updatePosition();
    }

    private void updatePosition() {
        imageLabel.setLocation(x, y);
        nameLabel.setLocation(x + 20, y - 20);
    }
}
