package com.narvasoft.bd;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    TextView lblWellcome;
    TextView lblname;
    public static final String nick = "names";
    public static final String name = "realname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lblWellcome= (TextView) findViewById(R.id.textWellcome);
        String usuario = getIntent().getStringExtra("names");
        String realname = getIntent().getStringExtra("realname");
        lblname = (TextView) findViewById(R.id.lblname);
        lblWellcome.setText("¡Welcome "+usuario+"!");
        lblname.setText("Real name "+realname);
    }
}