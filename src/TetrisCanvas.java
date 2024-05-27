import java.awt.*;
import javax.swing.*;

public class TetrisCanvas extends JPanel {
    private final int BLOCK_SIZE = 30; // 每个方块的大小
    private final int ROWS = 20;
    private final int COLS = 10;
    private Image[][] grid; // 用于存储每个网格单元的图片

    public TetrisCanvas() {
        setPreferredSize(new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        grid = new Image[ROWS][COLS];
        
        // 初始化网格为空白
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = null;
            }
        }
    }

    public static int collideOther(int row, int col, int n, int type, int d){ //if 撞到其他方塊or下邊界 //d代表是否下降
        //d==1 下降case 停止, d==0 初始情形 換位置
        return 0;
    }

    public static int collideborder(int row, int col, int n, int type, int d){ //if 撞到邊界

        return 0;
    }

    public void placeBlock(int row, int col, Image image) {
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            grid[row][col] = image;
        }
    }

    public void clearBlock(int row, int col){
        grid[row][col] = null;
    }

    public void putBlocks(Block b, int d){
        switch(b.num){
            case 2:
                if(b.type == 0){
                    clearBlock(b.row, b.col); clearBlock(b.row+1, b.col); clearBlock(b.row+1, b.col+1); clearBlock(b.row+2, b.col+1);
                    b.updateBlock(d);
                    Image blockImage = new ImageIcon(getClass().getResource("/image/block2.png")).getImage();
                    placeBlock(b.row, b.col, blockImage); placeBlock(b.row+1, b.col, blockImage); placeBlock(b.row+1, b.col+1, blockImage); placeBlock(b.row+2, b.col+1, blockImage); 
                    repaint();
                }
                else if(b.type == 1){

                }
                break;
            case 3:
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] != null) {
                    g.drawImage(grid[i][j], j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, this);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }
}
