package com.example.cannongame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonPlay;
    private Button buttonHighScore;
    private Button buttonQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonHighScore = (Button)findViewById(R.id.buttonHighScore);
        buttonQuit = (Button)findViewById(R.id.buttonQuit);

        final MainActivity activity = this;

        buttonPlay.setOnClickListener(this);
        buttonHighScore.setOnClickListener(this);
        buttonQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button curButton = (Button)v;
        switch (curButton.getId()){
            case R.id.buttonPlay:
                OnClickPlay(curButton);
                break;
            case R.id.buttonHighScore:
                OnClickHighScore(curButton);
                break;
            case R.id.buttonQuit:
                OnClickQuit(curButton);
                break;
        }
    }

    private void OnClickPlay(Button button){
        Intent i;
        i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    private void OnClickHighScore(Button button){


    }

    private void OnClickQuit(Button button){
        System.exit(0);
    }

}
