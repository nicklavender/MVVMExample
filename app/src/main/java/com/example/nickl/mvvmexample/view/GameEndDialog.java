package com.example.nickl.mvvmexample.view;

import com.example.nickl.mvvmexample.R;
import com.example.nickl.mvvmexample.model.connectfour.ConnectFourPlayer;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class GameEndDialog extends DialogFragment {

    private View mRootView;
    private AppCompatActivity mActivity;
    private String mWinnerName;

    public static GameEndDialog newInstance(AppCompatActivity activity, String winnerName) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.mActivity = activity;
        dialog.mWinnerName = winnerName;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setView(mRootView)
                .setCancelable(false)
                .setPositiveButton(R.string.done, (dialog, which) -> onNewGame())
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    private void initViews() {
        mRootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);
        ((TextView) mRootView.findViewById(R.id.tv_winner)).setText(mWinnerName);
    }

    private void onNewGame() {
        dismiss();
        if(mActivity instanceof TicTacGameActivity) {
            ((TicTacGameActivity)mActivity).promptForPlayers();
        }
        if(mActivity instanceof ConnectFourGameActivity) {
            ((ConnectFourGameActivity)mActivity).promptForPlayers();
        }
    }

}
