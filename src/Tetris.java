package src;// Tetris.java

import ch.aplu.jgamegrid.*;

import java.security.Key;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Tetris extends JFrame implements GGActListener {

    private TetrisBlock currentBlock;  // Currently active block
    private TetrisBlock blockPreview = null;   // block in preview window
    private int score;
    private int slowDown = 5;
    private Random random = new Random(0);

    private String difficulty = null;

    private static final int SLOW_DOWN_BASE = 5;

    private TetrisGameCallback gameCallback;

    private boolean isAuto = false;

    private int seed = 30006;
    // For testing mode, the block will be moved automatically based on the blockActions.
    // L is for Left, R is for Right, T is for turning (rotating), and D for down
    private String [] blockActions = new String[10];
    private int blockActionIndex = 0;

    private int [] number;

    // Initialise object
    private void initWithProperties(Properties properties) {
        this.seed = Integer.parseInt(properties.getProperty("seed", "30006"));
        this.difficulty =  properties.getProperty("difficulty");
        setDifficultySpeed();
        random = new Random(seed);
        isAuto = Boolean.parseBoolean(properties.getProperty("isAuto"));
        String blockActionProperty = properties.getProperty("autoBlockActions", "");
        blockActions = blockActionProperty.split(",");
    }

    public Tetris(TetrisGameCallback gameCallback, Properties properties) {
        // Initialise value
        initWithProperties(properties);
        this.gameCallback = gameCallback;
        blockActionIndex = 0;
        if (difficulty.equals("easy")){
            number = new int[7];
        }else{
            number = new int[10];
        }

        // Set up the UI components. No need to modify the UI Components
        tetrisComponents = new TetrisComponents();
        tetrisComponents.initComponents(this);
        gameGrid1.addActListener(this);
        gameGrid1.setSimulationPeriod(getSimulationTime());

        // Add the first block to start
        currentBlock = createRandomTetrisBlock();
        gameGrid1.addActor(currentBlock, new Location(6, 0));
        gameGrid1.doRun();

        // Do not lose keyboard focus when clicking this window
        gameGrid2.setFocusable(false);
        setTitle("SWEN30006 Tetris Madness");
        score = 0;
        showScore(score);
        slowDown = 5;
        //setDifficultySpeed(difficulty);
    }

    // set speed depending on difficulty
    private void setDifficultySpeed() {
        int slowModifier = 0;
        if (difficulty.equals("medium") ) {
            slowModifier = 1;
            this.slowDown = SLOW_DOWN_BASE - 1;
        } else {
            this.slowDown = SLOW_DOWN_BASE;
        }
        if (score > 10)
            this.slowDown = 4 - slowModifier;
        if (score > 20)
            this.slowDown = 3 - slowModifier;
        if (score > 30)
            this.slowDown = 2 - slowModifier;
        if (score > 40)
            this.slowDown = 1 - slowModifier;
        if (score > 50)
            this.slowDown = 0;
        if (difficulty.equals("madness")) {
            this.slowDown = random.nextInt( this.slowDown/2,this.slowDown + 1);
        }
        System.out.println("SlowDOWN : " + this.slowDown);

    }

    // create a block and assign to a preview mode
    TetrisBlock createRandomTetrisBlock() {
        if (blockPreview != null)
            blockPreview.removeSelf();

        // If the game is in auto test mode, then the block will be moved according to the blockActions
        String currentBlockMove = "";
        if (blockActions.length > blockActionIndex) {
            currentBlockMove = blockActions[blockActionIndex];
        }

        blockActionIndex++;
        int rnd;
        if (difficulty.equals("easy")){
            rnd = random.nextInt(7);
        }else{
            rnd = random.nextInt(10);
        }
        number[rnd]++;
        TetrisBlockGenerator tetrisBlockGenerator = new TetrisBlockGenerator(rnd);
        TetrisBlock t = tetrisBlockGenerator.generateRandomTetrisBlock(this);
        if (isAuto) {
            t.setAutoBlockMove(currentBlockMove);
        }
        // Show preview tetrisBlock
        TetrisBlock preview = tetrisBlockGenerator.generateRandomTetrisBlock(this);
        preview.display(gameGrid2, new Location(2 ,1));
        blockPreview = preview;

        if (difficulty.equals("madness")){
            setDifficultySpeed();
        }
        t.setSlowDown(slowDown);
        return t;
    }


    void setCurrentTetrisBlock(TetrisBlock t) {
        gameCallback.changeOfBlock(currentBlock);
        currentBlock = t;
    }

    // Handle user input to move block. Arrow left to move left, Arrow right to move right, Arrow up to rotate and
    // Arrow down for going down
    private void moveBlock(int keyEvent) {
        switch (keyEvent) {
            case KeyEvent.VK_UP:
                if (!difficulty.equals("madness")){
                    currentBlock.rotate();
                }
                break;
            case KeyEvent.VK_LEFT:
                currentBlock.left();
                break;
            case KeyEvent.VK_RIGHT:
                currentBlock.right();
                break;
            case KeyEvent.VK_DOWN:
                currentBlock.drop();
                break;
            default:
                return;
        }
    }
    public void act() {
        removeFilledLine();
        moveBlock(gameGrid1.getKeyCode());
    }
    private void removeFilledLine() {
        for (int y = 0; y < gameGrid1.nbVertCells; y++) {
            boolean isLineComplete = true;
            TetroBlock[] blocks = new TetroBlock[gameGrid1.nbHorzCells];   // One line
            // Calculate if a line is complete
            for (int x = 0; x < gameGrid1.nbHorzCells; x++) {
                blocks[x] =
                        (TetroBlock) gameGrid1.getOneActorAt(new Location(x, y), TetroBlock.class);
                if (blocks[x] == null) {
                    isLineComplete = false;
                    break;
                }
            }
            if (isLineComplete) {
                // If a line is complete, we remove the component block of the shape that belongs to that line
                for (int x = 0; x < gameGrid1.nbHorzCells; x++)
                    gameGrid1.removeActor(blocks[x]);
                ArrayList<Actor> allBlocks = gameGrid1.getActors(TetroBlock.class);
                for (Actor a : allBlocks) {
                    int z = a.getY();
                    if (z < y)
                        a.setY(z + 1);
                }
                gameGrid1.refresh();
                score++;
                gameCallback.changeOfScore(score);
                showScore(score);
                // Set speed of tetrisBlocks
                setDifficultySpeed();
            }
        }
    }

    // Show Score and Game Over

    private void showScore(final int score) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                scoreText.setText(score + " points");
            }
        });

    }

    void gameOver() {
        FileGenerator fileGenerator = new FileGenerator(difficulty,number,score);
        fileGenerator.writeFile();
        gameGrid1.addActor(new Actor("sprites/gameover.gif"), new Location(5, 5));
        gameGrid1.doPause();
        if (isAuto) {
            System.exit(0);
        }
    }

    // Start a new game
    public void startBtnActionPerformed(java.awt.event.ActionEvent evt)
    {
        gameGrid1.doPause();
        gameGrid1.removeAllActors();
        gameGrid2.removeAllActors();
        gameGrid1.refresh();
        gameGrid2.refresh();
        gameGrid2.delay(getDelayTime());
        blockActionIndex = 0;
        currentBlock = createRandomTetrisBlock();
        gameGrid1.addActor(currentBlock, new Location(6, 0));
        gameGrid1.doRun();
        gameGrid1.requestFocus();
        score = 0;
        showScore(score);
        slowDown = 5;
    }

    // Different speed for manual and auto mode
    private int getSimulationTime() {
        if (isAuto) {
            return 10;
        } else {
            return 100;
        }
    }

    private int getDelayTime() {
        if (isAuto) {
            return 200;
        } else {
            return 2000;
        }
    }

    // AUTO GENERATED - do not modify//GEN-BEGIN:variables
    public ch.aplu.jgamegrid.GameGrid gameGrid1;
    public ch.aplu.jgamegrid.GameGrid gameGrid2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField scoreText;
    public javax.swing.JButton startBtn;
    private TetrisComponents tetrisComponents;
    // End of variables declaration//GEN-END:variables

}
