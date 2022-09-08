package src;

import ch.aplu.jgamegrid.Location;

import java.util.*;

public class TetrisBlockGenerator {

    private Location[][] r = new Location[4][4];
    static Random random = new Random(0);


    public TetrisBlockGenerator() {

    }

    public static TetrisBlock generateBlock(Tetris tetris, int blockID){
        Location[][] r = null;
        String blockName = null;
        switch (blockID) {
            case 0:
                blockID = 0;
                blockName = "I";

                // rotId 0
                r = new Location[4][4];
                r[0][0] = new Location(new Location(-1, 0));
                r[1][0] = new Location(new Location(0, 0));
                r[2][0] = new Location(new Location(1, 0));
                r[3][0] = new Location(new Location(2, 0));
                // rotId 1
                r[0][1] = new Location(new Location(0, -1));
                r[1][1] = new Location(new Location(0, 0));
                r[2][1] = new Location(new Location(0, 1));
                r[3][1] = new Location(new Location(0, 2));
                // rotId 2
                r[0][2] = new Location(new Location(-1, 0));
                r[1][2] = new Location(new Location(0, 0));
                r[2][2] = new Location(new Location(1, 0));
                r[3][2] = new Location(new Location(2, 0));
                // rotId 3
                r[0][3] = new Location(new Location(0, -1));
                r[1][3] = new Location(new Location(0, 0));
                r[2][3] = new Location(new Location(0, 1));
                r[3][3] = new Location(new Location(0, 2));
                break;
            case 1:
                //J
                blockID = 1;
                blockName = "J";
                r = new Location[4][4];
                r[0][0] = new Location(new Location(-1, 0));
                r[1][0] = new Location(new Location(0, 0));
                r[2][0] = new Location(new Location(1, 0));
                r[3][0] = new Location(new Location(1, 1));
                // rotId 1
                r[0][1] = new Location(new Location(0, -1));
                r[1][1] = new Location(new Location(0, 0));
                r[2][1] = new Location(new Location(0, 1));
                r[3][1] = new Location(new Location(-1, 1));
                // rotId 2
                r[0][2] = new Location(new Location(1, 0));
                r[1][2] = new Location(new Location(0, 0));
                r[2][2] = new Location(new Location(-1, 0));
                r[3][2] = new Location(new Location(-1, -1));
                // rotId 3
                r[0][3] = new Location(new Location(0, 1));
                r[1][3] = new Location(new Location(0, 0));
                r[2][3] = new Location(new Location(0, -1));
                r[3][3] = new Location(new Location(1, -1));
                break;
            case 2:
                //L
                blockID = 2;
                blockName = "L";
                r = new Location[4][4];
                // rotId 0
                r[0][0] = new Location(new Location(1, 0));
                r[1][0] = new Location(new Location(0, 0));
                r[2][0] = new Location(new Location(-1, 0));
                r[3][0] = new Location(new Location(-1, 1));
                // rotId 1
                r[0][1] = new Location(new Location(0, 1));
                r[1][1] = new Location(new Location(0, 0));
                r[2][1] = new Location(new Location(0, -1));
                r[3][1] = new Location(new Location(-1, -1));
                // rotId 2
                r[0][2] = new Location(new Location(-1, 0));
                r[1][2] = new Location(new Location(0, 0));
                r[2][2] = new Location(new Location(1, 0));
                r[3][2] = new Location(new Location(1, -1));
                // rotId 3
                r[0][3] = new Location(new Location(0, -1));
                r[1][3] = new Location(new Location(0, 0));
                r[2][3] = new Location(new Location(0, 1));
                r[3][3] = new Location(new Location(1, 1));
                break;
            case 3:
                //O
                blockID = 3;
                blockName = "O";
                r = new Location[4][4];
                // rotId 0
                r[0][0] = new Location(new Location(0, 0));
                r[1][0] = new Location(new Location(1, 0));
                r[2][0] = new Location(new Location(1, 1));
                r[3][0] = new Location(new Location(0, 1));
                // rotId 1
                r[0][1] = new Location(new Location(0, 0));
                r[1][1] = new Location(new Location(1, 0));
                r[2][1] = new Location(new Location(1, 1));
                r[3][1] = new Location(new Location(0, 1));
                // rotId 2
                r[0][2] = new Location(new Location(0, 0));
                r[1][2] = new Location(new Location(1, 0));
                r[2][2] = new Location(new Location(1, 1));
                r[3][2] = new Location(new Location(0, 1));
                // rotId 3
                r[0][3] = new Location(new Location(0, 0));
                r[1][3] = new Location(new Location(1, 0));
                r[2][3] = new Location(new Location(1, 1));
                r[3][3] = new Location(new Location(0, 1));
                break;
            case 4:
                //S
                blockID = 4;
                blockName = "S";
                r = new Location[4][4];
                // rotId 0
                r[0][0] = new Location(new Location(1, 0));
                r[1][0] = new Location(new Location(0, 0));
                r[2][0] = new Location(new Location(0, 1));
                r[3][0] = new Location(new Location(-1, 1));
                // rotId 1
                r[0][1] = new Location(new Location(0, 1));
                r[1][1] = new Location(new Location(0, 0));
                r[2][1] = new Location(new Location(-1, 0));
                r[3][1] = new Location(new Location(-1, -1));
                // rotId 2
                r[0][2] = new Location(new Location(-1, 0));
                r[1][2] = new Location(new Location(0, 0));
                r[2][2] = new Location(new Location(0, -1));
                r[3][2] = new Location(new Location(1, -1));
                // rotId 3
                r[0][3] = new Location(new Location(0, -1));
                r[1][3] = new Location(new Location(0, 0));
                r[2][3] = new Location(new Location(1, 0));
                r[3][3] = new Location(new Location(1, 1));
                break;
            case 5:
                //T
                blockID = 5;
                blockName = "T";
                r = new Location[4][4];
                // rotId 0
                r[0][0] = new Location(new Location(-1, 0));
                r[1][0] = new Location(new Location(0, 0));
                r[2][0] = new Location(new Location(1, 0));
                r[3][0] = new Location(new Location(0, 1));
                // rotId 1
                r[0][1] = new Location(new Location(0, -1));
                r[1][1] = new Location(new Location(0, 0));
                r[2][1] = new Location(new Location(0, 1));
                r[3][1] = new Location(new Location(-1, 0));
                // rotId 2
                r[0][2] = new Location(new Location(1, 0));
                r[1][2] = new Location(new Location(0, 0));
                r[2][2] = new Location(new Location(-1, 0));
                r[3][2] = new Location(new Location(0, -1));
                // rotId 3
                r[0][3] = new Location(new Location(0, 1));
                r[1][3] = new Location(new Location(0, 0));
                r[2][3] = new Location(new Location(0, -1));
                r[3][3] = new Location(new Location(1, 0));
                break;
            case 6:
                //Z
                blockID = 6;
                blockName = "Z";
                r = new Location[4][4];
                // rotId 0
                r[0][0] = new Location(new Location(-1, 0));
                r[1][0] = new Location(new Location(0, 0));
                r[2][0] = new Location(new Location(0, 1));
                r[3][0] = new Location(new Location(1, 1));
                // rotId 1
                r[0][1] = new Location(new Location(0, -1));
                r[1][1] = new Location(new Location(0, 0));
                r[2][1] = new Location(new Location(-1, 0));
                r[3][1] = new Location(new Location(-1, 1));
                // rotId 2
                r[0][2] = new Location(new Location(1, 0));
                r[1][2] = new Location(new Location(0, 0));
                r[2][2] = new Location(new Location(0, -1));
                r[3][2] = new Location(new Location(-1, -1));
                // rotId 3
                r[0][3] = new Location(new Location(0, 1));
                r[1][3] = new Location(new Location(0, 0));
                r[2][3] = new Location(new Location(1, 0));
                r[3][3] = new Location(new Location(1, -1));
                break;
        }
        return new TetrisBlock(tetris, r, blockID, blockName);
    }
    public static TetrisBlock generateTetrisBlock(Tetris tetris,String difficulty){
        int rnd;

        if(difficulty == "medium" || difficulty == "madness"){
            rnd = random.nextInt(10);
        } else{ rnd = random.nextInt(7);
            System.out.println("rnd: " + rnd);
        }

        return generateBlock(tetris, rnd);
    }
}
