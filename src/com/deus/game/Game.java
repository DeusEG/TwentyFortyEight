package com.deus.game;

import com.deus.board.Board;
import com.deus.direction.Direction;

public interface Game {

    void init();
    boolean canMove();
    boolean move(Direction direction);
    void addItem();
    Board getGameBoard();
    boolean hasWin();
}
