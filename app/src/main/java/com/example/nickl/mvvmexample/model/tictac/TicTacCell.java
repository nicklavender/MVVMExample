package com.example.nickl.mvvmexample.model.tictac;

public class TicTacCell {

    public TicTacPlayer mPlayer;

    public TicTacCell(TicTacPlayer player) {
        mPlayer = player;
    }

    public boolean isEmpty() {
        return mPlayer == null || mPlayer.mValue == null || mPlayer.mValue.isEmpty();
    }
}
