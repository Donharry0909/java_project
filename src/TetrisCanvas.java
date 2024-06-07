import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class TetrisCanvas extends JPanel {
    private final int BLOCK_SIZE = 30; // 每个方块的大小
    private final int ROWS = 20;
    private final int COLS = 10;
    private Image[][] grid; // 用于存储每个网格单元的图片
    private int[][] checkMap; //紀錄那些方塊已著地
    public GameWindow gameWindow;

    public TetrisCanvas() {
        setPreferredSize(new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        grid = new Image[ROWS][COLS];//判斷哪些格子要有顏色
        checkMap = new int[ROWS][COLS];
        // 初始化网格为空白
        //grid可以不用初始化本來就是null
    }

    public void test(GameWindow g){
    	gameWindow=g;
    }

    public int collideOther(Block b, int d){ //if 撞到其他方塊or下邊界 //d代表是否下降
        //d==1 初始情形->換位置, d==2 下降case->停止, d==3 左右移case->不動
        //return 0 = 沒事, 1 = 換位置(一開始撞到方塊), 2 = 停止(下面有邊界或方塊), 3 = 不動(左右有邊界或方塊)

        if (d == 1) {//如果是下降case
            int i = b.row+1; int j = b.col;
            if(i+b.x_len-1 >= ROWS){//如果下面是邊界
                return 2;
            }
            for(int k=0; k < 4; k++){
                if(checkMap[i+b.position[b.type][k*2]][j+b.position[b.type][k*2+1]] != 0){
                    return 2;
                }
            }
        }
        else if (d == 0) {
            int i = b.row; int j = b.col;
            for(int k=0; k < 4; k++){
                if(checkMap[i+b.position[b.type][k*2]][j+b.position[b.type][k*2+1]] != 0){
                    return 1;
                }
            }
        }
        else if(d == 2){ //向左
            int i = b.row; int j = b.col-1;
            if(j < 0) return 3;
            for(int k=0; k < 4; k++){
                if(checkMap[i+b.position[b.type][k*2]][j+b.position[b.type][k*2+1]] != 0){
                    return 3;
                }
            }
        }
        else if(d == 3){ //向右
            int i = b.row; int j = b.col+1;
            if(j+b.y_len-1 >= COLS) return 3;
            for(int k=0; k < 4; k++){
                if(checkMap[i+b.position[b.type][k*2]][j+b.position[b.type][k*2+1]] != 0){
                    return 3;
                }
            }
        }
        else if(d == 4){
            b.updateBlock(4);
            int i = b.row; int j = b.col;
            for(int k=0; k < 4; k++){
                if(j < 0){
                    b.updateBlock(5); 
                    return 3;
                }
                if(j+b.y_len-1 >= COLS) {
                    b.updateBlock(5); 
                    return 3;
                }
                if(i < 0) {
                    b.updateBlock(5); 
                    return 3;
                }
                if(i+b.x_len-1 >= ROWS) {
                    b.updateBlock(5); 
                    return 3;
                }
                if(checkMap[i+b.position[b.type][k*2]][j+b.position[b.type][k*2+1]] != 0){
                    b.updateBlock(5);
                    return 3;
                }
            }
            b.updateBlock(5);
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

    public void putBlocks(Block b, int d){ //放置一組blocks //根據d的方向重新放置方塊,d的資訊參考updateBlock
        for(int i = 0; i < 4; i++){
            clearBlock(b.row+b.position[b.type][i*2], b.col+b.position[b.type][i*2+1]);
        }
        b.updateBlock(d);
        Image blockImage = b.blockImage;
        for(int i = 0; i < 4; i++){
            placeBlock(b.row+b.position[b.type][i*2], b.col+b.position[b.type][i*2+1],blockImage);
        }
        repaint();
    }

    public void updateCheck(Block b){ //更新checkmap
        for(int i = 0; i < 4; i++){
            checkMap[b.row+b.position[b.type][i*2]][b.col+b.position[b.type][i*2+1]] = 1;
        }
        for(int i=0;i<COLS;i++) {
            if(checkMap[3][i]!=0) {
                System.out.println("gameover");
                
                gameWindow.dispose();
                
            	//System.exit(0);
            }
        }
        boolean full=true;
        for(int i=0;i<ROWS;i++) {
        	full=true;
        	for(int j=0;j<COLS;j++) {
        		if(checkMap[i][j]==0) {
        			full=false;
        		}
        	}
        	if(full) {
        		for(int k=0;k<COLS;k++) {
        			checkMap[i][k]=0;
        			grid[i][k]=null;
        		}
        		for(int x=i;x>3;--x) {
        			for(int k=0;k<COLS;k++) {
            			checkMap[x][k]=checkMap[x-1][k];
            			grid[x][k]=grid[x-1][k];
            		}
        		}
        	}
        }
    }
    
    public void generateGrayBlock() {
    	for(int i=2;i<ROWS-1;i++) {
    		for(int k=0;k<COLS;k++) {
    			checkMap[i][k]=checkMap[i+1][k];
    			grid[i][k]=grid[i+1][k];
    		}
    	}
    	for(int k=0;k<COLS;k++) {
			checkMap[ROWS-1][k]=1;
			grid[ROWS-1][k]=new ImageIcon(getClass().getResource("/image/block1.png")).getImage();//change to gray block if possible
		}
    	Random r = new Random();
        int x = r.nextInt(COLS);
        checkMap[ROWS-1][x]=0;
		grid[ROWS-1][x]=null;
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

                    if (i == 4) { // If the current row is the 4th row (0-indexed)
                        // Draw top border in red
                        g.setColor(Color.RED);
                        g.drawLine(j * BLOCK_SIZE, i * BLOCK_SIZE, (j + 1) * BLOCK_SIZE, i * BLOCK_SIZE);
                        
                        // Draw other borders in black
                        g.setColor(Color.BLACK);
                        g.drawLine(j * BLOCK_SIZE, i * BLOCK_SIZE, j * BLOCK_SIZE, (i + 1) * BLOCK_SIZE);
                        g.drawLine((j + 1) * BLOCK_SIZE, i * BLOCK_SIZE, (j + 1) * BLOCK_SIZE, (i + 1) * BLOCK_SIZE);
                        g.drawLine(j * BLOCK_SIZE, (i + 1) * BLOCK_SIZE, (j + 1) * BLOCK_SIZE, (i + 1) * BLOCK_SIZE);
                    } else {
                        g.setColor(Color.BLACK);
                        g.drawRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
        }
    }

}
