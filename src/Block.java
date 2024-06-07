import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Block {
    private String name;
    public int row; public int col;
    public int num; //記錄方塊種類
    public int type; //旋轉方向
    public int [][] position;
    public int [] rotate_p;
    public TetrisCanvas canvas;
    public GameWindow gameWindow;
    public Image blockImage;
    public int x_len; public int y_len;
    

    public Block(int n, TetrisCanvas c, GameWindow g) {
        int [][] data = {{0,0,1,0,1,1,2,1},{0,1,0,2,1,0,1,1},{},{},
                         {0,1,1,0,2,0,1,1},{0,0,0,1,1,1,1,2},{},{},
                         {0,0,1,0,1,1,1,2},{0,0,0,1,1,0,2,0},{0,0,0,1,0,2,1,2},{0,1,1,1,2,0,2,1},
                         {0,2,1,0,1,1,1,2},{0,0,1,0,2,0,2,1},{0,0,0,1,0,2,1,0},{0,0,0,1,1,1,2,1},
                         {0,1,1,0,1,1,1,2},{0,0,1,0,1,1,2,0},{0,0,0,1,1,1,0,2},{0,1,1,0,1,1,2,1},
                         {0,0,0,1,1,0,1,1},{},{},{},
                         {0,0,1,0,2,0,3,0},{0,0,0,1,0,2,0,3},{},{},
                         {0,0,1,0,0,0,0,0},{0,0,1,0,0,0,0,0},{0,0,-1,0,0,0,-1,0},{0,0,-1,0,0,0,-1,0},{0,0,-1,0,0,0,-1,0},{0,0,0,0,0,0,0,0},{0,0,3,-1,0,0,0,0}};
        num = n; type = 0;
        position = new int[4][8]; rotate_p = new int[8];
        blockImage = generateImage(n);
        canvas = c; gameWindow = g;
        x_len = 0; y_len = 0;
        for(int i = 0;i<4;i++){
            position[i] = data[(n-1)*4+i];
        }
        rotate_p = data[27+n];
        for(int i = 0; i<4; i++){
            if(position[type][i*2] + 1 > x_len) x_len = position[type][i*2] + 1;
            if(position[type][i*2+1] + 1 > y_len) y_len = position[type][i*2+1] + 1;
        }
        Random r = new Random();
        int l = r.nextInt(10-y_len+1);
        row = 0;
        for(int i = 0; i <= 10 - y_len + 2; i++){
            col = l;
            if(i == 10 - y_len + 2){
                //gameover->stopgame;
                System.out.println("gameover");
                gameWindow.dispose();
                System.exit(0);
                break;
            }
            if(canvas.collideOther(this, 0) == 0){
                canvas.putBlocks(this, 0);
                break;
            }
            else{
                l++;
                if(l > 10 - y_len){
                    l = 0;
                }
            }
        }
    }

    public Image generateImage(int n){
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
        return im;
    }

    public String getName() {
        return name;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public void moveUp() {
        System.out.println("rotate");
        if(canvas.collideOther(this, 4) == 0){
            canvas.putBlocks(this, 4);
        }
        else if(canvas.collideOther(this, 4) == 3){
        }
    }

    public void moveRight() {
        System.out.println("go right");
        if(canvas.collideOther(this, 3) == 0){
            canvas.putBlocks(this, 3);
        }
        else if(canvas.collideOther(this, 3) == 3){
        }
    }

    public void moveLeft() {
        System.out.println("go left");
        if(canvas.collideOther(this, 2) == 0){
            canvas.putBlocks(this, 2);
        }
        else if(canvas.collideOther(this, 2) == 3){
        }
    }

    public void moveDown() {
        System.out.println("go down");
        if(canvas.collideOther(this, 1) == 0){
            canvas.putBlocks(this, 1);
        }
        else if(canvas.collideOther(this, 1) == 2){
            canvas.updateCheck(this);
            System.out.println("collide !");
            gameWindow.block_generate();
        }
    }

    public void updateBlock(int d){ //更新積木位置
        if(d == 1){      //往下
            row += 1;
        }
        if(d == 2){      //往左
            col -= 1;
        }
        if(d == 3){      //往右
            col += 1;
        }
        if(d == 4){      //旋轉
            int l_type = type;
            type++;
            if(type > 3) type = 0;
            row = row+rotate_p[(type)*2] - rotate_p[(l_type)*2];
            col = col+rotate_p[(type)*2+1] - rotate_p[(l_type)*2+1];
            int temp = x_len;
            x_len = y_len;
            y_len = temp;
            while(position[type].length == 0){
                l_type = type;
                type++;
                if(type > 3) type = 0;
                row = row+rotate_p[(type)*2] - rotate_p[(l_type)*2];
                col = col+rotate_p[(type)*2+1] - rotate_p[(l_type)*2+1];
                temp = x_len;
                x_len = y_len;
                y_len = temp;
            }
        }
        if(d == 5){
            int l_type = type;
            type--;
            if(type < 0) type = 3;
            row = row+rotate_p[(type)*2] - rotate_p[(l_type)*2];
            col = col+rotate_p[(type)*2+1] - rotate_p[(l_type)*2+1];
            int temp = x_len;
            x_len = y_len;
            y_len = temp;
            while(position[type].length == 0){
                l_type = type;
                type--;
                if(type < 0) type = 3;
                row = row+rotate_p[(type)*2] - rotate_p[(l_type)*2];
                col = col+rotate_p[(type)*2+1] - rotate_p[(l_type)*2+1];
                temp = x_len;
                x_len = y_len;
                y_len = temp;
            }
        }
    }
}
