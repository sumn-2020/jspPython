package com.example.myapp01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

   /* private Button btn;
    private TextView tvCount;
    private int count = 10000;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*tvCount = findViewById(R.id.tv);
        tvCount.setText(count+"");
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                tvCount.setText(count+"");
            }
        });*/


        TextView tv = findViewById(R.id.tv);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String a = (String)tv.getText();
               int aa = Integer.parseInt(a);
               aa++;

               tv.setText(aa+"");

            }
        });










    }







}