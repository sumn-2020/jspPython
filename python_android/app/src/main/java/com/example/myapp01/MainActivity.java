package com.example.myapp01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //버튼 클릭시 good Evening으로 변경하기
       /* final TextView text = (TextView)findViewById(R.id.tv);
        Button btn1 = (Button)findViewById(R.id.btn);

        btn1.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                text.setText("Good Evening");
            }
        });*/


        TextView tv = findViewById(R.id.tv);
        Button btn = findViewById(R.id.btn);
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("굿이브닝");
                Log.d("ddit", "hello");
                //System.out.println("hello");
            }
        });










    }







}