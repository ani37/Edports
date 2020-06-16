
package com.example.aniketagarwalla.task1;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class PracticeBrainBuzz extends AppCompatActivity {
    private static final String TAG ="hello" ;
    public static final String DIFFICULTY_EASY = "45 Second";
    public static final String DIFFICULTY_MEDIUM = "1 Minute";
    public static final String DIFFICULTY_HARD = "2 Minute";
    String difficulty,message;
    private Spinner spinnerDifficulty;
    Button button;
    TextView result,points,sum,timer;
    Button button1,button2,button3,button4,playAgainButton;
    ConstraintLayout gameRelativeLayout;
    ArrayList<Integer> answers= new ArrayList<>();
    int locationOfCorrectAnswer,score=0,numberOfQuestions=0;
    public static String[] getAllDifficultyLevels() {
        return new String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };
    }
    public void generateQuestion()
    {
        Random rand =new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(22)+1;
        String str;
        switch (message) {
            case "addition": {



                str = Integer.toString(a) + "+" + Integer.toString(b);
                sum.setText(str);
                locationOfCorrectAnswer = rand.nextInt(4);
                int incorrectAnswer;
                answers.clear();
                for (int i = 0; i < 4; i++) {
                    if (i == locationOfCorrectAnswer) {
                        answers.add(a + b);
                    } else {
                        incorrectAnswer = rand.nextInt(41);
                        while (incorrectAnswer == a + b) {
                            incorrectAnswer = rand.nextInt(41);

                        }
                        answers.add(incorrectAnswer);
                    }
                }
                break;
            }
            case "multipy": {
                    str = Integer.toString(a) + " x " + Integer.toString(b);
                    sum.setText(str);
                    locationOfCorrectAnswer = rand.nextInt(4);
                    int incorrectAnswer;
                    answers.clear();
                    for (int i = 0; i < 4; i++) {
                        if (i == locationOfCorrectAnswer) {
                            answers.add(a * b);
                        } else if (i == 0) {
                            incorrectAnswer = rand.nextInt(a * b + 20) + a * b - 10;
                            while (incorrectAnswer == a * b) {
                                incorrectAnswer = rand.nextInt(a * b + 20) + a * b - 10;

                            }
                            answers.add(incorrectAnswer);
                        } else {
                            incorrectAnswer = a * b + i * 10;
                            answers.add(incorrectAnswer);
                        }

                    }
                break;
                }
            case "division": {
                int x = a*b;


                str = Integer.toString(x) + " / " + Integer.toString(b);
                sum.setText(str);
                locationOfCorrectAnswer = rand.nextInt(4);
                int incorrectAnswer;
                answers.clear();
                for (int i = 0; i < 4; i++)
                {
                    if (i == locationOfCorrectAnswer)
                    {
                        answers.add(a);
                    } else {
                        incorrectAnswer = rand.nextInt(a + 11) + a - 3;
                        while (incorrectAnswer == a) {
                            incorrectAnswer = rand.nextInt(a + 11) + a - 3;

                        }
                        answers.add(incorrectAnswer);
                    }
                }
                break;
            }
            default: {
                a = rand.nextInt(41);
                b = rand.nextInt(41);
                if (a < b) {
                    a = a + b;
                    b = a - b;
                    a = a - b;
                }
                str = Integer.toString(a) + "-" + Integer.toString(b);
                sum.setText(str);
                locationOfCorrectAnswer = rand.nextInt(4);
                int incorrectAnswer;
                answers.clear();
                for (int i = 0; i < 4; i++) {
                    if (i == locationOfCorrectAnswer) {
                        answers.add(a - b);
                    } else {
                        incorrectAnswer = rand.nextInt(41);
                        while (incorrectAnswer == a - b) {
                            incorrectAnswer = rand.nextInt(41);

                        }
                        answers.add(incorrectAnswer);
                    }
                }
                break;
            }
        }
        str = Integer.toString(answers.get(0));
        button1.setText(str);
        str = Integer.toString(answers.get(1));
        button2.setText(str);
        str = Integer.toString(answers.get(2));
        button3.setText(str);
        str = Integer.toString(answers.get(3));
        button4.setText(str);


    }
    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        score=0;
        int time = 0;
        switch (difficulty) {
            case DIFFICULTY_EASY:
                timer.setText("45s");
                time = 45100;
                break;
            case DIFFICULTY_MEDIUM:
                timer.setText("60s");
                time = 60100;
                break;
            case DIFFICULTY_HARD:

                timer.setText("120s");
                time = 120100;
                break;
        }
        numberOfQuestions = 0;
        points.setText("00/00");
        result.setText("");
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        generateQuestion();

        new CountDownTimer(time,1000){

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
                //      updatedata(difficulty,score,numberOfQuestions);
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
            score--;
            if(score<0)
                score=0;

        }
        numberOfQuestions++;
        String st=Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions);
        points.setText(st);
        generateQuestion();

    }
    public void start(View view){
        difficulty = spinnerDifficulty.getSelectedItem().toString();
        spinnerDifficulty.setVisibility(View.INVISIBLE);
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
        spinnerDifficulty=findViewById(R.id.difficulty);
        //intent
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        message = bundle.getString("message");
     //   Log.d("any",message);
     //   Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        String[] levels =getAllDifficultyLevels();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,levels);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(arrayAdapter);

    }
}
