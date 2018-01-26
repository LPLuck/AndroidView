package com.view.lp.androidview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.view.lp.androidview.stepcount.QQStepCountActivity;
import com.view.lp.androidview.textchangecolor.TextChangeColorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void qqStepCount(View view){
        startActivity(new Intent(this, QQStepCountActivity.class));
    }
    public void onChangeTextColor(View view){
        startActivity(new Intent(this, TextChangeColorActivity.class));
    }
}
