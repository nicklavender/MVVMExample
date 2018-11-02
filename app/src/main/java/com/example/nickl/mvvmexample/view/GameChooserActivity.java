package com.example.nickl.mvvmexample.view;


import com.example.nickl.mvvmexample.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class GameChooserActivity extends AppCompatActivity {

    private static final String GAME_CHOOSE_DIALOG_TAG = "game_choose_dialog";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptGameChoice();
        setContentView(R.layout.activity_main);
    }

    private void promptGameChoice() {
        GameChooserDialog dialog = GameChooserDialog.newInstance(this);
        dialog.show(getSupportFragmentManager(), GAME_CHOOSE_DIALOG_TAG);
    }

    void launchTicTac() {
        Intent intent = new Intent(this, TicTacGameActivity.class);
        startActivity(intent);
    }

    void launchConnectFour() {
        Intent intent = new Intent(this, ConnectFourGameActivity.class);
        startActivity(intent);
    }
}
