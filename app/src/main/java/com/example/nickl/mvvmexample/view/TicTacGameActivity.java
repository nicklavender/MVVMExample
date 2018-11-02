package com.example.nickl.mvvmexample.view;

import com.example.nickl.mvvmexample.R;
import com.example.nickl.mvvmexample.databinding.ActivityTicTacGameBinding;
import com.example.nickl.mvvmexample.model.tictac.TicTacPlayer;
import com.example.nickl.mvvmexample.viewmodel.TicTacViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

public class TicTacGameActivity extends AppCompatActivity {

    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_end_tag";
    private static final String NO_WINNER = "No one";
    private TicTacViewModel mGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
        setContentView(R.layout.activity_main);
    }

    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }

    public void onPlayersSet(String player1, String player2) {
        initDataBinding(player1, player2);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityTicTacGameBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_tic_tac_game);
        mGameViewModel = ViewModelProviders.of(this).get(TicTacViewModel.class);
        mGameViewModel.init(player1, player2);
        activityGameBinding.setGameViewModel(mGameViewModel);
        setUpOnGameEndListener();
    }

    private void setUpOnGameEndListener() {
        mGameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    @VisibleForTesting
    public void onGameWinnerChanged(TicTacPlayer winner) {
        String winnerName = winner == null || winner.mName == null || winner.mName.isEmpty() ? NO_WINNER : winner.mName;
        GameEndDialog dialog = GameEndDialog.newInstance(this, winnerName);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
    }
}
