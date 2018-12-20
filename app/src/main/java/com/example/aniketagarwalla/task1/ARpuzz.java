package com.example.aniketagarwalla.task1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ARpuzz extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[4][4];

    private boolean khiladi1Turn = true;

    private int roundCount;

    private int khiladi1Points;
    private int khiladi2Points;

    private TextView textViewkhiladi1;
    private TextView textViewkhiladi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arpuzz);

        textViewkhiladi1 = findViewById(R.id.text_view_k1);
        textViewkhiladi2 = findViewById(R.id.text_view_k2);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (khiladi1Turn) {
            ((Button) v).setText("A");
        } else {
            ((Button) v).setText("R");
        }

        roundCount++;

        if (checkForWin()) {
            if (khiladi1Turn) {
                khiladi1Wins();
            } else {
                khiladi2Wins();
            }
        } else if (roundCount == 16) {
            draw();
        } else {
            khiladi1Turn = !khiladi1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 4; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2]) && field[i][0].equals(field[i][3])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i]) && field[0][i].equals(field[3][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2]) && field[0][0].equals(field[3][3])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][3].equals(field[1][2])
                && field[0][3].equals(field[2][1]) && field[0][3].equals(field[3][0])
                && !field[0][3].equals("")) {
            return true;
        }

        return false;
    }

    private void khiladi1Wins() {
        khiladi1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void khiladi2Wins() {
        khiladi2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        String str= "PLAYER 1: " + khiladi1Points + " ";
        textViewkhiladi1.setText(str);
        str= "PLAYER 2: " + khiladi2Points + " ";
        textViewkhiladi2.setText(str);
    }

    private void resetBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        khiladi1Turn = true;
    }

    private void resetGame() {
        khiladi1Points = 0;
        khiladi2Points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("khiladi1Points", khiladi1Points);
        outState.putInt("khiladi2Points", khiladi2Points);
        outState.putBoolean("khiladi1Turn", khiladi1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        khiladi1Points = savedInstanceState.getInt("khiladi1Points");
        khiladi2Points = savedInstanceState.getInt("khiladi2Points");
        khiladi1Turn = savedInstanceState.getBoolean("khiladi1Turn");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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

                ARpuzz.super.onBackPressed();
            }
        });
        builder.show();
    }
}