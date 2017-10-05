package com.example.mihai.littlespice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String swag = new String("swaga");
        Log.e("matrice", swag);
    }

    public void newCirFile(View tempView) {
        Intent tempIntent = new Intent(this, cirActivity.class);
        this.startActivity(tempIntent);
    }

    public void helpStart(View tempView) {
        Intent tempIntent = new Intent(this, helpActivity.class);
        this.startActivity(tempIntent);
    }
}
