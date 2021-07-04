package com.naman.sandesh.tictactoe;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.naman.sandesh.MainActivity;
import com.naman.sandesh.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void startGame_SinglePlayer(View view){
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
    }

    public void EndGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void ShowAboutNote(View view) {
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
    }

    public void StartGameOnline(View view) {
        Intent intent = new Intent(getApplicationContext(), com.naman.sandesh.tictactoe.OnlineLoginActivity.class);
        startActivity(intent);
    }
}
