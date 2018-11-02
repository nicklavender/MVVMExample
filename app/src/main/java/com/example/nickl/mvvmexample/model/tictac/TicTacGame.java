package com.example.nickl.mvvmexample.model.tictac;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

@SuppressWarnings("ProhibitedExceptionCaught")
public class TicTacGame {

    private static final String TAG = TicTacGame.class.getSimpleName();
    private static final int BOARD_SIZE = 3;

    public TicTacPlayer mPlayer1;
    public TicTacPlayer mPlayer2;

    public TicTacPlayer mCurrentPlayer = mPlayer1;
    public TicTacCell[][] mCells;

    public MutableLiveData<TicTacPlayer> mWinner = new MutableLiveData<>();

    public TicTacGame(String playerOne, String playerTwo) {
        mCells = new TicTacCell[BOARD_SIZE][BOARD_SIZE];
        mPlayer1 = new TicTacPlayer(playerOne, "x");
        mPlayer2 = new TicTacPlayer(playerTwo, "o");
        mCurrentPlayer = mPlayer1;
    }

    public void switchPlayer() {
        mCurrentPlayer = mCurrentPlayer.equals(mPlayer1) ? mPlayer2 : mPlayer1;
    }

    public boolean hasGameEnded() {
        if(hasThreeSameHorizontalCells() || hasThreeVerticalCells() || hasThreeDiagonalCells()) {
            mWinner.setValue(mCurrentPlayer);
            return true;
        }
        if(isBoardFull()) {
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
    private boolean hasThreeSameHorizontalCells() {
            for (int i = 0; i < BOARD_SIZE; i++) {
                String first = mCells[0][i] != null ? mCells[0][i].mPlayer.mValue : "null";
                String second = mCells[1][i] != null ? mCells[1][i].mPlayer.mValue : "null";
                String third = mCells[2][i] != null ? mCells[2][i].mPlayer.mValue : "null";
                Log.d("nwl", first + " " + second + " " + third);
                if (areEqual(mCells[0][i], mCells[1][i], mCells[2][i])) {
                    Log.e("NWL", "returning true here");
                    return true;
                }
            }
            return false;
    }

    private boolean hasThreeVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (areEqual(mCells[i][0], mCells[i][1], mCells[i][2])) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    private boolean hasThreeDiagonalCells() {
        try {
            return areEqual(mCells[0][0], mCells[1][1], mCells[2][2]) || areEqual(mCells[0][2], mCells[1][1], mCells[2][0]);
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    private boolean isBoardFull() {
        for(TicTacCell[] row : mCells) {
            for(TicTacCell cell : row) {
                if(cell == null || cell.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 2 mConnectFourCells are equal if:
     * -Both are none null
     * -Both have equal non null values
     * @param cells Cells to check if they are equal
     * @return true if mConnectFourCells are equal by above definition, false otherwise.
     */
    private boolean areEqual(TicTacCell... cells) {
        if (cells == null || cells.length == 0) {
            return false;
        }
        for (TicTacCell cell : cells) {
            if (cell == null || cell.mPlayer.mValue == null || cell.mPlayer.mValue.isEmpty()) {
                return false;
            }
        }
        TicTacCell comparisonBase = cells[0];
        for (int i = 1; i < cells.length; i++) {
            if (!comparisonBase.mPlayer.mValue.equals(cells[i].mPlayer.mValue)) {
                return false;
            }
        }

        return true;
    }

}
