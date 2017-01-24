
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by nazar on 20.01.17.
 */
public class Main extends JFrame {

    final String TITLE_OF_PROGRAM = "Mines";
    public static final String SIGN_OF_FLAG = "f";
    public static final int BLOCK_SIZE = 30;
    public static int FIELD_SIZE = 10;
    public static int NUMBER_OF_MINES = 5;
    public static final int[] COLOR_OF_NUMBERS = {0x0000FF, 0x008000, 0xFF0000, 0x800000, 0x0};
    public static Cell[][] field = new Cell[FIELD_SIZE][FIELD_SIZE];
    public static int countOpenedCells;
    public static boolean youWon, bangMine = false;
    final int FIELD_DX = 6;
    final int FIELD_DY = 28 + 17;
    final int START_LOCATION = 200;
    final int MOUSE_BUTTON_LEFT = 1;
    final int MOUSE_BUTTON_RIGHT = 3;
    int bangX, bangY;

    public static void main(String[] args) {
        new Main();
    }

    Main() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, FIELD_SIZE * BLOCK_SIZE + FIELD_DX, FIELD_SIZE * BLOCK_SIZE + FIELD_DY);
        setResizable(false);
        TimerLabel timeLabel = new TimerLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Board board = new Board();
        board.setBackground(Color.white);
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Cell cell = new Cell();

                int x = e.getX() / BLOCK_SIZE;
                int y = e.getY() / BLOCK_SIZE;
                if (!bangMine && !youWon) {
                    if (e.getButton() == MOUSE_BUTTON_LEFT)
                        if (field[y][x].isNotOpen()) {
                            cell.openCells(x, y);
                            youWon = countOpenedCells == FIELD_SIZE * FIELD_SIZE - NUMBER_OF_MINES;
                            if (bangMine) {
                                bangX = x;
                                bangY = y;
                            }
                        }
                    if (e.getButton() == MOUSE_BUTTON_RIGHT) field[y][x].inverseFlag();
                    if (bangMine || youWon) timeLabel.stopTimer();
                        if (youWon) {
                            JDialog d = new JDialog();
                            JButton b1 = new JButton("OK");
                            d.setTitle("You WIN");
                            d.setSize(200, 100);
                            b1.setSize(20, 20);
                            d.add(b1);
                            b1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    d.dispose();
                                    youWon = false;
                                }
                            });
                            d.setLocationRelativeTo(board);
                            d.setVisible(true);
                        }
                    board.repaint();
                }
            }
        });
        add(BorderLayout.CENTER, board);
        add(BorderLayout.SOUTH, timeLabel);
        setVisible(true);
        board.initField();
        JButton button = new JButton("Restart");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bangMine) bangMine = false;
                countOpenedCells = 0;
               Main.super.dispose();
                new Main();
            }
    });
        add(BorderLayout.NORTH, button);
    }
  /*  void myBot(){
        int x, y = 0;
        if (field[0][0].isNotOpen()){
            field[0][0].openCells(0,0);
        }
        for (x = 0; x < Main.FIELD_SIZE; x++)
            for (y = 0; y < Main.FIELD_SIZE; y++) {
                if (field[y][x].getCountBomb() > 0 && field[y][x].getCountBomb() < 5){
                    if (field[y][x].numberOf9TypeNeighbours > 0 && field[y][x].numberOfFlags == field[y][x].getCountBomb()){
                        Cell[] notOpenedNeighbours = field[y][x].get9TypeNeighbours();
                        int numberOfnotOpenedCells = field[y][x].numberOf9TypeNeighbours;
                        for (int k = 0; k < numberOfnotOpenedCells; ++k){

                        }
                    }
                }

        }



    }*/
    private void setGroups(){

    }

}
 class MyBot{




        }
