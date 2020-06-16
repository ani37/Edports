
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

public class BrainBuzz extends AppCompatActivity {
    private static final String TAG ="hello" ;
    public static final String DIFFICULTY_EASY = "45 Second";
    public static final String DIFFICULTY_MEDIUM ="1 Minute";
    public static final String DIFFICULTY_HARD =  "2 Minute";
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    String currentuser,difficulty,message;
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
        switch (message) {
            case "amateur": {

                int a = rand.nextInt(22) +10;
                int b = rand.nextInt(22) + 5;
                int choice = rand.nextInt(4);
                String str;
                if (choice == 0) {
                    str = Integer.toString(a) + " + " + Integer.toString(b);
                    sum.setText(str);
                    locationOfCorrectAnswer = rand.nextInt(4);
                    int incorrectAnswer;
                    answers.clear();
                    for (int i = 0; i < 4; i++) {
                        if (i == locationOfCorrectAnswer) {
                            answers.add(a + b);
                        } else {
                            incorrectAnswer = rand.nextInt(41) + 5;
                            while (incorrectAnswer == a + b) {
                                incorrectAnswer = rand.nextInt(41) + 5;

                            }
                            answers.add(incorrectAnswer);
                        }
                    }
                } else if (choice == 1) {
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
                }
                else if (choice == 2) {
                    a = rand.nextInt(41) + 10;
                    b = rand.nextInt(41) + 10;
                    if (a < b) {
                        a = a + b;
                        b = a - b;
                        a = a - b;
                    }
                    str = Integer.toString(a) + " - " + Integer.toString(b);
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
                }
                else if (choice == 3)
                {
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
                }
                str = Integer.toString(answers.get(0));
                button1.setText(str);
                str = Integer.toString(answers.get(1));
                button2.setText(str);
                str = Integer.toString(answers.get(2));
                button3.setText(str);
                str = Integer.toString(answers.get(3));
                button4.setText(str);


                break;
            }
            case "expert": {

                int a = rand.nextInt(31)+ 15;
                int b = rand.nextInt(31)+10;
                int choice = rand.nextInt(4);
                String str;
                if (choice == 0) {
                    str = Integer.toString(a) + " + " + Integer.toString(b);
                    sum.setText(str);
                    locationOfCorrectAnswer = rand.nextInt(4);
                    int incorrectAnswer;
                    answers.clear();
                    for (int i = 0; i < 4; i++) {
                        if (i == locationOfCorrectAnswer) {
                            answers.add(a + b);
                        } else {
                            incorrectAnswer = rand.nextInt(61) + 10;
                            while (incorrectAnswer == a + b) {
                                incorrectAnswer = rand.nextInt(61) + 10;

                            }
                            answers.add(incorrectAnswer);
                        }
                    }
                } else if (choice == 1)
                {
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
                }
                else if (choice == 2) {
                    a = rand.nextInt(61) +15;
                    b = rand.nextInt(61) + 15;
                    if (a < b) {
                        a = a + b;
                        b = a - b;
                        a = a - b;
                    }
                    str = Integer.toString(a) + " - " + Integer.toString(b);
                    sum.setText(str);
                    locationOfCorrectAnswer = rand.nextInt(4);
                    int incorrectAnswer;
                    answers.clear();
                    for (int i = 0; i < 4; i++) {
                        if (i == locationOfCorrectAnswer) {
                            answers.add(a - b);
                        } else {
                            incorrectAnswer = rand.nextInt(61);
                            while (incorrectAnswer == a - b) {
                                incorrectAnswer = rand.nextInt(61);

                            }
                            answers.add(incorrectAnswer);
                        }
                    }
                }
                else if (choice == 3)
                {
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
                }
                str = Integer.toString(answers.get(0));
                button1.setText(str);
                str = Integer.toString(answers.get(1));
                button2.setText(str);
                str = Integer.toString(answers.get(2));
                button3.setText(str);
                str = Integer.toString(answers.get(3));
                button4.setText(str);


                break;
            }
            default: {

                int a = rand.nextInt(12);
                int b = rand.nextInt(13)+1;
                int choice = rand.nextInt(4);
                String str;
                if (choice == 0) {
                    str = Integer.toString(a) + " + " + Integer.toString(b);
                    sum.setText(str);
                    locationOfCorrectAnswer = rand.nextInt(4);
                    int incorrectAnswer;
                    answers.clear();
                    for (int i = 0; i < 4; i++) {
                        if (i == locationOfCorrectAnswer) {
                            answers.add(a + b);
                        } else {
                            incorrectAnswer = rand.nextInt(25);
                            while (incorrectAnswer == a + b) {
                                incorrectAnswer = rand.nextInt(25);

                            }
                            answers.add(incorrectAnswer);
                        }
                    }
                } else if (choice == 1) {
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
                }
                else if (choice == 2) {
                    a = rand.nextInt(21);
                    b = rand.nextInt(21);
                    if (a < b) {
                        a = a + b;
                        b = a - b;
                        a = a - b;
                    }
                    str = Integer.toString(a) + " - " + Integer.toString(b);
                    sum.setText(str);
                    locationOfCorrectAnswer = rand.nextInt(4);
                    int incorrectAnswer;
                    answers.clear();
                    for (int i = 0; i < 4; i++) {
                        if (i == locationOfCorrectAnswer) {
                            answers.add(a - b);
                        } else {
                            incorrectAnswer = rand.nextInt(21);
                            while (incorrectAnswer == a - b) {
                                incorrectAnswer = rand.nextInt(21);

                            }
                            answers.add(incorrectAnswer);
                        }
                    }
                }
                else if (choice == 3)
                {
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
                }
                str = Integer.toString(answers.get(0));
                button1.setText(str);
                str = Integer.toString(answers.get(1));
                button2.setText(str);
                str = Integer.toString(answers.get(2));
                button3.setText(str);
                str = Integer.toString(answers.get(3));
                button4.setText(str);


                break;
            }
        }
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
    public void addUser(){

        Map<String, Integer> user = new HashMap<>();
        user.put("easy", 0);
        user.put("medium", 0);
        user.put("hard", 0);
        user.put("easy1", 0);
        user.put("medium1",0);
        user.put("hard1", 0);
        db.collection("users").document(currentuser)
                .collection("Brainbuzz").document("score").set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                });
    }

    private void checkIfUserAlreadyExitsOrCreateOne(){

        db.collection("users").document(currentuser)
                .collection("Brainbuzz").document("score").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    assert documentSnapshot != null;
                    Log.e(TAG, String.valueOf(documentSnapshot));
                    if (!documentSnapshot.exists())
                    {
                        addUser();
                        Log.e(TAG, "not Exits.........");
                    }else{

                        Log.e(TAG,"Exits.........");
                    }
                }
            });
    }
    private void updatedata(final String difficulty, final int score, final int num) {


        db.collection("users").document(currentuser)
                .collection("Brainbuzz").document("score").get().addOnCompleteListener(new OnCompleteListener < DocumentSnapshot > () {

            @Override

            public void onComplete(@NonNull Task < DocumentSnapshot > task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot doc = task.getResult();
                    assert doc != null;
                    long sc = (long) doc.get(difficulty);
                    String st= difficulty +'1';
                    long num1= (long) doc.get(st);
                    Log.d("score", String.valueOf(sc));
                    Log.d("no", String.valueOf(num1));

                        if(sc<score || (sc==score && num1>num ))
                        {
                            db.collection("users").document(currentuser)
                                    .collection("Brainbuzz").document("score").update(difficulty,score);
                            db.collection("users").document(currentuser)
                                    .collection("Brainbuzz").document("score").update(st,num);
                            Toast.makeText(BrainBuzz.this,"Your highest score : " + score + " in turns : " + num, Toast.LENGTH_SHORT).show();


                        }


                }

            }

        })

                .addOnFailureListener(new OnFailureListener() {

                    @Override

                    public void onFailure(@NonNull Exception e) {

                    }

                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_buzz);
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
        //firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        currentuser = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        checkIfUserAlreadyExitsOrCreateOne();

        String[] levels =getAllDifficultyLevels();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,levels);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(arrayAdapter);

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

                BrainBuzz.super.onBackPressed();
            }
        });
        builder.show();
    }
}
