import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by nazar on 20.01.17.
 */
class Board extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int x = 0; x < Main.FIELD_SIZE; x++) {
            for (int y = 0; y < Main.FIELD_SIZE; y++) Main.field[y][x].paint(g, x, y);

        /*    for (int x1 = 0; x < Main.FIELD_SIZE; x++)
                for (int y1 = 0; y < Main.FIELD_SIZE; y++){
                    if (Main.field[y1][x1].getCountBomb() > 0 && Main.field[y1][x1].getCountBomb() < 5){
                        Main.field[y1][x1].get9TypeNeighbours();
                    }
                }*/
        }
    }

    void initField() {
        int x, y, countMines = 0;
        Random random = new Random();

        for (x = 0; x < Main.FIELD_SIZE; x++)
            for (y = 0; y < Main.FIELD_SIZE; y++)
                Main.field[y][x] = new Cell();

        while (countMines < Main.NUMBER_OF_MINES) {
            do {
                x = random.nextInt(Main.FIELD_SIZE);
                y = random.nextInt(Main.FIELD_SIZE);
            } while (Main.field[y][x].isMined());
            Main.field[y][x].mine();
            countMines++;
        }
        //countBombNear
        for (x = 0; x < Main.FIELD_SIZE; x++)
            for (y = 0; y < Main.FIELD_SIZE; y++)
                if (!Main.field[y][x].isMined()) {
                    int count = 0;
                    for (int dx = -1; dx < 2; dx++)
                        for (int dy = -1; dy < 2; dy++) {
                            int nX = x + dx;
                            int nY = y + dy;
                            if (nX < 0 || nY < 0 || nX > Main.FIELD_SIZE - 1 || nY > Main.FIELD_SIZE - 1) {
                                nX = x;
                                nY = y;
                            }
                            count += (Main.field[nY][nX].isMined()) ? 1 : 0;
                        }
                    Main.field[y][x].setCountBomb(count);
                }


    }
}
