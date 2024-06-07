import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GrayBlock {
	private final int BLOCK_SIZE = 30; // 每个方块的大小
    private final int ROWS = 20;
    private final int COLS = 10;
    private Image[][] grid; // 用于存储每个网格单元的图片
    private int[][] checkMap; //紀錄那些方塊已著地
    public GameWindow gameWindow;
    
    public GrayBlock(Image[][] gr,int[][] ch,GameWindow g) {
		grid=gr;checkMap=ch;gameWindow=g;
		
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
			grid[ROWS-1][k]=new ImageIcon(getClass().getResource("/image/block8.png")).getImage();
		}
    	Random r = new Random();
        int x = r.nextInt(COLS);
        checkMap[ROWS-1][x]=0;
		grid[ROWS-1][x]=null;
    }
}
