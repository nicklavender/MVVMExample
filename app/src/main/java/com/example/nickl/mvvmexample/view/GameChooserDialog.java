package com.example.nickl.mvvmexample.view;

import com.example.nickl.mvvmexample.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class GameChooserDialog extends DialogFragment {

    private GameChooserActivity mActivity;
    private View mRootView;

    public static GameChooserDialog newInstance(GameChooserActivity activity) {
        GameChooserDialog dialog = new GameChooserDialog();
        dialog.mActivity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(mRootView)
                .setTitle("Choose Game")
                .setCancelable(false)
                .setPositiveButton("Tic Tac", null)
                .setNegativeButton("Connect Four", null)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(true);
        alertDialog.setOnShowListener(dialog -> onDialogShow(alertDialog));
        return alertDialog;
    }

    private void initViews() {
        mRootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_chooser_dialog, null, false);
    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> ticTacClicked());
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(v -> connectFourClicked());
    }

    private void ticTacClicked() {
        mActivity.launchTicTac();
    }

    private void connectFourClicked() {
        mActivity.launchConnectFour();
    }
}
