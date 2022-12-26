package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0: zero, 1: cross, 2=null

    int[] gameWin = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningposition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;
    boolean gameactive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gameWin[tappedcounter] == 2 && gameactive) {
            gameWin[tappedcounter] = activePlayer;

            counter.setTranslationY(-1000);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000).setDuration(300);

            for (int[] winning : winningposition) {
                if (gameWin[winning[0]] == gameWin[winning[1]] && gameWin[winning[1]] == gameWin[winning[2]] && gameWin[winning[0]] != 2) {
                    // some has won
                    gameactive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Zero";
                    } else {
                        winner = "cross";
                    }



                    TextView winnertextView = findViewById(R.id.Winnertextview);

                    winnertextView.setText(winner + " has won");
                    winnertextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    public void play(View view) {
        Button playAgainButton = findViewById(R.id.playAgainbutton);
        TextView winnertextView = findViewById(R.id.Winnertextview);

        winnertextView.setVisibility(View.INVISIBLE);

        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridlayout);

        for (int i = 0; i < gridlayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridlayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
            for (int z = 0; z < gameWin.length; z++) {
                gameWin[z] = 2;
            }

            activePlayer = 0;
            gameactive = true;

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}