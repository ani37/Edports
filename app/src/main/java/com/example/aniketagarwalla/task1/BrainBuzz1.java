package com.example.aniketagarwalla.task1;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BrainBuzz1 extends AppCompatActivity {

    Button button;
    TextView result,points,sum,timer;
    Button button1,button2,button3,button4,playAgainButton;
    ConstraintLayout gameRelativeLayout;
    ArrayList<Integer> answers= new ArrayList<>();
    int locationOfCorrectAnswer,score=0,numberOfQuestions=0;
    public void generateQuestion(){
        Random rand =new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        String str=Integer.toString(a)+" x " + Integer.toString(b);
        sum.setText(str);
        locationOfCorrectAnswer=rand.nextInt(4);
        int incorrectAnswer;
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a*b);
            }
            else {
                incorrectAnswer=rand.nextInt(41);
                while (incorrectAnswer==a*b){
                    incorrectAnswer=rand.nextInt(41);

                }
                answers.add(incorrectAnswer);
            }
        }
        str=Integer.toString(answers.get(0));
        button1.setText(str);
        str=Integer.toString(answers.get(1));
        button2.setText(str);
        str=Integer.toString(answers.get(2));
        button3.setText(str);
        str=Integer.toString(answers.get(3));
        button4.setText(str);



    }
    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        score=0;
        numberOfQuestions=0;
        timer.setText("30s");
        points.setText("00/00");
        result.setText("");
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        generateQuestion();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                String s=String.valueOf(millisUntilFinished/1000)+"s";
                timer.setText(s);
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timer.setText("0s");
                String string="Your score:"+ Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions);
                result.setText(string);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);

            }
        }.start();

    }
    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            result.setText("Correct!");
        }
        else {
            result.setText("Wrong!");
        }
        numberOfQuestions++;
        String st=Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions);
        points.setText(st);
        generateQuestion();

    }
    public void start(View view){
        button.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_buzz1);
        button= findViewById(R.id.button);
        button1= findViewById(R.id.button1);
        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        button4= findViewById(R.id.button4);
        result= findViewById(R.id.result);
        points= findViewById(R.id.points);
        sum= findViewById(R.id.sum);
        timer= findViewById(R.id.timer);
        playAgainButton= findViewById(R.id.playAgainButton);
        gameRelativeLayout= findViewById(R.id.gameRelativeLayout);

    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });
        builder.setMessage("Do you want to exit? ");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                BrainBuzz1.super.onBackPressed();
            }
        });
        builder.show();
    }
}
