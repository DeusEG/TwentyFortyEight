package com.deus.game;

import com.deus.board.Board;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.deus.direction.Direction;
import com.deus.key.Key;

public class Game2048 implements Game{

    GameHelper helper = new GameHelper();
    Board board;
    Random random = new Random();

    public Game2048(Board board) {
        this.board = board;
    }

    @Override
    public void init() {
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 4, 4));
        board.fillBoard(list);
    }

    @Override
    public boolean canMove() {
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        if (canMove()) {
            switch (direction) {
                case LEFT: {
                    for (int i = 0; i < board.getHeight(); i++) {
                        helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
                    }
                    addItem();
                    break;
                }
                case RIGHT: {
                    for (int i = board.getHeight() - 1; i >= 0 ; i--) {
                        helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
                    }
                    addItem();
                    break;
                }
                case UP: {
                    for (int j = board.getWidth() - 1; j >= 0 ; j--) {
                        helper.moveAndMergeEqual(board.getValues(board.getColumn(j)));
                    }
                    addItem();
                    break;
                }
                case DOWN: {
                    for (int j = 0; j < board.getWidth(); j++) {
                        helper.moveAndMergeEqual(board.getValues(board.getColumn(j)));
                    }
                    addItem();
                    break;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void addItem() {
        List<Key> spaces = board.availableSpace();
        if (!spaces.isEmpty()) {
            Integer[] randomNumbers = new Integer[]{2, 4, 8};
            Integer randomNumber = random.nextInt(randomNumbers.length);
            Key randomKey = spaces.get(random.nextInt(spaces.size()));
            board.addItem(randomKey, randomNumber);
        }
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }
}
