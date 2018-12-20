package com.example.aniketagarwalla.task1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuessNum extends AppCompatActivity {

    int randomNumber,guessInt;
    int count=0;
    public void makeToast(String string){
        Toast.makeText(this,string, Toast.LENGTH_SHORT).show();
    }
    public void guess(View view){

        TextView textView= findViewById(R.id.textView3);
        textView.setText("");
        EditText guessEditText = findViewById(R.id.guessEditText);
        if(guessEditText.getText().toString().isEmpty())
            makeToast("Enter a number!");
        else {
            guessInt = Integer.parseInt(guessEditText.getText().toString());

            count++;
            if (guessInt > 25 || guessInt < 1) {
                makeToast("Aakal ke andah!!Thik se type kar");
            } else if (guessInt > randomNumber) {

                makeToast("GO LOWER!");
            } else if (guessInt < randomNumber) {

                makeToast("GO HIGHER!");
            } else {
                makeToast("PERFECT!!");
                String s="You found the number in "  + count +" turns.";
                textView.setText(s);
                makeToast("Play Again!");
                Random random = new Random();
                randomNumber = random.nextInt(25) + 1;
                guessEditText.setText("");
                count=0;

            }
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_num);
        Random rand = new Random();
        randomNumber =rand.nextInt(25)+1;
        Button button= findViewById(R.id.guess);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess(v);
            }
        });
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

                GuessNum.super.onBackPressed();
            }
        });
        builder.show();
    }
}
