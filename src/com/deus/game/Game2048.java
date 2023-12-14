package com.deus.game;

import com.deus.board.Board;
import com.deus.board.SquareBoard;

import java.util.*;

import com.deus.direction.Direction;
import com.deus.exception.NotEnoughSpaceException;
import com.deus.key.Key;

public class Game2048 implements Game{

    public static final int GAME_SIZE = 4;
    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    private static final String NOT_ENOUGH_SPACE_MESSAGE = "Not enough space";
    GameHelper helper = new GameHelper();
    Random random = new Random();


    public Game2048() {
    }

    @Override
    public void init() {
        board.fillBoard(Collections.nCopies(GAME_SIZE * GAME_SIZE, null));
        for (int i = 0; i < 2; i++) {
            try {
                addItem();
            } catch (NotEnoughSpaceException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean canMove() {
        if (!board.availableSpace().isEmpty()) {
            return true;
        }
        for (int i = 0; i < board.getHeight(); i++) {
            var rowCurrent = board.getValues(board.getRow(i));
            if (!rowCurrent.equals(helper.moveAndMergeEqual(rowCurrent))) {
                return true;
            }
        }
        for (int j = 0; j < board.getWidth(); j++) {
            var column = board.getValues(board.getColumn(j));
            if (!column.equals(helper.moveAndMergeEqual(column))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean move(Direction direction) {
        if (canMove()) {
            switch (direction) {
                case LEFT:
                    for (int i = 0; i < board.getHeight(); i++) {
                        List<Integer> rowLeft = helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
                        for (int j = 0; j < rowLeft.size(); j++) {
                            board.addItem(board.getKey(i, j), rowLeft.get(j));
                        }
                    }
                    try {
                        addItem();
                    } catch (NotEnoughSpaceException e) {
                        return false;
                    }
                    break;
                case RIGHT:
                    for (int i = 0; i < board.getHeight(); i++) {
                        List<Integer> inputList = board.getValues(board.getRow(i));
                        Collections.reverse(inputList);
                        List<Integer> rowRight = helper.moveAndMergeEqual(inputList);
                        int rowRightSize = rowRight.size();
                        for (int j = 0; j < rowRightSize; j++) {
                            board.addItem(board.getKey(i, rowRightSize - 1 - j), rowRight.get(j));
                        }
                    }
                    try {
                        addItem();
                    } catch (NotEnoughSpaceException e) {
                        return false;
                    }
                    break;
                case UP:
                    for (int j = 0; j < board.getWidth(); j++) {
                        List<Integer> colForward = helper.moveAndMergeEqual(board.getValues(board.getColumn(j)));
                        for (int i = 0; i < colForward.size(); i++) {
                            board.addItem(board.getKey(i, j), colForward.get(i));
                        }
                    }
                    try {
                        addItem();
                    } catch (NotEnoughSpaceException e) {
                        return false;
                    }
                    break;
                case DOWN:
                    for (int j = 0; j < board.getWidth(); j++) {
                        List<Integer> inputList = board.getValues(board.getColumn(j));
                        Collections.reverse(inputList);
                        List<Integer> colBack = helper.moveAndMergeEqual(inputList);
                        int colBackSize = colBack.size();
                        for (int i = 0; i < colBackSize; i++) {
                            board.addItem(board.getKey(colBackSize - 1 - i, j), colBack.get(i));
                        }
                    }
                    try {
                        addItem();
                    } catch (NotEnoughSpaceException e) {
                        return false;
                    }
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addItem() throws NotEnoughSpaceException {
        List<Key> spaces = board.availableSpace();
        if (!spaces.isEmpty() && canMove()) {
            Integer[] randomNumbers = new Integer[]{2, 4};
            var randomSuit = new Random().nextInt(randomNumbers.length);
            var randomNumber = randomNumbers[randomSuit];
            var randomKey = spaces.get(random.nextInt(spaces.size()));
            board.addItem(randomKey, randomNumber);
        } else {
            throw new NotEnoughSpaceException(NOT_ENOUGH_SPACE_MESSAGE);
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
