package com.example.nickl.mvvmexample.model.connectfour;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.Nullable;
import android.util.Pair;

public class ConnectFourGame {

    public static String CONNECT_FOUR_TAG = ConnectFourGame.class.getSimpleName();
    public static final int BOARD_WIDTH = 7;
    public static final int BOARD_HEIGHT = 6;

    private final String PLAYER_ONE_COLOR = "black";
    private final String PLAYER_TWO_COLOR = "red";


    public ConnectFourPlayer mPlayer1;
    public ConnectFourPlayer mPlayer2;
    public ConnectFourPlayer mCurrentPlayer;
    public ConnectFourCell[][] mCells;
    public Pair<Integer, Integer> mLastMove;

    public MutableLiveData<ConnectFourPlayer> mWinner = new MutableLiveData<>();

    public ConnectFourGame(String player1, String player2){
        mPlayer1 = new ConnectFourPlayer(player1, PLAYER_ONE_COLOR);
        mPlayer2 = new ConnectFourPlayer(player2, PLAYER_TWO_COLOR);
        mCells = new ConnectFourCell[BOARD_HEIGHT][BOARD_WIDTH];
        mCurrentPlayer = mPlayer1;
    }

    public void setLastMove(@Nullable Pair<Integer, Integer> lastMove) {
        mLastMove = lastMove;
    }

    @Nullable
    public Pair<Integer, Integer> getLastMove() {
        return mLastMove;
    }

    public void switchPlayer() {
        mCurrentPlayer = mCurrentPlayer.equals(mPlayer1) ? mPlayer2 : mPlayer1;
    }

    public boolean hasGameEnded() {
        if(hasFourInARow()) {
            mWinner.setValue(mCurrentPlayer);
            return true;
        }
        if(allCellsFull()) {
            mWinner.setValue(null);
            return true;
        }
        return false;
    }

    public void reset() {
        mPlayer1 = null;
        mPlayer2 = null;
        mCurrentPlayer = null;
        mCells = null;
    }

    boolean hasFourInARow(){
        return hasFourVertical() || hasFourHorizontal() || hasFourDiagonal();
    }

    boolean allCellsFull() {
        for(int i = 0; i < BOARD_WIDTH; i++) {
            for(int j = 0; j < BOARD_HEIGHT; j++) {
                if(mCells[j][i] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasFourVertical() {
        for(int i = 0; i < BOARD_WIDTH; i++) {
            ConnectFourPlayer startPositionPlayer = mCells[0][i] != null ? mCells[0][i].mPlayer : null;
            String color = startPositionPlayer == null ? null : startPositionPlayer.mColor;
            int fourInARowCounter = color == null ? 0 : 1;
            for (int j = 1; j < BOARD_HEIGHT; j++) {
                ConnectFourPlayer player = mCells[j][i] != null ? mCells[j][i].mPlayer : null;
                if (player != null && player.mColor.equals(color)) {
                    fourInARowCounter++;
                    if (fourInARowCounter == 4) {
                        return true;
                    }
                } else {
                    color = player == null ? null : player.mColor;
                    fourInARowCounter = player == null ? 0 : 1;
                }
            }
        }
        return false;
    }

    private boolean hasFourHorizontal() {
        for(int j = 0; j < BOARD_HEIGHT; j++) {
            ConnectFourPlayer startPositionPlayer = mCells[j][0] != null ? mCells[j][0].mPlayer : null;
            String color = startPositionPlayer == null ? null : startPositionPlayer.mColor;
            int fourInARowCounter = color == null ? 0 : 1;
            for (int i = 1; i < BOARD_WIDTH; i++) {
                ConnectFourPlayer player = mCells[j][i] != null ? mCells[j][i].mPlayer : null;
                if (player != null && player.mColor.equals(color)) {
                    fourInARowCounter++;
                    if (fourInARowCounter == 4) {
                        return true;
                    }
                } else {
                    color = player == null ? null : player.mColor;
                    fourInARowCounter = player == null ? 0 : 1;
                }
            }
        }
        return false;
    }

    private boolean hasFourDiagonal() {
        return checkDiagonalMovingLeft() || checkDiagonalMovingRight();
    }

    private boolean checkDiagonalMovingRight() {
        for(int i = 0; i < BOARD_WIDTH; i++) {
            ConnectFourPlayer startPositionPlayer = mCells[0][i] != null ? mCells[0][i].mPlayer : null;
            String color = startPositionPlayer == null ? null : startPositionPlayer.mColor;
            int fourInARowCounter = color == null ? 0 : 1;
            int col = i;
            int row = 0;
            while(col < BOARD_WIDTH && row < BOARD_HEIGHT) {
                ConnectFourPlayer player = mCells[row][col] != null ? mCells[row][col].mPlayer : null;
                if (player != null && player.mColor.equals(color)) {
                    fourInARowCounter++;
                    if (fourInARowCounter == 4) {
                        return true;
                    }
                } else {
                    color = player == null ? null : player.mColor;
                    fourInARowCounter = player == null ? 0 : 1;
                }
                col++;
                row++;
            }
        }
        return false;
    }

    private boolean checkDiagonalMovingLeft() {
        for(int i = BOARD_WIDTH - 1; i >= 0; i--) {
            ConnectFourPlayer startPositionPlayer = mCells[0][i] != null ? mCells[0][i].mPlayer : null;
            String color = startPositionPlayer == null ? null : startPositionPlayer.mColor;
            int fourInARowCounter = color == null ? 0 : 1;
            int col = i;
            int row = 0;
            while(col >= 0 && row < BOARD_HEIGHT) {
                ConnectFourPlayer player = mCells[row][col] != null ? mCells[row][col].mPlayer : null;
                if (player != null && player.mColor.equals(color)) {
                    fourInARowCounter++;
                    if (fourInARowCounter == 4) {
                        return true;
                    }
                } else {
                    color = player == null ? null : player.mColor;
                    fourInARowCounter = player == null ? 0 : 1;
                }
                col--;
                row++;
            }
        }
        return false;
    }

}
