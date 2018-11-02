package com.example.nickl.mvvmexample.view;

import com.example.nickl.mvvmexample.R;
import com.example.nickl.mvvmexample.databinding.ActivityConnectFourGameBinding;
import com.example.nickl.mvvmexample.model.connectfour.ConnectFourPlayer;
import com.example.nickl.mvvmexample.viewmodel.ConnectFourViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ConnectFourGameActivity extends AppCompatActivity {

    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_end_tag";
    private static final String NO_WINNER = "No one";
    private ConnectFourViewModel mConnectFourViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.connect_four_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int switchItem = item.getItemId();
        switch (switchItem) {
            case R.id.undo: {
                mConnectFourViewModel.undoLastMove();
                break;
            }
            case R.id.game_select: {
                //TODO: back to game select
            }
        }
        return true;
    }

    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }

    public void onPlayersSet(String player1, String player2) {
        initDataBinding(player1, player2);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityConnectFourGameBinding activityConnectFourGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_connect_four_game);
        mConnectFourViewModel = ViewModelProviders.of(this).get(ConnectFourViewModel.class);
        mConnectFourViewModel.init(player1, player2);
        activityConnectFourGameBinding.setConnectFourViewModel(mConnectFourViewModel);
        setUpOnGameEndListener();
    }

    private void setUpOnGameEndListener() {
        mConnectFourViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    public void onGameWinnerChanged(ConnectFourPlayer winner) {
        String winnerName = winner == null || winner.mName == null || winner.mName.isEmpty() ? NO_WINNER : winner.mName;
        GameEndDialog dialog = GameEndDialog.newInstance(this, winnerName);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
    }

}
