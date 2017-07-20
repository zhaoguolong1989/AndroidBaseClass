package com.github.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void a(View view){
        ActUtils.STActivity(this,Main2Activity.class,
                Pair.create(findViewById(R.id.a),"a"),
                Pair.create(findViewById(R.id.b),"b"));
        // Pair.create(findViewById(R.id.b),"b")
        //Pair.create(findViewById(R.id.a),"a"),
    }
}
