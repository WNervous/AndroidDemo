package com.meevii.dialogsizechange.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.meevii.dialogsizechange.R;

public class GameCompleteFragment extends DialogFragment {
    public static final String KEY_GAME_SCORE = "key_game_score";
    public static final String KEY_GAME_TOTAL_SCORE = "key_game_total_score";
    public static final String KEY_GAME_CHORDS = "key_game_chords";
    public static final String KEY_GAME_MUSIC = "key_game_music";
    ImageView closeImg;


    public static GameCompleteFragment newInstance() {
        Bundle args = new Bundle();
        GameCompleteFragment fragment = new GameCompleteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("GameCompleteFragment", "create");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("GameCompleteFragment", "onStart");
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_game_complete_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (getDialog().isShowing()) {
                    dismissAllowingStateLoss();
                }
                return false;
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Dialog dialog = getDialog();
            if (dialog != null) {
                DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        }
//        newConfig.orientation=Configuration.ORIENTATION_LANDSCAPE;
        Log.d("GameCompleteFragment", "onConfigurationChanged:" + newConfig.orientation);
    }
}
