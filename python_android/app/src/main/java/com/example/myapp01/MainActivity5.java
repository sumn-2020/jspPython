package com.example.myapp01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {


    EditText et1, et2, et3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);

        //클릭이벤트
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClick();
            }
        });

    }


    public void btnClick() {
       /* //et1 id를 가진 EditText를 받아서 number1 객체에 저장
        EditText number1 = (EditText)findViewById(R.id.et1);
        EditText number2 = (EditText)findViewById(R.id.et2);
        EditText result = (EditText)findViewById(R.id.et3);

        //각각의 값을 정수형으로 저장
        int n1 = Integer.parseInt(number1.getText().toString());
        int n2 = Integer.parseInt(number2.getText().toString());

        //더하기 결과 반환
        result.setText(Integer.toString(n1+n2));*/



        String a = et1.getText().toString();
        String b = et2.getText().toString();
        int aa = Integer.parseInt(a);
        int bb = Integer.parseInt(b);
        int sum = aa + bb;
        et3.setText(sum+"");





    }

}