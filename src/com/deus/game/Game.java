package com.deus.game;

import com.deus.board.Board;
import com.deus.direction.Direction;
import com.deus.exception.NotEnoughSpaceException;

public interface Game {

    void init();
    boolean canMove();
    boolean move(Direction direction);
    void addItem() throws NotEnoughSpaceException;
    Board getGameBoard();
    boolean hasWin();
}
