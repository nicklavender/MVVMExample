package com.example.nickl.mvvmexample.viewmodel;

import com.example.nickl.mvvmexample.model.connectfour.ConnectFourCell;
import com.example.nickl.mvvmexample.model.connectfour.ConnectFourGame;
import com.example.nickl.mvvmexample.model.connectfour.ConnectFourPlayer;
import com.example.nickl.mvvmexample.utilities.StringUtilities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;
import android.util.Pair;

public class ConnectFourViewModel extends ViewModel {

    public ObservableArrayMap<String, String> mConnectFourCells;
    private ConnectFourGame mConnectFourGame;

    public void init(String player1, String player2) {
        mConnectFourGame = new ConnectFourGame(player1, player2);
        mConnectFourCells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
            int droppedRow = row;
            for (int i = 0; i < ConnectFourGame.BOARD_HEIGHT; i++) {
                if (mConnectFourGame.mCells[i][column] == null) {
                    droppedRow = i;
                }
            }
            if (mConnectFourGame.mCells[droppedRow][column] == null) {
                mConnectFourGame.setLastMove(new Pair<>(droppedRow, column));
                mConnectFourGame.mCells[droppedRow][column] = new ConnectFourCell(mConnectFourGame.mCurrentPlayer);
                mConnectFourCells.put(StringUtilities.stringFromNumbers(droppedRow, column), mConnectFourGame.mCurrentPlayer.mColor);
                if (mConnectFourGame.hasGameEnded()) {
                    mConnectFourGame.reset();
                } else {
                    mConnectFourGame.switchPlayer();
                }
            }
    }

    public void undoLastMove() {
        Pair<Integer, Integer> lastMove = mConnectFourGame.getLastMove();
        if(lastMove != null) {
            int row = lastMove.first;
            int column = lastMove.second;
            mConnectFourGame.mCells[row][column] = null;
            mConnectFourCells.put(StringUtilities.stringFromNumbers(row, column), null);
            mConnectFourGame.switchPlayer();
            mConnectFourGame.setLastMove(null);
        }
    }

    public LiveData<ConnectFourPlayer> getWinner() {
        return mConnectFourGame.mWinner;
    }
}
