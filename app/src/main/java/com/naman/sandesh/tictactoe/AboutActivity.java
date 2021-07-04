package com.naman.sandesh.tictactoe;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.naman.sandesh.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void BackToMain(View view) {
        Intent intent = new Intent(getApplicationContext(), com.naman.sandesh.tictactoe.MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
