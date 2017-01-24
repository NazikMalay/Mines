import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by nazar on 20.01.17.
 */
public class Cell {

    private int countBombNear,x,y;
    private boolean isOpen, isMine, isFlag;
    //valued - відкрита і має цифру
    private boolean valued;
    // unknown - не відкрита
    private boolean unknown;
    private List<Cell> neighbours;
    private List<Group> groups;

   /* public Cell(int x, int y) {
        unknown = true;
        isMine = false;
        valued = false;
        neighbours = new ArrayList<>();
        //possibilities=new ArrayList<>();
        //possibility = -1;
        this.x = x;
        this.y = y;
        groups=new ArrayList<>();
    }*/
    public void add(Group group){
        groups.add(group);
    }

    void open() {
        isOpen = true;
        Main.bangMine = isMine;
        if (!isMine) Main.countOpenedCells++;
    }

    void mine() {
        isMine = true;
    }

    public boolean isUnknown() {
        return unknown;
    }
    public void setUnknown() {
        this.unknown = true;
        isMine = false;
        valued = false;
    }
    public List<Cell>getValued() {
        List<Cell> valued = new ArrayList<>();
        for (Cell cell : neighbours) {
            if (cell.isValued()) valued.add(cell);
        }
        return valued;
    }
    public void setValue(int value) {
        this.countBombNear = value;
        valued = true;
        unknown = false;
        isMine = false;
    }
    public void addNeighbour(Cell cell) {
        neighbours.add(cell);
    }

    public boolean isValued() {
        return valued;
    }

    void setCountBomb(int count) {
        countBombNear = count;
    }

    int getCountBomb() {
        return countBombNear;
    }

    boolean isNotOpen() {
        return !isOpen;
    }

    boolean isMined() {
        return isMine;
    }

    void inverseFlag() {
        isFlag = !isFlag;
    }

    final int BLOCK_SIZE = Main.BLOCK_SIZE;

    void paintBomb(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x * BLOCK_SIZE + 7, y * BLOCK_SIZE + 10, 18, 10);
        g.fillRect(x * BLOCK_SIZE + 11, y * BLOCK_SIZE + 6, 10, 18);
        g.fillRect(x * BLOCK_SIZE + 9, y * BLOCK_SIZE + 8, 14, 14);
        g.setColor(Color.white);
        g.fillRect(x * BLOCK_SIZE + 11, y * BLOCK_SIZE + 10, 4, 4);
    }

    void paintString(Graphics g, String str, int x, int y, Color color) {
        g.setColor(color);
        g.setFont(new Font("", Font.BOLD, BLOCK_SIZE));
        g.drawString(str, x * BLOCK_SIZE + 8, y * BLOCK_SIZE + 26);
    }

    void paint(Graphics g, int x, int y) {
        g.setColor(Color.lightGray);
        g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (!isOpen) {
            if ((Main.bangMine || Main.youWon) && isMine) paintBomb(g, x, y, Color.black);
            else {
                g.setColor(Color.lightGray);
                g.fill3DRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);
                if (isFlag) paintString(g, Main.SIGN_OF_FLAG, x, y, Color.red);
            }
        } else if (isMine) paintBomb(g, x, y, Main.bangMine ? Color.red : Color.black);
        else if (countBombNear > 0)
            paintString(g, Integer.toString(countBombNear), x, y, new Color(Main.COLOR_OF_NUMBERS[countBombNear - 1]));
    }

    void openCells(int x, int y) {
        if (x < 0 || x > Main.FIELD_SIZE - 1 || y < 0 || y > Main.FIELD_SIZE - 1)
            return;
        if (!Main.field[y][x].isNotOpen()) return;
        Main.field[y][x].open();
        if (Main.field[y][x].getCountBomb() > 0 || Main.bangMine) return;
        for (int dx = -1; dx < 2; dx++)
            for (int dy = -1; dy < 2; dy++) openCells(x + dx, y + dy);
    }

}
