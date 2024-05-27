import java.util.Random;

public class Block {
    private String name;
    public int row;
    public int col;
    public int num;
    public int type;
    public TetrisCanvas canvas;
    private int step = 10; // 每次移动的步长

    public Block(int n, TetrisCanvas c) {
        canvas = c;
        Random r = new Random();
        int l = r.nextInt(3);
        if(TetrisCanvas.collideOther(0, 3+2*l, n, 0, 0) == 0){
            row = 0; col = 3+2*l; num = 2; type = 0;
            canvas.putBlocks(this, 0);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void moveUp() {
    }

    public void moveLeft() {
    }

    public void moveDown() {
        System.out.println("1");
        if(TetrisCanvas.collideOther(row+1, col, num, 0, 1) == 0){
            canvas.putBlocks(this, 1);
        }
        else{

        }
    }

    public void updateBlock(int d){
        if(d == 1){
            row += 1;
        }
        if(d == 2){

        }
        if(d == 3){

        }
    }

    public void moveRight() {
    }
}
