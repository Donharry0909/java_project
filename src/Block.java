import java.util.Random;

public class Block {
    private String name;
    public int row;
    public int col;
    public int num;
    public int type;
    public TetrisCanvas canvas;
    public GameWindow gameWindow;

    public Block(int n, TetrisCanvas c, GameWindow g) {
        canvas = c; gameWindow = g;
        Random r = new Random();
        int l = r.nextInt(3);
        if(canvas.collideOther(this, 0) == 0){
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
        if(canvas.collideOther(this, 1) == 0){
            canvas.putBlocks(this, 1);
        }
        else if(canvas.collideOther(this, 1) == 1){
            canvas.updateCheck(this);
            System.out.println("oooooooooooooooooo");
            gameWindow.block_generate();
        }
    }

    public void updateBlock(int d){ //更新積木位置
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
