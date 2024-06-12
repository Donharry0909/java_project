import java.awt.*;
import javax.swing.*;

public class NextBlockCanvas extends JPanel {
    private final int BLOCK_SIZE = 30; // The size of each block
    private final int ROWS = 4;
    private final int COLS = 4;
    public Image[][] grid; // Used to store the image of each grid cell

    public NextBlockCanvas() {
        setPreferredSize(new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        grid = new Image[ROWS][COLS]; // Initialize the grid with null
        // No need to initialize the grid, it defaults to null
    }

    public void updateCanva(int n){
        int [][] data = {{},
                         {0,0,1,0,1,1,2,1},
                         {0,1,1,0,2,0,1,1},
                         {0,0,1,0,1,1,1,2},
                         {0,2,1,0,1,1,1,2},
                         {0,1,1,0,1,1,1,2},
                         {0,0,0,1,1,0,1,1},
                         {0,0,1,0,2,0,3,0},
                         };
                         int [] arr = new int[8];
                         arr = data[n];
                         Image im = null;
                         switch(n){
                             case 1: im = new ImageIcon(getClass().getResource("/image/block1.png")).getImage(); break;
                             case 2: im = new ImageIcon(getClass().getResource("/image/block2.png")).getImage(); break;
                             case 3: im = new ImageIcon(getClass().getResource("/image/block3.png")).getImage(); break;
                             case 4: im = new ImageIcon(getClass().getResource("/image/block4.png")).getImage(); break;
                             case 5: im = new ImageIcon(getClass().getResource("/image/block5.png")).getImage(); break;
                             case 6: im = new ImageIcon(getClass().getResource("/image/block6.png")).getImage(); break;
                             case 7: im = new ImageIcon(getClass().getResource("/image/block7.png")).getImage(); break;
                         }
                         for(int i = 0; i < 4; i++){
                            placeBlock(arr[i*2], 1+arr[i*2+1],im);
                        }
    }

    public void placeBlock(int row, int col, Image image) { // Place a single block
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            grid[row][col] = image;
        }
        repaint();
    }

    public void clearBlock(int row, int col){
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            grid[row][col] = null;
        }
        repaint();
    }

    public void clearAllBlocks(){
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                grid[i][j] = null;
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] != null) {
                    g.drawImage(grid[i][j], j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, this);
                } else {
                    g.setColor(new Color(0, 44, 140)); // Dark blue background
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

                    g.setColor(new Color(103, 176, 230)); // Light blue grid lines
                    g.drawLine(j * BLOCK_SIZE, i * BLOCK_SIZE, (j + 1) * BLOCK_SIZE, i * BLOCK_SIZE);
                    g.drawLine(j * BLOCK_SIZE, i * BLOCK_SIZE, j * BLOCK_SIZE, (i + 1) * BLOCK_SIZE);
                }
            }
        }
    }
}
