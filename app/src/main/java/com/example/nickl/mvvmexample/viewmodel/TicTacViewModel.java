package com.example.nickl.mvvmexample.viewmodel;

import com.example.nickl.mvvmexample.model.tictac.TicTacCell;
import com.example.nickl.mvvmexample.model.tictac.TicTacGame;
import com.example.nickl.mvvmexample.model.tictac.TicTacPlayer;
import com.example.nickl.mvvmexample.utilities.StringUtilities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;

public class TicTacViewModel extends ViewModel {

    public ObservableArrayMap<String, String> mCells;
    private TicTacGame mGame;

    public void init(String player1, String player2) {
        mGame = new TicTacGame(player1, player2);
        mCells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if(mGame.mCells[row][column] == null) {
            mGame.mCells[row][column] = new TicTacCell(mGame.mCurrentPlayer);
            mCells.put(StringUtilities.stringFromNumbers(row, column), mGame.mCurrentPlayer.mValue);
        }
        if(mGame.hasGameEnded()) {
            mGame.reset();
        } else {
            mGame.switchPlayer();
        }
    }

    public LiveData<TicTacPlayer> getWinner() {
        return mGame.mWinner;
    }



}
