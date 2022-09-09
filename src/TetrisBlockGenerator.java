// Workshop 12 Thursday 12:00 Group 10
// Jinrun Ji: 1394227
// Alexei Cherstnov: 1080039
// Vincent Khuat: 1081402
package src;

import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

import java.util.*;

public class TetrisBlockGenerator {
    private int rnd;

    public TetrisBlockGenerator(int rnd){
        this.rnd = rnd;
    }

    public TetrisBlock generateRandomTetrisBlock(Tetris tetris){
        TetrisBlock t = null;
        switch (rnd) {
            case 0:
                t = new I(tetris);
                break;
            case 1:
                t = new J(tetris);
                break;
            case 2:
                t = new L(tetris);
                break;
            case 3:
                t = new O(tetris);
                break;
            case 4:
                t = new S(tetris);
                break;
            case 5:
                t = new T(tetris);
                break;
            case 6:
                t = new Z(tetris);
                break;
            case 7:
                t = new PLUS(tetris);
                break;
            case 8:
                t = new P(tetris);
                break;
            case 9:
                t = new Q(tetris);
                break;
        }
        return t;
    }

}
