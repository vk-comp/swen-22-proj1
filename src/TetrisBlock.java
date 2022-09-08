// TetrisBlock.java
package src;
import ch.aplu.jgamegrid.*;

import java.util.ArrayList;
import java.util.Arrays;

class TetrisBlock extends Actor
{
    private final int blockId;
    private final int blockType;
    private final String blockName;
    private Location[][] r;

    TetrisBlock(Tetris tetris, Location[][] r, int blockId, String blockName, int blockType)
    {
        super();

        this.tetris = tetris;
        this.blockId = blockId;
        this.blockType = blockType;
        this.blockName = blockName;


        for (int i = 0; i < r.length; i++)
            blocks.add(new TetroBlock(blockType, r[i]));
    }
    public int getBlockId() {
        return blockId;
    }
    public String toString() {
        return "For testing, do not change: Block: " + blockName + ". Location: " + blocks + ". Rotation: " + rotId;
    }
    protected Tetris tetris;
    private boolean isStarting = true;
    private int rotId = 0;
    private int nb;
    protected ArrayList<TetroBlock> blocks = new ArrayList<TetroBlock>();
    private Actor nextTetrisBlock = null;
    private String autoBlockMove = "";
    private int autoBlockIndex = 0;
    public void setAutoBlockMove(String autoBlockMove) {
        this.autoBlockMove = autoBlockMove;
    }

    // The game is called in a run loop, this method for a block is called every 1/30 seconds as the starting point
    public void act()
    {
        if (isStarting) {
            for (TetroBlock a : blocks) {
                Location loc =
                        new Location(getX() + a.getRelLoc(0).x, getY() + a.getRelLoc(0).y);
                gameGrid.addActor(a, loc);
            }
            isStarting = false;
            nb = 0;
        } else if (nb >= blocks.size() && canAutoPlay()) {
            autoMove();
        } else
        {
            setDirection(90);
            if (nb == 1)
                nextTetrisBlock = tetris.createRandomTetrisBlock();
            if (!advance())
            {
                if (nb == 0)  // Game is over when tetrisBlock cannot fall down
                    tetris.gameOver();
                else
                {
                    setActEnabled(false);
                    gameGrid.addActor(nextTetrisBlock, new Location(6, 0));
                    tetris.setCurrentTetrisBlock(nextTetrisBlock);
                }
            }
            nb++;
        }
    }

    // Based on the input in the properties file, the block can move automatically
    private void autoMove() {
        String moveString = autoBlockMove.substring(autoBlockIndex, autoBlockIndex + 1);
        switch (moveString) {
            case "L":
                left();
                break;
            case "R":
                right();
                break;
            case "T":
                rotate();
                break;
            case "D":
                drop();
                break;
        }

        autoBlockIndex++;
    }

    // Cechk if the block can be played automatically based on the properties file
    private boolean canAutoPlay() {
        if (autoBlockMove != null && !autoBlockMove.equals("")) {
            if (autoBlockMove.length() > autoBlockIndex) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    void display(GameGrid gg, Location location)
    {
        for (TetroBlock a : blocks)
        {
            Location loc =
                    new Location(location.x + a.getRelLoc(0).x, location.y + a.getRelLoc(0).y);
            gg.addActor(a, loc);
        }
    }

    // Actual actions on the block: move the block left, right, drop and rotate the block
    void left()
    {
        if (isStarting)
            return;
        setDirection(180);
        advance();
    }

    void right()
    {
        if (isStarting)
            return;
        setDirection(0);
        advance();
    }

    void rotate()
    {
        if (isStarting)
            return;

        int oldRotId = rotId; // Save it
        rotId++;
        if (rotId == 4)
            rotId = 0;

        if (canRotate(rotId))
        {
            for (TetroBlock a : blocks)
            {
                Location loc = new Location(getX() + a.getRelLoc(rotId).x, getY() + a.getRelLoc(rotId).y);
                a.setLocation(loc);
            }
        }
        else
            rotId = oldRotId;  // Restore

    }

    private boolean canRotate(int rotId)
    {
        // Check for every rotated tetroBlock within the tetrisBlock
        for (TetroBlock a : blocks)
        {
            Location loc =
                    new Location(getX() + a.getRelLoc(rotId).x, getY() + a.getRelLoc(rotId).y);
            if (!gameGrid.isInGrid(loc))  // outside grid->not permitted
                return false;
            TetroBlock block =
                    (TetroBlock)(gameGrid.getOneActorAt(loc, TetroBlock.class));
            if (blocks.contains(block))  // in same tetrisBlock->skip
                break;
            if (block != null)  // Another tetroBlock->not permitted
                return false;
        }
        return true;
    }

    void drop()
    {
        if (isStarting)
            return;
        setSlowDown(0);
    }

    // Logic to check if the block has been removed (as winning a line) or drop to the bottom
    private boolean advance()
    {
        boolean canMove = false;
        for (TetroBlock a: blocks) {
            if (!a.isRemoved()) {
                canMove = true;
            }
        }
        for (TetroBlock a : blocks)
        {
            if (a.isRemoved())
                continue;
            if (!gameGrid.isInGrid(a.getNextMoveLocation()))
            {
                canMove = false;
                break;
            }
        }

        for (TetroBlock a : blocks)
        {
            if (a.isRemoved())
                continue;
            TetroBlock block =
                    (TetroBlock)(gameGrid.getOneActorAt(a.getNextMoveLocation(),
                            TetroBlock.class));
            if (block != null && !blocks.contains(block))
            {
                canMove = false;
                break;
            }
        }

        if (canMove)
        {
            move();
            return true;
        }
        return false;
    }

    // Override Actor.setDirection()
    public void setDirection(double dir)
    {
        super.setDirection(dir);
        for (TetroBlock a : blocks)
            a.setDirection(dir);
    }

    // Override Actor.move()
    public void move()
    {
        if (isRemoved())
            return;
        super.move();
        for (TetroBlock a : blocks)
        {
            if (a.isRemoved())
                break;
            a.move();
        }
    }

    // Override Actor.removeSelf()
    public void removeSelf()
    {
        super.removeSelf();
        for (TetroBlock a : blocks)
            a.removeSelf();
    }
}
