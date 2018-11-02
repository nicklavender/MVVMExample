package com.example.nickl.mvvmexample.view;

import com.example.nickl.mvvmexample.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class GameBeginDialog extends DialogFragment {

    private TextInputLayout mPlayer1Layout;
    private TextInputLayout mPlayer2Layout;

    private TextInputEditText mPlayer1EditText;
    private TextInputEditText mPlayer2EditText;

    private String mPlayer1;
    private String mPlayer2;

    private View mRootView;
    private AppCompatActivity mActivity;

    public static GameBeginDialog newInstance(AppCompatActivity activity) {
        GameBeginDialog dialog = new GameBeginDialog();
        dialog.mActivity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(mRootView)
                .setTitle(R.string.game_dialog_title)
                .setCancelable(false)
                .setPositiveButton(R.string.done, null)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setOnShowListener(dialog -> onDialogShow(alertDialog));
        return alertDialog;
    }

    private void initViews() {
        mRootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_begin_dialog, null, false);

        mPlayer1Layout = mRootView.findViewById(R.id.layout_player1);
        mPlayer2Layout = mRootView.findViewById(R.id.layout_player2);

        mPlayer1EditText = mRootView.findViewById(R.id.et_player1);
        mPlayer2EditText = mRootView.findViewById(R.id.et_player2);
        addTextWatchers();
    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            onDoneClicked();
        });
    }

    private void onDoneClicked() {
        if (isAValidName(mPlayer1Layout, mPlayer1) && isAValidName(mPlayer2Layout, mPlayer2)) {
            if(mActivity instanceof TicTacGameActivity) {
                ((TicTacGameActivity)mActivity).onPlayersSet(mPlayer1, mPlayer2);
            }
            if(mActivity instanceof ConnectFourGameActivity) {
                ((ConnectFourGameActivity)mActivity).onPlayersSet(mPlayer1, mPlayer2);
            }
            dismiss();
        }
    }

    private boolean isAValidName(TextInputLayout layout, String name) {
        if (TextUtils.isEmpty(name)) {
            layout.setErrorEnabled(true);
            layout.setError(getString(R.string.game_dialog_empty_name));
            return false;
        }

        if (mPlayer1 != null && mPlayer2 != null && mPlayer1.equalsIgnoreCase(mPlayer2)) {
            layout.setErrorEnabled(true);
            layout.setError(getString(R.string.game_dialog_same_names));
            return false;
        }

        layout.setErrorEnabled(false);
        layout.setError("");
        return true;
    }

    private void addTextWatchers() {
        mPlayer1EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPlayer1 = s.toString();
            }
        });
        mPlayer2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPlayer2 = s.toString();
            }
        });
    }
}

