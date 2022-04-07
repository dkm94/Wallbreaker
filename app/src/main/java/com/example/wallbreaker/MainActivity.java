package com.example.wallbreaker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameView game = new GameView(this); // nouvelle instance de GameView qui s'appelle game
        game.setBackgroundColor(Color.BLACK);
        setContentView(game);
    }
}