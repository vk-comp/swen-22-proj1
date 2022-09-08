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


//
//    private Location[][] r = new Location[4][4];
//    static Random random = new Random(0);
//
//
//    public TetrisBlockGenerator() {
//
//    }
//
//    public static TetrisBlock generateBlock(Tetris tetris, int blockID){
//        Location[][] r = null;
//        String blockName = null;
//        int blockType = 0;
//        switch (blockID) {
//            case 0:
//                blockID = 0;
//                blockType = 0;
//                blockName = "I";
//
//                // rotId 0
//                r = new Location[4][4];
//                r[0][0] = new Location(new Location(-1, 0));
//                r[1][0] = new Location(new Location(0, 0));
//                r[2][0] = new Location(new Location(1, 0));
//                r[3][0] = new Location(new Location(2, 0));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, -1));
//                r[1][1] = new Location(new Location(0, 0));
//                r[2][1] = new Location(new Location(0, 1));
//                r[3][1] = new Location(new Location(0, 2));
//                // rotId 2
//                r[0][2] = new Location(new Location(-1, 0));
//                r[1][2] = new Location(new Location(0, 0));
//                r[2][2] = new Location(new Location(1, 0));
//                r[3][2] = new Location(new Location(2, 0));
//                // rotId 3
//                r[0][3] = new Location(new Location(0, -1));
//                r[1][3] = new Location(new Location(0, 0));
//                r[2][3] = new Location(new Location(0, 1));
//                r[3][3] = new Location(new Location(0, 2));
//                break;
//            case 1:
//                //J
//                blockID = 1;
//                blockType = 1;
//                blockName = "J";
//                r = new Location[4][4];
//                r[0][0] = new Location(new Location(-1, 0));
//                r[1][0] = new Location(new Location(0, 0));
//                r[2][0] = new Location(new Location(1, 0));
//                r[3][0] = new Location(new Location(1, 1));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, -1));
//                r[1][1] = new Location(new Location(0, 0));
//                r[2][1] = new Location(new Location(0, 1));
//                r[3][1] = new Location(new Location(-1, 1));
//                // rotId 2
//                r[0][2] = new Location(new Location(1, 0));
//                r[1][2] = new Location(new Location(0, 0));
//                r[2][2] = new Location(new Location(-1, 0));
//                r[3][2] = new Location(new Location(-1, -1));
//                // rotId 3
//                r[0][3] = new Location(new Location(0, 1));
//                r[1][3] = new Location(new Location(0, 0));
//                r[2][3] = new Location(new Location(0, -1));
//                r[3][3] = new Location(new Location(1, -1));
//                break;
//            case 2:
//                //L
//                blockID = 2;
//                blockType = 2;
//                blockName = "L";
//                r = new Location[4][4];
//                // rotId 0
//                r[0][0] = new Location(new Location(1, 0));
//                r[1][0] = new Location(new Location(0, 0));
//                r[2][0] = new Location(new Location(-1, 0));
//                r[3][0] = new Location(new Location(-1, 1));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, 1));
//                r[1][1] = new Location(new Location(0, 0));
//                r[2][1] = new Location(new Location(0, -1));
//                r[3][1] = new Location(new Location(-1, -1));
//                // rotId 2
//                r[0][2] = new Location(new Location(-1, 0));
//                r[1][2] = new Location(new Location(0, 0));
//                r[2][2] = new Location(new Location(1, 0));
//                r[3][2] = new Location(new Location(1, -1));
//                // rotId 3
//                r[0][3] = new Location(new Location(0, -1));
//                r[1][3] = new Location(new Location(0, 0));
//                r[2][3] = new Location(new Location(0, 1));
//                r[3][3] = new Location(new Location(1, 1));
//                break;
//            case 3:
//                //O
//                blockID = 3;
//                blockType = 3;
//                blockName = "O";
//                r = new Location[4][4];
//                // rotId 0
//                r[0][0] = new Location(new Location(0, 0));
//                r[1][0] = new Location(new Location(1, 0));
//                r[2][0] = new Location(new Location(1, 1));
//                r[3][0] = new Location(new Location(0, 1));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, 0));
//                r[1][1] = new Location(new Location(1, 0));
//                r[2][1] = new Location(new Location(1, 1));
//                r[3][1] = new Location(new Location(0, 1));
//                // rotId 2
//                r[0][2] = new Location(new Location(0, 0));
//                r[1][2] = new Location(new Location(1, 0));
//                r[2][2] = new Location(new Location(1, 1));
//                r[3][2] = new Location(new Location(0, 1));
//                // rotId 3
//                r[0][3] = new Location(new Location(0, 0));
//                r[1][3] = new Location(new Location(1, 0));
//                r[2][3] = new Location(new Location(1, 1));
//                r[3][3] = new Location(new Location(0, 1));
//                break;
//            case 4:
//                //S
//                blockID = 4;
//                blockType = 4;
//                blockName = "S";
//                r = new Location[4][4];
//                // rotId 0
//                r[0][0] = new Location(new Location(1, 0));
//                r[1][0] = new Location(new Location(0, 0));
//                r[2][0] = new Location(new Location(0, 1));
//                r[3][0] = new Location(new Location(-1, 1));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, 1));
//                r[1][1] = new Location(new Location(0, 0));
//                r[2][1] = new Location(new Location(-1, 0));
//                r[3][1] = new Location(new Location(-1, -1));
//                // rotId 2
//                r[0][2] = new Location(new Location(-1, 0));
//                r[1][2] = new Location(new Location(0, 0));
//                r[2][2] = new Location(new Location(0, -1));
//                r[3][2] = new Location(new Location(1, -1));
//                // rotId 3
//                r[0][3] = new Location(new Location(0, -1));
//                r[1][3] = new Location(new Location(0, 0));
//                r[2][3] = new Location(new Location(1, 0));
//                r[3][3] = new Location(new Location(1, 1));
//                break;
//            case 5:
//                //T
//                blockID = 5;
//                blockType = 5;
//                blockName = "T";
//                r = new Location[4][4];
//                // rotId 0
//                r[0][0] = new Location(new Location(-1, 0));
//                r[1][0] = new Location(new Location(0, 0));
//                r[2][0] = new Location(new Location(1, 0));
//                r[3][0] = new Location(new Location(0, 1));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, -1));
//                r[1][1] = new Location(new Location(0, 0));
//                r[2][1] = new Location(new Location(0, 1));
//                r[3][1] = new Location(new Location(-1, 0));
//                // rotId 2
//                r[0][2] = new Location(new Location(1, 0));
//                r[1][2] = new Location(new Location(0, 0));
//                r[2][2] = new Location(new Location(-1, 0));
//                r[3][2] = new Location(new Location(0, -1));
//                // rotId 3
//                r[0][3] = new Location(new Location(0, 1));
//                r[1][3] = new Location(new Location(0, 0));
//                r[2][3] = new Location(new Location(0, -1));
//                r[3][3] = new Location(new Location(1, 0));
//                break;
//            case 6:
//                //Z
//                blockID = 6;
//                blockType = 6;
//                blockName = "Z";
//                r = new Location[4][4];
//                // rotId 0
//                r[0][0] = new Location(new Location(-1, 0));
//                r[1][0] = new Location(new Location(0, 0));
//                r[2][0] = new Location(new Location(0, 1));
//                r[3][0] = new Location(new Location(1, 1));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, -1));
//                r[1][1] = new Location(new Location(0, 0));
//                r[2][1] = new Location(new Location(-1, 0));
//                r[3][1] = new Location(new Location(-1, 1));
//                // rotId 2
//                r[0][2] = new Location(new Location(1, 0));
//                r[1][2] = new Location(new Location(0, 0));
//                r[2][2] = new Location(new Location(0, -1));
//                r[3][2] = new Location(new Location(-1, -1));
//                // rotId 3
//                r[0][3] = new Location(new Location(0, 1));
//                r[1][3] = new Location(new Location(0, 0));
//                r[2][3] = new Location(new Location(1, 0));
//                r[3][3] = new Location(new Location(1, -1));
//                break;
//            case 7:
//                blockID = 7;
//                blockType = 1;
//                blockName = "+";
//                r = new Location[5][4];
//                // rotId 0
//                r[0][0] = new Location(new Location(-1, 0));
//                r[1][0] = new Location(new Location(0, 0));
//                r[2][0] = new Location(new Location(1, 0));
//                r[3][0] = new Location(new Location(0, 1));
//                r[4][0] = new Location(new Location(0, -1));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, -1));
//                r[1][1] = new Location(new Location(0, 0));
//                r[2][1] = new Location(new Location(0, 1));
//                r[3][1] = new Location(new Location(-1, 0));
//                r[4][1] = new Location(new Location(1, 0));
//                // rotId 2
//                r[0][2] = new Location(new Location(1, 0));
//                r[1][2] = new Location(new Location(0, 0));
//                r[2][2] = new Location(new Location(-1, 0));
//                r[3][2] = new Location(new Location(0, -1));
//                r[4][2] = new Location(new Location(0, 1));
//                // rotId 3
//                r[0][3] = new Location(new Location(0, 1));
//                r[1][3] = new Location(new Location(0, 0));
//                r[2][3] = new Location(new Location(0, -1));
//                r[3][3] = new Location(new Location(1, 0));
//                r[4][3] = new Location(new Location(-1, 0));
//                break;
//
//            case 8:
//                blockID = 8;
//                blockType = 2;
//                blockName = "P";
//                r = new Location[5][4];
//                // rotId 0
//                r[0][0] = new Location(new Location(1, -1));
//                r[1][0] = new Location(new Location(0, 0));
//                r[2][0] = new Location(new Location(1, 0));
//                r[3][0] = new Location(new Location(0, 1));
//                r[4][0] = new Location(new Location(0, -1));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, 1));
//                r[1][1] = new Location(new Location(0, 0));
//                r[2][1] = new Location(new Location(1, 1));
//                r[3][1] = new Location(new Location(-1, 0));
//                r[4][1] = new Location(new Location(1, 0));
//                // rotId 2
//                r[0][2] = new Location(new Location(1, 1));
//                r[1][2] = new Location(new Location(1, 0));
//                r[2][2] = new Location(new Location(1, -1));
//                r[3][2] = new Location(new Location(0, 1));
//                r[4][2] = new Location(new Location(0, 0));
//                // rotId 3
//                r[0][3] = new Location(new Location(1, 1));
//                r[1][3] = new Location(new Location(-1, 1));
//                r[2][3] = new Location(new Location(0, 1));
//                r[3][3] = new Location(new Location(-1, 0));
//                r[4][3] = new Location(new Location(0, 0));
//                break;
//            case 9:
//                blockID = 9;
//                blockType = 3;
//                blockName = "Q";
//                r = new Location[5][4];
//                // rotId 0
//                r[0][0] = new Location(new Location(1, 1));
//                r[1][0] = new Location(new Location(1, 0));
//                r[2][0] = new Location(new Location(1, -1));
//                r[3][0] = new Location(new Location(0, -1));
//                r[4][0] = new Location(new Location(0, 0));
//                // rotId 1
//                r[0][1] = new Location(new Location(0, 1));
//                r[1][1] = new Location(new Location(0, 0));
//                r[2][1] = new Location(new Location(1, 1));
//                r[3][1] = new Location(new Location(-1, 1));
//                r[4][1] = new Location(new Location(1, 0));
//                // rotId 2
//                r[0][2] = new Location(new Location(1, 1));
//                r[1][2] = new Location(new Location(1, 0));
//                r[2][2] = new Location(new Location(0, -1));
//                r[3][2] = new Location(new Location(0, 1));
//                r[4][2] = new Location(new Location(0, 0));
//                // rotId 3
//                r[0][3] = new Location(new Location(1, 0));
//                r[1][3] = new Location(new Location(-1, 1));
//                r[2][3] = new Location(new Location(0, 1));
//                r[3][3] = new Location(new Location(-1, 0));
//                r[4][3] = new Location(new Location(0, 0));
//                break;
//        }
//        return new TetrisBlock(tetris, r, blockID, blockName, blockType);
//    }
//    public static TetrisBlock generateTetrisBlock(Tetris tetris,String difficulty){
//        int rnd;
//
//        if(difficulty.equals("medium")  || difficulty.equals("madness") ){
//            rnd = random.nextInt(10);
//            System.out.println("rnd: " + rnd + difficulty);
//        } else{ rnd = random.nextInt(7);
//            System.out.println("rnd: " + rnd + difficulty);
//        }
//
//        return generateBlock(tetris, rnd);
//    }
}
