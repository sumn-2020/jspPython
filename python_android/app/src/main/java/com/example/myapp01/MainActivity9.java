package com.example.myapp01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity9 extends AppCompatActivity {


    TextView tv_disp;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnCall;
    String str = "";
    //String[] str = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        tv_disp = findViewById(R.id.tv_disp);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnCall = findViewById(R.id.btnCall);


        //버튼누르기 
        btn0.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});
        btn1.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});
        btn2.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});
        btn3.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});
        btn4.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});
        btn5.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});
        btn6.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});
        btn7.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});
        btn8.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});
        btn9.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {myclick(view);}});

        //call버튼누르기
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCall();
            }
        });
    }

    public void myCall() {
        String str_tel = tv_disp.getText().toString();
        Toast.makeText(getApplicationContext(), "caliing\n" + str_tel, Toast.LENGTH_LONG).show();
    }


    public void myclick(View view) {
        Button obj = (Button) view; //파라미터 값으로 view 받아서  view를 버튼으로 캐스팅해서 버튼역할로 변경시키기
        //Log.d("메시지",obj.getText().toString());
        String str_old = tv_disp.getText().toString(); //오래된글자(기존글자)
        String str_new = obj.getText().toString(); //새로운 글자
        tv_disp.setText(str_old + str_new); //기존글자 + 새로운글자
    }


}