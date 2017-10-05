package com.example.mihai.littlespice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class cirActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cir);
    }

    public void runButton(View tempView) {
        EditText tempText = (EditText) findViewById(R.id.editText);
        Intent tempIntent = new Intent(this, answerActivity.class);
        tempIntent.putExtra("raspuns", tempText.getText().toString());
        this.startActivity(tempIntent);
    }
}
