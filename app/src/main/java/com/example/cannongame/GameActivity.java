package com.example.cannongame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textPartA;
    private TextView textOperation;
    private TextView textPartB;
    private TextView textScore;
    private TextView textLevel;

    private Button buttonChoice1;
    private Button buttonChoice2;
    private Button buttonChoice3;

    private int correct_answer;

    private Toast current_toast;

    private int currentScore = 0;
    private int currentLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/


        textPartA = (TextView)this.findViewById(R.id.textPartA);
        textOperation = (TextView)this.findViewById(R.id.textOperation);
        textPartB = (TextView)this.findViewById(R.id.textPartB);
        textScore = (TextView)this.findViewById(R.id.textScore);
        textLevel = (TextView)this.findViewById(R.id.textLevel);

        buttonChoice1 = (Button) this.findViewById(R.id.buttonChoice1);
        buttonChoice2 = (Button) this.findViewById(R.id.buttonChoice2);
        buttonChoice3 = (Button) this.findViewById(R.id.buttonChoice3);

        buttonChoice1.setOnClickListener(this);
        buttonChoice2.setOnClickListener(this);
        buttonChoice3.setOnClickListener(this);

        current_toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        setQuestion();
    }

    private void setQuestion(){
        //генерируем части вопроса
        int numberRange = 9;
        Random randInt = new Random();
        int partA = randInt.nextInt(numberRange);
        partA++;//для того, чтобы не получился 0
        int partB = randInt.nextInt(numberRange);
        partB++;//для того, чтобы не получился 0

        String operator = "";

        switch (randInt.nextInt(2)){
            case 0:
                operator = "X";
                correct_answer = partA * partB;
                break;
            case 1:
                operator = "/";
                int checkVal = partA * partB;
                correct_answer = partB;
                partB = partA;
                partA = checkVal;
                break;
            default:
                correct_answer = 0;
                break;
        }

        int wrongAnswer1 = correct_answer-2;
        int wrongAnswer2 = correct_answer+2;
        textPartA.setText(partA+"");
        textPartB.setText(partB+"");
        textOperation.setText(operator);

        String choise1 = "";
        String choise2 = "";
        String choise3 = "";

        switch (randInt.nextInt(6)){
            case 0:
                choise1 = correct_answer+"";
                choise2 = wrongAnswer2+"";
                choise3 = wrongAnswer1+"";
                break;
            case 1:
                choise1 = wrongAnswer1+"";
                choise2 = correct_answer+"";
                choise3 = wrongAnswer2+"";
                break;
            case 2:
                choise1 = wrongAnswer2+"";
                choise2 = wrongAnswer1+"";
                choise3 = correct_answer+"";
                break;
            case 3:
                choise1 = correct_answer+"";
                choise2 = wrongAnswer1+"";
                choise3 = wrongAnswer2+"";
                break;
            case 4:
                choise1 = wrongAnswer2+"";
                choise2 = correct_answer+"";
                choise3 = wrongAnswer1+"";
                break;
            case 5:
                choise1 = wrongAnswer1+"";
                choise2 = wrongAnswer2+"";
                choise3 = correct_answer+"";
                break;
        }

        buttonChoice1.setText(choise1);
        buttonChoice2.setText(choise2);
        buttonChoice3.setText(choise3);
    }

    private void updateScoreAndLevel(int answerGiven){
        if(isCorrect(answerGiven)){
            for(int i = 1; i <= currentLevel; i++){
                currentScore += i;
            }
            currentLevel++;
        }
        else{
            currentScore = 0;
            currentLevel = 1;
        }
        //Отображаем текущие значения в наших TextView
        textScore.setText("Score: " + currentScore);
        textLevel.setText("Level: " + currentLevel);
    }

    private boolean isCorrect(int answerGiven) {
        current_toast.cancel();
        if (answerGiven == correct_answer){
            current_toast = current_toast.makeText(
                    this,
                    "Correct answer!",
                    Toast.LENGTH_LONG);
            current_toast.show();
            return true;
        }
        else{
            current_toast = current_toast.makeText(
                    this,
                    "Wrong answer!",
                    Toast.LENGTH_LONG);
            current_toast.show();
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        Button curButton = (Button)v;
        int answer_given = Integer.parseInt(curButton.getText().toString());
        updateScoreAndLevel(answer_given);
        setQuestion();
    }
}
