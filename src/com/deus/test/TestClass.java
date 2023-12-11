package com.deus.test;

import com.deus.board.Board;
import com.deus.board.SquareBoard;
import com.deus.game.Game;
import com.deus.game.Game2048;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048();
        System.out.println(game2048.canMove());
    }
}

