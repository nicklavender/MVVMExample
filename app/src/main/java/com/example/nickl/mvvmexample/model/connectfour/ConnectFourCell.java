package com.example.nickl.mvvmexample.model.connectfour;

public class ConnectFourCell {

    public ConnectFourPlayer mPlayer;

    public ConnectFourCell(ConnectFourPlayer player) {
        mPlayer = player;
    }

    public boolean isEmpty() {
        return mPlayer == null;
    }
}
