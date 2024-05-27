import java.awt.*;
import javax.swing.*;

public class TetrisCanvas extends JPanel {
    private final int BLOCK_SIZE = 30; // 每个方块的大小
    private final int ROWS = 20;
    private final int COLS = 10;
    private Image[][] grid; // 用于存储每个网格单元的图片
    private int[][] checkMap;

    public TetrisCanvas() {
        setPreferredSize(new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        grid = new Image[ROWS][COLS];
        checkMap = new int[ROWS][COLS];
        // 初始化网格为空白
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = null;
            }
        }
    }

    public void test(GameWindow g){

    }

    public int collideOther(Block b, int d){ //if 撞到其他方塊or下邊界 //d代表是否下降
        //d==1 初始情形->換位置, d==2 下降case->停止, d==3 左右移case->不動
        //return 0 = 沒事, 1 = 換位置(一開始撞到方塊), 2 = 停止(下面有邊界或方塊), 3 = 不動(左右有邊界或方塊)

        if (d == 1) {//如果是下降case
            int i = b.row+1; int j = b.col;
            if(i+2 >= ROWS){//如果下面是邊界
                return 1;
            }
            if(checkMap[i][j] != 0 || checkMap[i+1][j] != 0 || checkMap[i+1][j+1] != 0 || checkMap[i+2][j+1] != 0){//如果有障礙物
                return 1;
            }
        }
        else if (true) {
            
        }
        return 0;
    }

    public static int collideborder(Block b, int d){ //if 撞到邊界

        return 0;
    }

    public void placeBlock(int row, int col, Image image) { //放置單個block
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            grid[row][col] = image;
        }
    }

    public void clearBlock(int row, int col){
        grid[row][col] = null;
    }

    public void putBlocks(Block b, int d){ //放置一組blocks
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

    public void updateCheck(Block b){
        int i = b.row; int j = b.col;
        switch(b.num){
            case 2:
                if(b.type == 0){
                    checkMap[i][j] = 1; checkMap[i+1][j] = 1; checkMap[i+1][j+1] = 1; checkMap[i+2][j+1] = 1;
                }
                else if(b.type == 1){

                }
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
