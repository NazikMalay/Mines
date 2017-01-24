import java.util.*;

/**
 * Created by nazar on 24.01.17.
 */
public class Group {

    private int valueOfBomb;
    private int openedCells;
    private int closedCells;
    private boolean done = false;
    private List<Cell> list;
    private Deque<StringBuilder> stack;

    public Group(List<Cell> cells,int value) {
        list=new ArrayList<>(cells);
        this.valueOfBomb=value;
        stack = new LinkedList<>();
    }
    public List<Cell> getList() {
        return list;
    }
    public void add(Cell cell) {
        if (!cell.isUnknown()) throw new IllegalArgumentException("Try to add not unknown cell "+cell.toString()+" into group");
        list.add(cell);
    }
    public void add(Collection<Cell> cells){
        list.addAll(cells);
    }
    public boolean isCross(Group group){
        for (Cell cell : group.list) {
            if (list.contains(cell))return true;
        }
        return false;
    }

    public int size(){
        return list.size();
    }

    public int getValueOfBomb() {
        return valueOfBomb;
    }

    public void setValueOfBomb(int valueOfBomb) {
        this.valueOfBomb = valueOfBomb;
    }

    public int getOpenedCells() {
        return openedCells;
    }

    public void setOpenedCells(int openedCells) {
        this.openedCells = openedCells;
    }

    public int getClosedCells() {
        return closedCells;
    }

    public void setClosedCells(int closedCells) {
        this.closedCells = closedCells;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }




}
