package com.example.myapp01;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity3 extends AppCompatActivity {



    TextView tv1,tv2,tv3,tv4,tv5,tv6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myclick();
            }
        });

    }



    public void myclick() {

        int[] arr45 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45};

        for (int i = 0; i < 1000; i++) {
            int rnd = (int)(Math.random()*45);
            int a = arr45[0];
            int b = arr45[rnd];
            arr45[0]=b;
            arr45[rnd]=a;
        }
        Log.d("ddit", arr45[0]+"");
        Log.d("ddit", arr45[1]+"");
        tv1.setText(arr45[0]+"");
        tv2.setText(arr45[1]+"");
        tv3.setText(arr45[2]+"");
        tv4.setText(arr45[3]+"");
        tv5.setText(arr45[4]+"");
        tv6.setText(arr45[5]+"");





    }

}